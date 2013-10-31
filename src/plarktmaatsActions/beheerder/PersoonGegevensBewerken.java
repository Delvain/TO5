package plarktmaatsActions.beheerder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;
import plarktmaatsDomein.Beheerder;

import com.opensymphony.xwork2.ActionSupport;

public class PersoonGegevensBewerken extends ActionSupport {
	PersoonDAOImpl pdi = new PersoonDAOImpl();
	private String voornaam;
	private String achternaam;
	private String email;
	private String wachtwoord;
	private String wachtwoord2;
	private String strGeboorteDatum;
	private Calendar geboorteDatum = Calendar.getInstance();
	private String bankRekening;
	private boolean geblokkeerd;
	private Gebruiker gebruiker;
	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	public String execute() {
		System.out.println("Updating user " + gebruiker.getGebruikersnaam());
		Gebruiker g = (Gebruiker) gebruiker;
		g.setVoornaam(voornaam);
		g.setAchternaam(achternaam);
		g.setEmail(email);
		g.setWachtwoord(wachtwoord);
		g.setGeboortedatum(geboorteDatum);
		g.setBankNr(bankRekening);
		pdi.update(g.getGebruikersnaam(), g);

		return ActionSupport.SUCCESS;
	}

	public void validate() {
		voornaam = voornaam.trim();
		achternaam = achternaam.trim();
		email = email.trim();
		wachtwoord = wachtwoord.trim();
		wachtwoord2 = wachtwoord2.trim();
		geboorteDatum = Calendar.getInstance();

		// Geboortedatum omzetten naar Calendar
		if (!strGeboorteDatum.equals("")) {
			try {
				geboorteDatum.setTime(df.parse(strGeboorteDatum));
			} catch (ParseException e) {
				addFieldError("strGeboorteDatum", "Geen geldige datum!");
			}
		} else {
			geboorteDatum = gebruiker.getGeboortedatum();
		}

		if (email.length() == 0) {
			email = gebruiker.getEmail();
		}

		if (voornaam.length() == 0) {
			voornaam = gebruiker.getVoornaam();
		}

		if (achternaam.length() == 0) {
			achternaam = gebruiker.getAchternaam();
		}

		if (!wachtwoord.equals(wachtwoord2)) {
			addFieldError("wachtwoord2", "Wachtwoorden komen niet overeen!");
		}

		if (wachtwoord.length() == 0) {
			wachtwoord = gebruiker.getWachtwoord();
		}

		if (bankRekening.length() == 0) {
			Gebruiker g = (Gebruiker) gebruiker;
			bankRekening = g.getBankNr();
		}
		
	}

	// Getters&Setters
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
	
	public String getWachtwoord2() {
		return wachtwoord2;
	}

	public void setWachtwoord2(String wachtwoord2) {
		this.wachtwoord2 = wachtwoord2;
	}

	public boolean isGeblokkeerd() {
		return geblokkeerd;
	}

	public void setGeblokkeerd(boolean geblokkeerd) {
		this.geblokkeerd = geblokkeerd;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}

	

}
