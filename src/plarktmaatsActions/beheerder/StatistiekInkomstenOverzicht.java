package plarktmaatsActions.beheerder;

import java.util.Calendar;
import java.util.List;

import plarktmaatsDAO.VeilingDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

public class StatistiekInkomstenOverzicht extends ActionSupport {
	private List<String[]> veilingen;
	private String maand;
	
	public String execute() {
		VeilingDAOImpl data = new VeilingDAOImpl();
		Calendar begin = Calendar.getInstance();
		Calendar eind = Calendar.getInstance();
		
		setVeilingen(data.getInkomstenOverzicht(begin, eind));
		return SUCCESS;
	}
	
	public void validate() {
		if (maand != "" && maand != null) {
			
		}
	}

	public List<String[]> getVeilingen() {
		return veilingen;
	}

	public void setVeilingen(List<String[]> veilingen) {
		this.veilingen = veilingen;
	}

	public String getMaand() {
		return maand;
	}

	public void setMaand(String maand) {
		this.maand = maand;
	}
}
