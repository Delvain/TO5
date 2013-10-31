package plarktmaatsActions.gebruikers;

import com.opensymphony.xwork2.ActionSupport;

public class VoegBodToe extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String id;
	private String credits;

	public String execute() {
		return SUCCESS;
	}
	
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
