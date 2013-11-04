package plarktmaatsActions.gebruikers;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;
import tools.ProjectTools;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class KoopCredits extends ActionSupport implements UserAware {
	private Persoon user;
	String credits;
	int creds;
	
	public String execute() {
		PersoonDAOImpl pdi = new PersoonDAOImpl();
		pdi.addCredits(user.getGebruikersnaam(), creds);
				
		return SUCCESS;
	}
	
	public void validate() {
		if(!ProjectTools.isNumeric(credits)) {
			addFieldError("credits", "Vul een bedrag in");
			return;
		}
		creds = Integer.parseInt(credits);
		if(creds <= 0) {
			addFieldError("credits", "U kunt geen negatief aantal credits kopen");
		}
		if(!(user instanceof Gebruiker)) {
			addFieldError("credits", "Alleen gebruikers mogen credits kopen");
			return;
		}
		Gebruiker usergebruiker = (Gebruiker)user;
		if(usergebruiker.getGeblokkeerd()) {
			addFieldError("credits", "U mag geen credits kopen");
		}
	}
	
	@Override
	public void setUser(Persoon user) {
		this.user = user;		
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}
}
