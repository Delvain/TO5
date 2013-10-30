package plarktmaatsActions.gebruikers;
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

public class GegevensAanpassen extends ActionSupport implements UserAware{
	PersoonDAOImpl pdi = new PersoonDAOImpl();
	private String voornaam;
	private String achternaam;
	private String email;
	private String wachtwoord;
	private String wachtwoord2;
	private String strGeboorteDatum;
	private Calendar geboorteDatum = Calendar.getInstance();
	private String bankRekening;
	private Persoon user;
	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	public String execute() {
		System.out.println("Updating user " + user.getGebruikersnaam());
		if (user instanceof Gebruiker) {
			Gebruiker g = (Gebruiker) user;
			g.setVoornaam(voornaam);
			g.setAchternaam(achternaam);
			g.setEmail(email);
			g.setWachtwoord(wachtwoord);
			g.setGeboortedatum(geboorteDatum);
			g.setBankNr(bankRekening);
			pdi.update(g.getGebruikersnaam(), g);
		}
		if (user instanceof Beheerder) {
			Beheerder b = (Beheerder) user;
			b.setVoornaam(voornaam);
			b.setAchternaam(achternaam);
			b.setEmail(email);
			b.setWachtwoord(wachtwoord);
			b.setGeboortedatum(geboorteDatum);
			pdi.update(b.getGebruikersnaam(), b);
		}
		
		

		return SUCCESS;
	}

	public void validate() {
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
				addFieldError("strGeboorteDatum","Geen geldige datum!");
			}
		} else {
			geboorteDatum = user.getGeboortedatum();
		}
		
		if (email.length() == 0 ){			
			email = user.getEmail();
		}
		
		if (voornaam.length() == 0) {
			voornaam = user.getVoornaam();
		}
		
		if (achternaam.length() == 0) {
			achternaam = user.getAchternaam();
		}
		
		if (!wachtwoord.equals(wachtwoord2)) {
			addFieldError("wachtwoord2", "Wachtwoorden komen niet overeen!");
		}
		
		if (wachtwoord.length() == 0) {			
			wachtwoord = user.getWachtwoord();
		}
		
		if (user instanceof Beheerder) {
			if (bankRekening.length() != 0 ) {			
				addFieldError("bankRekening", "Als beheerder mag u geen rekeningnummer opgeven!");
			}
		} else if (user instanceof Gebruiker) {
			if (bankRekening.length() == 0 ) {			
				Gebruiker g = (Gebruiker) user;
				bankRekening = g.getBankNr();
			}
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

	public void setWachtwoord2(String wachtwoord2) {
		this.wachtwoord2 = wachtwoord2;
	}

	@Override
	public void setUser(Persoon user) {
		this.user = user;
	}
}
