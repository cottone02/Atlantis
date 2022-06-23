package it.rjcsoft.atlantis.dto;

public class LoginDTO {

//	private String LP_NOME;
//	private String PSW;
//	public String getLP_NOME() {
//		return LP_NOME;
//	}
//	public void setLP_NOME(String lP_NOME) {
//		LP_NOME = lP_NOME;
//	}
//	public String getPSW() {
//		return PSW;
//	}
//	public void setPSW(String pSW) {
//		PSW = pSW;
//	}
//	@Override
//	public String toString() {
//		return "LoginDTO [LP_NOME=" + LP_NOME + ", PSW=" + PSW + "]";
//	}
//	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [username=" + username + ", password=" + password + "]";
	}
	
	
	
}
