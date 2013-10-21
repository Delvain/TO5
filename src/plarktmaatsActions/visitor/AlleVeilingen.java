package plarktmaatsActions.visitor;

import java.util.ArrayList;

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
		} catch(Exception e) {}
		
		return SUCCESS;
	}
	
	public ArrayList<Veiling> getItems() {
		return items;
	}
}
