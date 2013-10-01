package plarktmaatsActions.visitor;

import java.util.Collection;

import plarktmaatsDAO.VeilingDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

public class AlleVeilingen extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Collection items;

	public String execute(){
		VeilingDAOImpl dAI = new VeilingDAOImpl();
		
		//VeiligDAOImp moet select all krijgen
		
		return SUCCESS;
	}
	
	public Collection getItems() {
		return items;
	}
}
