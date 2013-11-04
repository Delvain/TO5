package plarktmaatsActions.gebruikers;

import java.util.Calendar;
import java.util.Date;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.BodDAOImpl;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Bod;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;
import plarktmaatsDomein.Veiling;
import tools.ProjectTools;

import com.opensymphony.xwork2.ActionSupport;

public class VoegBodToe extends ActionSupport implements UserAware {
	private static final long serialVersionUID = 1L;
	private String id;
	private String credits;
	private Persoon user;

	public String execute() {
		Gebruiker g = (Gebruiker)user;
		int bedrag = Integer.parseInt(credits);
		Bod b = new Bod(0, bedrag, g, id); //Bod maken
		BodDAOImpl bodDAO = new BodDAOImpl();
		bodDAO.create(b); //Bod in db zetten
		
		return SUCCESS;
	}
	
	public void validate() {
		if(!ProjectTools.isNumeric(credits)) { //credits moet een nummer zijn
			addFieldError("credits", "Vul een bod in");
			return; //stoppen
		}
		VeilingDAOImpl veilingDAO = new VeilingDAOImpl();
		Veiling v = veilingDAO.read(id); //veiling ophalen
		if(v == null) { //veiling moet bestaan
			addFieldError("credits", "Deze veiling bestaat niet (meer)"); 
			return; //stoppen
		}
		if(v.getGeblokkeerd()) { //veiling mag niet geblokkeerd zijn
			addFieldError("credits", "Deze veiling is geblokkeerd");
		}
		int creditsInt = Integer.parseInt(credits);
		if(v.getMinBedrag() > creditsInt) { //credits moet hoger zijn dan minimumbedrag
			addFieldError("credits", "Dit bod is onder het minimumbedrag");
		}
		if(v.getHoogsteBod() != null) { //als er een bod is gedaan:
			if(v.getHoogsteBod().getBedrag() >= creditsInt) { //credits moet hoger zijn dan hoogste bod
				addFieldError("credits", "Er is al hoger geboden");
			}
		}
		if(!(user instanceof Gebruiker)) { //als niet ingelogd als Gebruiker:
			addFieldError("credits", "Alleen gebruikers mogen bieden"); //fout: moet ingelogd zijn als Gebruiker
			return; //stoppen
		}
		Gebruiker usergebruiker = (Gebruiker)user; //Persoon user parsen naar Gebruiker
		if(usergebruiker.getGeblokkeerd()) { //ingelogde Gebruiker mag niet geblokkeeerd zijn
			addFieldError("credits", "U mag niet bieden");
		}
		if(usergebruiker.getCredits()-creditsInt < 0) { //ingelogde Gebruiker moet genoeg credits hebben
			addFieldError("credits", "U heeft niet genoeg credits");
		}
		Date eindTijd = v.getEindTijd().getTime();
		Date nu = Calendar.getInstance().getTime();
		long diff = (eindTijd.getTime() - nu.getTime())/1000;
		if(diff < 0) { //Veiling mag niet gesloten zijn
			addFieldError("credits", "De veiling is gesloten");
		}

	}
	
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setUser(Persoon user) {
		this.user = user;
	}
}
