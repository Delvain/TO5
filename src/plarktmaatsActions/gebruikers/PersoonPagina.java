package plarktmaatsActions.gebruikers;


import java.util.List;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Persoon;
import plarktmaatsDomein.Veiling;
import tools.ProjectTools;

import com.opensymphony.xwork2.ActionSupport;

public class PersoonPagina extends ActionSupport implements UserAware {
	private static final long serialVersionUID = 1L;
	private List mijnVeilingen = null;
	private List mijnBiedingen = null;
	private String gebruikersNaam;
	private String prijs = "Error";
	private String timer = "Error";
	private String id = "";
	private Persoon user;
	private List<String> mijnVeilingData;
	private List<String> mijnBiedingenData;

	public String execute() {
		VeilingDAOImpl vDI = new VeilingDAOImpl();
		mijnVeilingen = vDI.mijnVeilingen(gebruikersNaam);
		mijnBiedingen = vDI.mijnBiedingen(gebruikersNaam);
		
		if(mijnVeilingen.size() < 1 && mijnBiedingen.size() < 1) {
			return SUCCESS;
		}
		
		for (int i = 0; i < mijnVeilingen.size(); i++) {
			try {
				mijnVeilingen.add(vDI.mijnVeilingen(gebruikersNaam));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < mijnBiedingen.size(); i++) {
			try {
				mijnBiedingenData = vDI.readBedragTijd((Integer) mijnBiedingen.get(i));
				prijs = mijnBiedingenData.get(0);
				String timerTemp = mijnBiedingenData.get(1);
				if(Integer.parseInt(timerTemp) > 0) {
					timer = ProjectTools.SecToString(timerTemp);
				} else {
					timer = "Gesloten";
				}
			} catch(Exception e) {
				e.printStackTrace();
				return SUCCESS;
			}
		}
		return SUCCESS;
	}
	
	public String getGebruikersnaam() {
		return user.getGebruikersnaam();
	}
	
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersNaam = gebruikersnaam;
	}
	
	public void setData(List<String> data) {
		this.mijnVeilingData = data;
	}
	
	public String getPrijs() {
		return prijs;
	}
	
	public Persoon getUser() {
		return user;
	}

	@Override
	public void setUser(Persoon user) {
		this.user = user;
	}
}