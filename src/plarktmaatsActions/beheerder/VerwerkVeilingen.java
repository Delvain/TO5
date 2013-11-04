package plarktmaatsActions.beheerder;

import plarktmaatsDAO.VeilingDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

public class VerwerkVeilingen extends ActionSupport {
	String datum;
	
	public String execute() {
		VeilingDAOImpl data = new VeilingDAOImpl();
		datum = data.verwerkVeilingen();
		return SUCCESS;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}
}
