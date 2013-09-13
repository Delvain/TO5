package plarktmaatsActions.gebruikers;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import plarktmaatsAware.UserAware;
import plarktmaatsDomein.Persoon;


import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Logout extends ActionSupport implements UserAware, SessionAware {
	
	private Persoon user;
	
	@SuppressWarnings("rawtypes")
	private SessionMap session;

	public String execute(){
		session.invalidate(); 
		
		return ActionSupport.SUCCESS;
	}

	public Persoon getUser() {
		return user;
	}
	
	public void setUser(Persoon user) {
		this.user = user;
	}

	@SuppressWarnings("rawtypes")
	public void setSession(Map session) {
		this.session = (SessionMap) session;
	}
}
