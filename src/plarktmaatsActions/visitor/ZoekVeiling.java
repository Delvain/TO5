package plarktmaatsActions.visitor;

import java.util.ArrayList;

import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Veiling;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ZoekVeiling extends ActionSupport {
	private ArrayList<Veiling> zoekResultaten = new ArrayList<Veiling>();
	private String q;
	
	public String execute() {
		VeilingDAOImpl vDI = new VeilingDAOImpl();
		setZoekResultaten((ArrayList<Veiling>) vDI.zoekVeiling("%" + q.toUpperCase() + "%"));
		return SUCCESS;
	}
	
	public String getQ() {
		return q;
	}
	
	public void setQ(String q) {
		this.q = q;
	}

	public ArrayList<Veiling> getZoekResultaten() {
		return zoekResultaten;
	}

	public void setZoekResultaten(ArrayList<Veiling> zoekResultaten) {
		this.zoekResultaten = zoekResultaten;
	}
}
