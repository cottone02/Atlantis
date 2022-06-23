package it.rjcsoft.atlantis.dto;

import java.util.Date;
import java.util.List;

public class SrvUtentiSgcslDTO {
	private Long LP_NUM;
	private String LP_CODICE;
	private String LP_PADRE;
	private Boolean LP_CATEGORIA;
	private String LP_NOME;
	private String LP_COGNOME;
	private String LOGIN;
	private String LP_PROFILO_OPER;
	private String PSW;
	private Long IDPROFILE;
	private String LP_GROUP_LAVORO;
	private String NOTE;
	private Boolean ABILITATO;
	private String USERID;
	private Date DATAINS;
	private Date DATAMOD;
	private String Token;
	private List<FixProfileItemsDTO> MENU;

	public Long getLP_NUM() {
		return LP_NUM;
	}

	public void setLP_NUM(Long lP_NUM) {
		LP_NUM = lP_NUM;
	}

	public String getLP_CODICE() {
		return LP_CODICE;
	}

	public void setLP_CODICE(String lP_CODICE) {
		LP_CODICE = lP_CODICE;
	}

	public String getLP_PADRE() {
		return LP_PADRE;
	}

	public void setLP_PADRE(String lP_PADRE) {
		LP_PADRE = lP_PADRE;
	}

	public Boolean getLP_CATEGORIA() {
		return LP_CATEGORIA;
	}

	public void setLP_CATEGORIA(Boolean lP_CATEGORIA) {
		LP_CATEGORIA = lP_CATEGORIA;
	}

	public String getLP_NOME() {
		return LP_NOME;
	}

	public void setLP_NOME(String lP_NOME) {
		LP_NOME = lP_NOME;
	}

	public String getLP_COGNOME() {
		return LP_COGNOME;
	}

	public void setLP_COGNOME(String lP_COGNOME) {
		LP_COGNOME = lP_COGNOME;
	}

	public String getLOGIN() {
		return LOGIN;
	}

	public void setLOGIN(String lOGIN) {
		LOGIN = lOGIN;
	}

	public String getLP_PROFILO_OPER() {
		return LP_PROFILO_OPER;
	}

	public void setLP_PROFILO_OPER(String lP_PROFILO_OPER) {
		LP_PROFILO_OPER = lP_PROFILO_OPER;
	}

	public String getPSW() {
		return PSW;
	}

	public void setPSW(String pSW) {
		PSW = pSW;
	}

	public Long getIDPROFILE() {
		return IDPROFILE;
	}

	public void setIDPROFILE(Long iDPROFILE) {
		IDPROFILE = iDPROFILE;
	}

	public String getLP_GROUP_LAVORO() {
		return LP_GROUP_LAVORO;
	}

	public void setLP_GROUP_LAVORO(String lP_GROUP_LAVORO) {
		LP_GROUP_LAVORO = lP_GROUP_LAVORO;
	}

	public String getNOTE() {
		return NOTE;
	}

	public void setNOTE(String nOTE) {
		NOTE = nOTE;
	}

	public Boolean getABILITATO() {
		return ABILITATO;
	}

	public void setABILITATO(Boolean aBILITATO) {
		ABILITATO = aBILITATO;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	public Date getDATAINS() {
		return DATAINS;
	}

	public void setDATAINS(Date dATAINS) {
		DATAINS = dATAINS;
	}

	public Date getDATAMOD() {
		return DATAMOD;
	}

	public void setDATAMOD(Date dATAMOD) {
		DATAMOD = dATAMOD;
	}
	

	public List<FixProfileItemsDTO> getMENU() {
		return MENU;
	}

	public void setMENU(List<FixProfileItemsDTO> menu) {
		this.MENU = menu;
	}
	

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	@Override
	public String toString() {
		return "SrvUtentiSgcslDTO [LP_NUM=" + LP_NUM + ", LP_CODICE=" + LP_CODICE + ", LP_PADRE=" + LP_PADRE
				+ ", LP_CATEGORIA=" + LP_CATEGORIA + ", LP_NOME=" + LP_NOME + ", LP_COGNOME=" + LP_COGNOME + ", LOGIN="
				+ LOGIN + ", LP_PROFILO_OPER=" + LP_PROFILO_OPER + ", PSW=" + PSW + ", IDPROFILE=" + IDPROFILE
				+ ", LP_GROUP_LAVORO=" + LP_GROUP_LAVORO + ", NOTE=" + NOTE + ", ABILITATO=" + ABILITATO + ", USERID="
				+ USERID + ", DATAINS=" + DATAINS + ", DATAMOD=" + DATAMOD + ", Token=" + Token + ", MENU=" + MENU
				+ "]";
	}

	

	

}
