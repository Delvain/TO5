package biebActions.member;

import biebDomain.Book;
import biebDomain.User;
import biebService.IBiebService;
import biebService.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

public class ChangePassword extends ActionSupport {
	private IBiebService ibs = ServiceProvider.getBiebService();
	private String username = "";
	private String nieuwePassword ="";
	private String oudePassword = "";

	
	public String execute() {
		ibs.changePassword(username, nieuwePassword);
		return ActionSupport.SUCCESS;
	}
	
	public void validate() {
		User user = ibs.getUserByUsername(username);
		if (user.getPassword().equals(oudePassword)) {
			addFieldError("bookId", "boekId is onbekend");
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String us) {
		username = us;
	}
	
	public String getNieuwePassword() {
		return nieuwePassword;
	}
	
	public void setNieuwePassword(String pass) {
		nieuwePassword = pass;
	}
	
	public String getOudePassword() {
		return oudePassword;
	}
	
	public void setOudePassword(String pass) {
		oudePassword = pass;
	}
	
	
}
