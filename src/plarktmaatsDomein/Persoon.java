package plarktmaatsDomein;

import java.util.Calendar;


public abstract class Persoon {
	
	private String 	gebruikersnaam;
	private String	voornaam;
	private String	achternaam;
	private String	email;
	private Calendar geboortedatum;
	private String wachtwoord;
	
	public Persoon(String gNm, String vNm, String aNm, String mail, Calendar gebdat, String ww) {
		gebruikersnaam = gNm;
		voornaam = vNm;
		achternaam 	= aNm;
		email = mail;
		setGeboortedatum(gebdat);
		setWachtwoord(ww);
	}

	public String getAchternaam() {
		return achternaam;
	}


	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}


	public void setGebruikersnaam(String gNm) {
		this.gebruikersnaam = gNm;
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


	public Calendar getGeboortedatum() {
		return geboortedatum;
	}


	public void setGeboortedatum(Calendar geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
}
