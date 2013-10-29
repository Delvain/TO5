package plarktmaatsActions.visitor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDAO.PlarktmaatsDAOInterface;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CreateAccount extends ActionSupport {
	
	PersoonDAOImpl pdi = new PersoonDAOImpl();
	private String gebruikersnaam;
	private String voornaam;
	private String achternaam;
	private String email;
	private String wachtwoord;
	private String wachtwoord2;
	private String strGeboorteDatum;
	private Calendar geboorteDatum = Calendar.getInstance();
	private String bankRekening;
	private Persoon p;
	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	
	public String execute(){
		p = new Gebruiker(gebruikersnaam, voornaam, achternaam, email, geboorteDatum, bankRekening, wachtwoord);
		pdi.create(p);
		
		return SUCCESS;
	}

	public void validate(){
		gebruikersnaam = gebruikersnaam.toLowerCase();
		voornaam = voornaam.trim();
		achternaam = achternaam.trim();
		email = email.trim();
		wachtwoord = wachtwoord.trim();
		wachtwoord2 = wachtwoord2.trim();
		geboorteDatum = Calendar.getInstance();
		
		// Geboortedatum omzetten naar Calendar
		if (strGeboorteDatum != null) {
			try {
				geboorteDatum.setTime(df.parse(strGeboorteDatum));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			addActionError("Geboortedatum is verplicht");			
		}
		
		if (gebruikersnaam.length() == 0 ){			
			addActionError("Gebruikersnaam is verplicht");
		}
		
		if (email.length() == 0 ){			
			addFieldError("email", "Email is verplicht");
		}
		
		if (voornaam.length() == 0) {
			addFieldError("voornaam", "Voornaam is veprlicht");
		}
		
		if (achternaam.length() == 0) {
			addFieldError("achternaam", "Achternaam is verplicht");
		}
	
		else if (pdi.read(gebruikersnaam) != null){		
			addFieldError("bestaat", "Gebruiker bestaat al");
		}
		
		if (wachtwoord.length() == 0 ) {			
			addFieldError("wachtwoord", "Wachtwoord is verplicht");
		}
		
		if (!wachtwoord.equals(wachtwoord2)) {
			addFieldError("wachtwoord2", "Wachtwoorden komen niet overeen");
		}
	}
	
//	Getters&Setters
	public String getGebruikersnaam() {
		return email;
	}
	
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	
	public String getVoornaam() {
		return voornaam;
	}
	
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	
	public String getAchternaam() {
		return achternaam;
	}
	       
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	
	public void setGeboorteDatum(Calendar geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}
	
	public Calendar getGeboorteDatum() {
		return geboorteDatum;
	}
	
	public String getStrGeboorteDatum() {
		return strGeboorteDatum;
	}

	public void setStrGeboorteDatum(String strGeboorteDatum) {
		this.strGeboorteDatum = strGeboorteDatum;
	}
	
	public String getBankRekening() {
		return bankRekening;
	}
	
	public void setBankRekening(String bankRekening) {
		this.bankRekening = bankRekening;
	}
	
	public String getWachtwoord() {
		return wachtwoord;
	}
	
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	
	public void setWachtwoord2(String wachtwoord2) {
		this.wachtwoord2 = wachtwoord2;
	}
}
