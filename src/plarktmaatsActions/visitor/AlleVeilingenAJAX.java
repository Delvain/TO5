package plarktmaatsActions.visitor;

import java.util.ArrayList;

import plarktmaatsDAO.VeilingDAOImpl;
import tools.ProjectTools;

import com.opensymphony.xwork2.ActionSupport;

public class AlleVeilingenAJAX extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String prijs = "Error";
	private String timer = "Error";
	private String id = "";

	public String execute(){
		id = id.trim();
		
		if(id.length() < 0)
			return SUCCESS;
		
		VeilingDAOImpl vDI = new VeilingDAOImpl();
		try {
			ArrayList<String> data = vDI.readBedragTijd(Integer.parseInt(id)); //data ophalen
			prijs = data.get(0); //data(0) prijs
			String timerTemp = data.get(1);
			if(Integer.parseInt(timerTemp) > 0)
				timer = ProjectTools.SecToString(timerTemp); //Niet gesloten. Format naar mooie string
			else
				timer = "Gesloten";
		} catch(Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}
		return SUCCESS;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getPrijs() {
		return prijs;
	}
	public String getTimer() {
		return timer;
	}
}
