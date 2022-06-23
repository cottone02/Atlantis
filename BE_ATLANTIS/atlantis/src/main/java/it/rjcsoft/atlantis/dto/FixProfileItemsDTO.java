package it.rjcsoft.atlantis.dto;

import java.util.List;

public class FixProfileItemsDTO {
	private String NOME;
	private String DESCRIZIONE;
	private String ROUTING;
	private List<FixProfileItemsDTO> MENU2;
	
	public String getNOME() {
		return NOME;
	}
	public void setNOME(String nOME) {
		NOME = nOME;
	}
	public String getDESCRIZIONE() {
		return DESCRIZIONE;
	}
	public void setDESCRIZIONE(String dESCRIZIONE) {
		DESCRIZIONE = dESCRIZIONE;
	}
	public List<FixProfileItemsDTO> getMENU2() {
		return MENU2;
	}
	public void setMENU2(List<FixProfileItemsDTO> mENU2) {
		MENU2 = mENU2;
	}	
	public String getROUTING() {
		return ROUTING;
	}
	public void setROUTING(String rOUTING) {
		ROUTING = rOUTING;
	}
	@Override
	public String toString() {
		return "FixProfileItemsDTO [NOME=" + NOME + ", DESCRIZIONE=" + DESCRIZIONE + ", ROUTING=" + ROUTING + ", MENU2="
				+ MENU2 + "]";
	}

	
	
}
