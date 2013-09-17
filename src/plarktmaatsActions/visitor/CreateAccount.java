package plarktmaatsActions.visitor;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CreateAccount extends ActionSupport {
	
	private String email;
	private String voornaam;
	private String achternaam;
	private String password;

	public String execute(){
		//ibs.addMember(username,password);
		
		return SUCCESS;
	}

	public void validate(){
		email = email.trim();
		password = password.trim();
		
		if (email.length() == 0 ){			
			addFieldError( "email", "email is verplicht");
		}
		
		if (voornaam.length() == 0) {
			addFieldError( "voornaam", "voornaam is veprlicht");
		}
		
		if (achternaam.length() == 0) {
			addFieldError( "achternaam", "achternaam is verplicht");
		}
		
//		else if ( ibs.userExists(username ) ){		
//			addFieldError("username", "gebruiker bestaat al");
//		}
		
		if ( password.length() == 0 ){			
			addFieldError( "password", "wachtwoord is verplicht");
		}
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
//	public String getPassword() {
//		return password;
//	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
