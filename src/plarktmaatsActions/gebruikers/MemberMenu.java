package plarktmaatsActions.gebruikers;

import plarktmaatsAware.UserAware;
import plarktmaatsDomein.User;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MemberMenu extends ActionSupport implements UserAware {
	
	private User user;

	public String execute(){
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
