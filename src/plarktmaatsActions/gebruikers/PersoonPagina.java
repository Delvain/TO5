package plarktmaatsActions.gebruikers;


import java.util.ArrayList;
import java.util.HashMap;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Bod;
import plarktmaatsDomein.Persoon;
import plarktmaatsDomein.Veiling;

import com.opensymphony.xwork2.ActionSupport;

public class PersoonPagina extends ActionSupport implements UserAware {
	private static final long serialVersionUID = 1L;
	private ArrayList<Veiling> mijnVeilingen = new ArrayList<Veiling>();
	private ArrayList<Bod> mijnBiedingen = new ArrayList<Bod>();
	private ArrayList<String> mijnBiedingenData = new ArrayList<String>();
	private Persoon user;
	
	public String execute() {
		System.out.println("Executing PersoonPagina.java for user " + user.getGebruikersnaam());
		VeilingDAOImpl vDI = new VeilingDAOImpl();
		mijnVeilingen = (ArrayList<Veiling>) vDI.mijnVeilingen(user.getGebruikersnaam());
		mijnBiedingen = (ArrayList<Bod>) vDI.mijnBiedingen(user.getGebruikersnaam());
		for (Bod b: mijnBiedingen) {
			mijnBiedingenData.add(vDI.getVeilingNaam(b.getVeilingId()) + ": " + b.getBedrag());
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
	
	public ArrayList<Veiling> getMijnVeilingen() {
		return mijnVeilingen;
	}
	
	public ArrayList<Bod> getMijnBiedingen() {
		return mijnBiedingen;
	}

	public ArrayList<String> getMijnBiedingenData() {
		return mijnBiedingenData;
	}
}