package plarktmaatsActions.gebruikers;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDomein.Persoon;

import com.opensymphony.xwork2.ActionSupport;

public class KoopCredits extends ActionSupport implements UserAware {
	private Persoon user;
	
	public String execute() {
		PersoonDAOImpl pdi = new PersoonDAOImpl();
		pdi.addCredits(user.getGebruikersnaam());
		return SUCCESS;
	}
	
	@Override
	public void setUser(Persoon user) {
		this.user = user;		
	}

}
