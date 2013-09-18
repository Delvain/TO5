package plarktmaatsActions.visitor;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CreateAccount extends ActionSupport {
	
	private String email;
	private String voornaam;
	private String achternaam;
	private String password;
	private String password2;

	public String execute(){
		//ibs.addMember(username,password);
		
		return SUCCESS;
	}

	public void validate(){
		email = email.trim();
		voornaam = voornaam.trim();
		achternaam = achternaam.trim();
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
		
		if (password.length() == 0 ) {			
			addFieldError( "password", "wachtwoord is verplicht");
		}
		
		if (!password.equals(password2)) {
			addFieldError( "password2", "wachtwoorden komen niet overeen");
		}
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getVoornaam() {
		return voornaam;
	}
	
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	
	public String getAchternaam() {
		return achternaam;
	}
	               
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

//	public String getPassword() {
//		return password;
//	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
}
