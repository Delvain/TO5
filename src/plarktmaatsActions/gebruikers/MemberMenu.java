package plarktmaatsActions.gebruikers;

import plarktmaatsAware.UserAware;
import plarktmaatsDomein.Persoon;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MemberMenu extends ActionSupport implements UserAware {
	
	private Persoon user;

	public String execute(){
		return SUCCESS;
	}

	public Persoon getUser() {
		return user;
	}

	public void setUser(Persoon user) {
		this.user = user;
	}
}
