package biebActions.member;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import biebAware.UserAware;
import biebDomain.User;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Logout extends ActionSupport implements UserAware, SessionAware {
	
	private User user;
	
	@SuppressWarnings("rawtypes")
	private SessionMap session;

	public String execute(){
		session.invalidate(); 
		
		return ActionSupport.SUCCESS;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@SuppressWarnings("rawtypes")
	public void setSession(Map session) {
		this.session = (SessionMap) session;
	}
}
