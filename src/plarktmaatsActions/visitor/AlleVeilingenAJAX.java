package plarktmaatsActions.visitor;

import plarktmaatsDAO.VeilingDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

public class AlleVeilingenAJAX extends ActionSupport {
	private String prijs = "Error";
	private String id = "";

	public String execute(){
		id = id.trim();
		if(id.length() < 0)
			return SUCCESS;
		
		VeilingDAOImpl vDI = new VeilingDAOImpl();
		try {
			prijs = vDI.readBedrag(Integer.parseInt(id))+"";
		} catch(Exception e) {
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
}
