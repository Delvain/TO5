package plarktmaatsActions.visitor;

import java.util.Calendar;
import java.util.Date;

import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Veiling;

import com.opensymphony.xwork2.ActionSupport;

public class AlleVeilingenAJAX extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String timer = "Error";
	private String prijs = "Error";
	private String id = "";

	public String execute(){
		id = id.trim();
		if(id.length() < 0)
			return SUCCESS;
		
		VeilingDAOImpl vDI = new VeilingDAOImpl();
		Veiling v = null;
		try {
			v = vDI.read(id);
		} catch(Exception e) {
			//niks doen
		}
		if(v == null)
			return SUCCESS;
		
		if(v.getHoogsteBod() != null)
			prijs = v.getHoogsteBod().getBedrag()+"";
		else
			prijs = v.getMinBedrag()+"";
		
		Date eindTijd 	= v.getEindTijd().getTime();
		Date nuTijd 	= Calendar.getInstance().getTime();
		long verschil	= eindTijd.getTime() - nuTijd.getTime();
		if(verschil < 1)
			timer = "Gesloten";
		else {
			int seconds		= (int) (verschil / 1000);
			timer = ""+seconds;
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
