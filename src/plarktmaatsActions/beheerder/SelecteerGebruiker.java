package plarktmaatsActions.beheerder;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class SelecteerGebruiker extends ActionSupport implements SessionAware {
	private String gebruikersnaam;
	private Gebruiker gebruiker;
	private Map<String,Object> session;
	
	public String execute() {
		PersoonDAOImpl data = new PersoonDAOImpl();
		Persoon p = data.read(gebruikersnaam);
		gebruiker = (Gebruiker)p;
		session.put("gebruiker", gebruiker);
		return SUCCESS;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}

	@Override
	public void setSession(Map<String, Object> sess) {
		session = sess;		
	}
	
	public Map<String,Object> getSession() {
		return session;
	}


}