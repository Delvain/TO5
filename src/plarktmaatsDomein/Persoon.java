package plarktmaatsDomein;

import java.util.Calendar;


public abstract class Persoon {
	
	private int 	id;
	private String	voornaam;
	private String	achternaam;
	private String	email;
	private Calendar geboortedatum;
	
	public Persoon(String vNm, String aNm, String mail, Calendar gebdat) {
		id = 0;
		voornaam = vNm;
		achternaam 	= aNm;
		email = mail;
		setGeboortedatum(gebdat);
	}
	
	public Persoon(int id, String vNm, String aNm, String mail, Calendar gebdat) {
		this.id = id;
		voornaam = vNm;
		achternaam 	= aNm;
		email = mail;
		setGeboortedatum(gebdat);
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


	public Calendar getGeboortedatum() {
		return geboortedatum;
	}


	public void setGeboortedatum(Calendar geboortedatum) {
		this.geboortedatum = geboortedatum;
	}
}
