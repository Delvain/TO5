package plarktmaatsActions.gebruikers;

import java.util.ArrayList;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Persoon;
import tools.ProjectTools;

import com.opensymphony.xwork2.ActionSupport;

public class MijnVeilingen extends ActionSupport implements UserAware {
	private static final long serialVersionUID = 1L;
	private ArrayList mijnVeilingen = null;
	private String gebruikersNaam;
	private String prijs = "Error";
	private String timer = "Error";
	private String id = "";
	private Persoon user;

	public String execute() {
		VeilingDAOImpl vDI = new VeilingDAOImpl();
		mijnVeilingen = vDI.mijnVeilingen(gebruikersNaam);
		
		if(mijnVeilingen.size() < 1) {
			return SUCCESS;
		}
		
		for (int i = 0; i < mijnVeilingen.size(); i++) {
			try {
				ArrayList<String> data = vDI.readBedragTijd((Integer) mijnVeilingen.get(i));
				prijs = data.get(0);
				String timerTemp = data.get(1);
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