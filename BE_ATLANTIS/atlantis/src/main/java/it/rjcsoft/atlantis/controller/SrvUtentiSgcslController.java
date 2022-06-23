package it.rjcsoft.atlantis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.atlantis.dto.LoginDTO;
import it.rjcsoft.atlantis.dto.SrvUtentiSgcslDTO;
import it.rjcsoft.atlantis.service.SrvUtentiSgcslService;

@CrossOrigin
@RestController
@RequestMapping(value = "/utente")
public class SrvUtentiSgcslController extends BaseController {

	@Autowired
	SrvUtentiSgcslService srvUtentiSgcslService;

	@GetMapping
	public ResponseEntity<List<SrvUtentiSgcslDTO>> allUsers() {
		log.debug("START - FIND ALL USERS");
		return new ResponseEntity<>(srvUtentiSgcslService.getAll(), HttpStatus.OK);

	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> Login(@RequestBody LoginDTO user ) {
		log.debug("START - login user={}",user);	      
		SrvUtentiSgcslDTO userDTO = srvUtentiSgcslService.Login(user);
		if(userDTO.getLOGIN()!=null) {
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Username e/o password errata", HttpStatus.BAD_REQUEST);
		}
		
	

	}
	
	@GetMapping("/check")
	public ResponseEntity<SrvUtentiSgcslDTO> checkToken(@RequestParam String token) {
		log.debug("Check token: " + token);
		try {
			SrvUtentiSgcslDTO res = srvUtentiSgcslService.check(token);
			log.debug("Login - res: " + res);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Errore in check token - " + token, e);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
