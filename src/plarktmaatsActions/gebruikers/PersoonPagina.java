package plarktmaatsActions.gebruikers;


import java.util.List;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Bod;
import plarktmaatsDomein.Persoon;
import plarktmaatsDomein.Veiling;
import tools.ProjectTools;

import com.opensymphony.xwork2.ActionSupport;

public class PersoonPagina extends ActionSupport implements UserAware {
	private static final long serialVersionUID = 1L;
	private List<Veiling> mijnVeilingen = null;
	private List<Bod> mijnBiedingen = null;
	private Persoon user;

	public String execute() {
		System.out.println("Executing PersoonPagina.java for user " + user);
		VeilingDAOImpl vDI = new VeilingDAOImpl();
		mijnVeilingen = vDI.mijnVeilingen(user.getGebruikersnaam());
		mijnBiedingen = vDI.mijnBiedingen(user.getGebruikersnaam());
		
		if(mijnVeilingen.size() < 1 && mijnBiedingen.size() < 1) {
			return SUCCESS;
		}
		
		for (int i = 0; i < mijnVeilingen.size(); i++) {
			try {
				mijnVeilingen.add((Veiling) vDI.mijnVeilingen(user.getGebruikersnaam()));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < mijnBiedingen.size(); i++) {
			try {
				mijnBiedingen.add((Bod) vDI.mijnBiedingen(user.getGebruikersnaam()));
			} catch(Exception e) {
				e.printStackTrace();
				return SUCCESS;
			}
		}
		return SUCCESS;
	}
	
	public Persoon getUser() {
		return user;
	}

	@Override
	public void setUser(Persoon user) {
		this.user = user;
	}
}