package plarktmaatsDomein;


public abstract class Persoon {
	
	private int 	id;
	private String	voornaam;
	private String	achternaam;
	private String	email;
	
	public Persoon(String vNm, String aNm, String mail) {
		id = 0;
		voornaam = vNm;
		achternaam 	= aNm;
		email = mail;
	}
	

	public String getAchternaam() {
		return achternaam;
	}


	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getVoornaam() {
		return voornaam;
	}


	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
