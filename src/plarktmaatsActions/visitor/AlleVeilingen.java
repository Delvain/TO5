package plarktmaatsActions.visitor;

import java.util.ArrayList;
import java.util.Calendar;

import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Veiling;

import com.opensymphony.xwork2.ActionSupport;

public class AlleVeilingen extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private ArrayList<Veiling> items = new ArrayList<Veiling>();

	public String execute(){
		VeilingDAOImpl dAI = new VeilingDAOImpl();
		
		try {
			items = dAI.getAll();
		} catch(NullPointerException e) {}
		
		ArrayList<Veiling> tempGesloten = new ArrayList<Veiling>(); //Lijst van gesloten veilingen
		ArrayList<Veiling> tempOpen		= new ArrayList<Veiling>();// Lijst van open veilingen
		
		for(Veiling v : items) {
			Calendar nu = Calendar.getInstance();
			if(nu.getTime().getTime() < v.getEindTijd().getTime().getTime())
				tempOpen.add(v); //open toevoegen aan tempOpen
			else 
				tempGesloten.add(v); //Gesloten toevoegen aan tempGesloten
		}
		
		items = new ArrayList<Veiling>(); //items leegmaken
		
		//Eerst alle open veilingen, dan de gesloten:
		for(Veiling v : tempOpen) {
			items.add(v); 
		}
		for(Veiling v : tempGesloten) {
			items.add(v);
		}
		
		return SUCCESS;
	}
	
	public ArrayList<Veiling> getItems() {
		return items;
	}
}
