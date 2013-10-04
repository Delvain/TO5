package plarktmaatsActions.visitor;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDomein.Persoon;
import plarktmaatsDomein.Beheerder;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Login extends ActionSupport implements SessionAware {
	
	PersoonDAOImpl database = new PersoonDAOImpl();
	private Persoon user;
	@SuppressWarnings("rawtypes")
	private Map session;
	private String username;
	private String password;

	@SuppressWarnings("unchecked")
	public String execute(){
		session.put("user", user);
		try {
			Beheerder beheerder = (Beheerder) user;
			return "managermenu";
		} catch(ClassCastException ce) {
			ce.printStackTrace();
		}
		
//		if (user.getFunctie().equals("Beheerder"))
//			return "managermenu";
		return SUCCESS;
	}

	public void validate(){
		username = username.trim().toLowerCase();
		user = database.read(username);
		
		if(username.length() == 0){			
			addFieldError("username", "Voer uw gebruikersnaam in.");
		}
		if(password.length() == 0){			
			addFieldError("password", "Voer uw wachtwoord in.");
		}
		
		if(user == null || !password.equals(user.getWachtwoord())) {
			addFieldError("username", "Gebruikersnaam of wachtwoord is onjuist");
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@SuppressWarnings("rawtypes")
	public void setSession(Map session) {
		this.session = session;
		
	}
	
	public Persoon getUser() {
		return user;
	}
}
