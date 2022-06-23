package it.rjcsoft.atlantis.service;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.atlantis.dto.FixProfileItemsDTO;
import it.rjcsoft.atlantis.dto.LoginDTO;
import it.rjcsoft.atlantis.dto.SrvUtentiSgcslDTO;
import it.rjcsoft.atlantis.mapper.FixProfileItemsMapper;
import it.rjcsoft.atlantis.mapper.SrvProfileValuesMapper;
import it.rjcsoft.atlantis.mapper.SrvUtentiSgcslMapper;
import it.rjcsoft.atlantis.model.FixProfileItems;
import it.rjcsoft.atlantis.model.SrvUtentiSgcsl;
import it.rjcsoft.atlantis.security.JwtUtil;

@Service
public class SrvUtentiSgcslService extends BaseService {

	@Autowired
	SrvUtentiSgcslMapper srvUtentiSgcslMapper;

	@Autowired
	FixProfileItemsMapper fixProfileItemsMapper;

	@Autowired
	SrvProfileValuesMapper srvProfileValuesMapper;
	
	@Autowired
	private JwtUtil jwtUtil;

	public List<SrvUtentiSgcslDTO> getAll() {
		log.debug("START - FIND ALL USERS");
		List<SrvUtentiSgcsl> users = srvUtentiSgcslMapper.getAll();
		List<SrvUtentiSgcslDTO> usersDto = new ArrayList();
		users.forEach(x -> {
			usersDto.add(convertSrvUtentiSgcslDTO(x));
		});
		log.debug("END - Lista Utenti={}", usersDto);
		return usersDto;

	}

	public SrvUtentiSgcslDTO Login(LoginDTO user) {
		log.debug("START - Login  username={},password={}", user.getUsername(), user.getPassword());
		String psw_encprypted = encryptPassword(user.getPassword());
		SrvUtentiSgcsl utente = srvUtentiSgcslMapper.login(user.getUsername());

		SrvUtentiSgcslDTO utenteDTO = new SrvUtentiSgcslDTO();
		if (utente != null && utente.getPSW().equals(psw_encprypted)) {
			utenteDTO = convertSrvUtentiSgcslDTO(utente);
			utenteDTO.setMENU(creazioneMenu(utente));
		}
		utenteDTO.setPSW(null);
		utenteDTO.setToken(jwtUtil.generateToken(utente));
		log.debug("END - Login riuscita  utente={}", utenteDTO);
		return utenteDTO;
	}
	
	public SrvUtentiSgcsl getProfiloUtente(SrvUtentiSgcsl user) {
		log.debug("START - Login  username={},password={}", user.getLOGIN(), user.getPSW());
		String psw_encprypted = encryptPassword(user.getPSW());
		SrvUtentiSgcsl utente = srvUtentiSgcslMapper.getProfiloUtente(user.getLOGIN(),psw_encprypted);
		log.debug("END - Login riuscita  utente={}", utente);
		return utente;
		
	
	}
	public SrvUtentiSgcslDTO check(String token) throws Exception {
		SrvUtentiSgcsl profiloUtente = jwtUtil.parseToken(token);
		String psw_encprypted = encryptPassword(profiloUtente.getPSW());
		SrvUtentiSgcsl user = srvUtentiSgcslMapper.getProfiloUtente(profiloUtente.getLOGIN(),psw_encprypted);
		SrvUtentiSgcslDTO userdto = convertSrvUtentiSgcslDTO(user);
		return userdto;
	}

	private List<FixProfileItemsDTO> creazioneMenu(SrvUtentiSgcsl utente) {
		log.debug("START - creazioneMenu utente={}", utente);
		List<FixProfileItems> menu1 = fixProfileItemsMapper.menuPrimoLivello();
		List<FixProfileItemsDTO> menu1DTO = new ArrayList();
		menu1.forEach(x -> {
			if (!srvProfileValuesMapper.abilitazioneMenu(utente.getIDPROFILE(), x.getNOME()+"%").isEmpty()) {
				FixProfileItemsDTO xDTO = convertFixProfileItemsDTO(x);
				menu1DTO.add(xDTO);
			}
		});

		menu1DTO.forEach(x -> {
			List<FixProfileItems> menu2 = fixProfileItemsMapper.menuSecondoLivello(x.getNOME()+".%",x.getNOME()+".%.%");
			List<FixProfileItemsDTO> menu2DTO = new ArrayList();
			menu2.forEach(y -> {
				FixProfileItemsDTO yDTO = convertFixProfileItemsDTO(y);
				yDTO.setROUTING(yDTO.getDESCRIZIONE().replaceAll("\\s+", ""));
				menu2DTO.add(yDTO);
			});
			x.setMENU2(menu2DTO);
			x.setROUTING(x.getDESCRIZIONE().replaceAll("\\s+", ""));
		});
		log.debug("END - creazioneMenu completato menu={}", menu1DTO);
		return menu1DTO;
	}
	
	private String encryptPassword(String password) {
		log.debug("START - encryptPassword password={}", password);
		try {
			byte[] textBytes = password.getBytes("UTF-8");
			int[] sessionKey = { 145, 12, 32, 245, 98, 132, 98, 214, 6, 77, 131, 44, 221, 3, 9, 50 };
			int[] iv = { 15, 122, 132, 5, 93, 198, 44, 31, 9, 39, 241, 49, 250, 188, 80, 7 };
			byte[] sessionKeyB = new byte[sessionKey.length];
			for (int i = 0; i < sessionKey.length; i++) {
				sessionKeyB[i] = (byte) (sessionKey[i] & 0xFF);
			}
			byte[] ivB = new byte[iv.length];
			for (int i = 0; i < iv.length; i++) {
				ivB[i] = (byte) (iv[i] & 0xFF);
			}
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(sessionKeyB, "AES"), new IvParameterSpec(ivB));
			byte[] ciphertext = cipher.doFinal(textBytes);
			return Hex.encodeHexString(ciphertext).toUpperCase();
		} catch (Exception e) {
			log.error("END - encrypt password non riuscito err={}", e.getMessage());
			return "";
		}
	}


	private FixProfileItemsDTO convertFixProfileItemsDTO(FixProfileItems x) {
		FixProfileItemsDTO y = new FixProfileItemsDTO();
		BeanUtils.copyProperties(x, y);
		return y;
	}

	private SrvUtentiSgcslDTO convertSrvUtentiSgcslDTO(SrvUtentiSgcsl x) {
		SrvUtentiSgcslDTO y = new SrvUtentiSgcslDTO();
		BeanUtils.copyProperties(x, y);
		return y;
	}

	
}
