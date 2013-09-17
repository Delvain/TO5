package plarktmaatsDomein;


public abstract class Persoon {
	
	private int 	id;
	private String	voornaam;
	private String	achternaam;
	private String	email;
	
	private PlarktMaats plarktMaats;
	
	public Persoon(String vNm, String aNm, String mail, PlarktMaats pl) {
		id = 0;
		voornaam = vNm;
		achternaam 	= aNm;
		email = mail;
		plarktMaats = pl;
		
		plarktMaats.voegPersoonToe(this);
	}
	

	public String getAchternaam() {
		return achternaam;
	}


	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}


	public PlarktMaats getPlarktMaats() {
		return plarktMaats;
	}


	public void setPlarktMaats(PlarktMaats plarktMaats) {
		this.plarktMaats = plarktMaats;
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
