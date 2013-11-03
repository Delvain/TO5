package plarktmaatsActions.beheerder;

import java.util.Calendar;
import java.util.List;

import plarktmaatsDAO.VeilingDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

public class StatistiekInkomstenOverzicht extends ActionSupport {
	private List<String[]> veilingen;
	Calendar begin = Calendar.getInstance();
	Calendar eind = Calendar.getInstance();
	private String maand;
	
	public String execute() {
		VeilingDAOImpl data = new VeilingDAOImpl();
		eind.set(Calendar.DATE, 1);
		eind.set(Calendar.MONTH, begin.get(Calendar.MONTH) + 1);
		begin.set(Calendar.DATE, 1);
		if (maand != "" && maand != null) {
			if (maand.contains("-")) {
				String[] parts = maand.split("-");
				String strMaand = parts[0];
				String strJaar = parts[1];
				try {
					int maand = Integer.parseInt(strMaand);
					int jaar = Integer.parseInt(strJaar);
					if (maand >= 1 || maand <= 12 || jaar >= 2012 || jaar <= eind.get(Calendar.YEAR)) {
						begin.set(Calendar.MONTH, maand -1);
						begin.set(Calendar.YEAR, jaar);
						if (maand == 12) { //bij december moet er gecheckt worden tot 1 januari van het volgende jaar
							jaar++;
							maand = 0;
						}
						eind.set(Calendar.MONTH, maand);
						eind.set(Calendar.YEAR, jaar);
					}
				} catch (NumberFormatException nfe) {
				}
			} 
		}
		setVeilingen(data.getInkomstenOverzicht(begin, eind));
		return SUCCESS;
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
