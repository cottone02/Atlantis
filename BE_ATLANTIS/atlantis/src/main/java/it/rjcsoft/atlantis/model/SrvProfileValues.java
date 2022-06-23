package it.rjcsoft.atlantis.model;

public class SrvProfileValues {

	private Long ID_PROFILE;
	private String ID_ELEMENT;
	private String VALUE;
	public Long getID_PROFILE() {
		return ID_PROFILE;
	}
	public void setID_PROFILE(Long iD_PROFILE) {
		ID_PROFILE = iD_PROFILE;
	}
	public String getID_ELEMENT() {
		return ID_ELEMENT;
	}
	public void setID_ELEMENT(String iD_ELEMENT) {
		ID_ELEMENT = iD_ELEMENT;
	}
	public String getVALUE() {
		return VALUE;
	}
	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}
	@Override
	public String toString() {
		return "SrvProfileValues [ID_PROFILE=" + ID_PROFILE + ", ID_ELEMENT=" + ID_ELEMENT + ", VALUE=" + VALUE + "]";
	}
	
	
}
