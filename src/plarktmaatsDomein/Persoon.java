package classes;


public abstract class Persoon {
	
	private int 	id;
	private String	voornaam;
	private String	achternaam;
	private String	email;
	
	private PlarktMaats plarktMaats;
	
	public Persoon(String vNm, String aNm, String mail, PlarktMaats pl) {
		id			= 0;
		voornaam 	= vNm;
		achternaam 	= aNm;
		email		= mail;
		plarktMaats = pl;
		
		plarktMaats.voegPersoonToe(this);
	}
	
	public String getVoornaam() {
		return voornaam;
	}
	
	public String getAchternaam() {
		return achternaam;
	}
	
	public int getId() {
		return id;
	}
}
