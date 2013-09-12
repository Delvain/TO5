package biebActions.visitor;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import biebDomain.User;
import biebDomain.UserRole;
import biebService.IBiebService;
import biebService.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Login extends ActionSupport implements SessionAware {
	
	private IBiebService ibs = ServiceProvider.getBiebService();
	private User user;
	@SuppressWarnings("rawtypes")
	private Map session;
	private String username;
	private String password;

	@SuppressWarnings("unchecked")
	public String execute(){
		session.put( "user", user );
		if (user.getUr().equals(UserRole.Manager))
			return "managermenu";
		if (user.getUr().equals(UserRole.Coworker))
			return "coworkermenu";
		return SUCCESS;
	}

	public void validate(){
		
		password = password.trim();
		username = username.trim();
		
		if ( username.length() == 0 ){			
			addFieldError( "username", "naam is verplicht");
		}
		if ( password.length() == 0 ){			
			addFieldError( "password", "wachtwoord is verplicht" );
		}
		
	    user = ibs.getUserByUsername(username);
		if ((user == null) || !(user.getPassword().equals(password))){
			addFieldError("username", "naam of wachtwoord is niet juist");
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
//	public String getPassword() {
//		return password;
//	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@SuppressWarnings("rawtypes")
	public void setSession(Map session) {
		this.session = session;
		
	}
	
	public User getUser() {
		return user;
	}

}
