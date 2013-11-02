package plarktmaatsActions.beheerder;
import java.util.List;

import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDomein.Gebruiker;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class AlleGebruikers extends ActionSupport {
	private List<Gebruiker> gebruikers;
	
	public String execute() {
		PersoonDAOImpl data = new PersoonDAOImpl();
		setGebruikers((List<Gebruiker>)data.getAllGebruikers());
		return SUCCESS;
	}

	public List<Gebruiker> getGebruikers() {
		return gebruikers;
	}

	public void setGebruikers(List<Gebruiker> gebruikers) {
		this.gebruikers = gebruikers;
	}

}