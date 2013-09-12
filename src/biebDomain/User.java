package biebDomain;

import java.util.ArrayList;

public class User {
	
	private String username;
	private String password;
	private UserRole ur;
	private ArrayList<Book> books =  new ArrayList<Book>(); // geleende of gereserveerde boeken
	
	public User() {
	}
	
	public User(String username, String password, UserRole ur)
	{
		setUsername(username);
		setPassword(password);
		setUr(ur);
	}
	

	public UserRole getUr() {
		return ur;
	}

	public void setUr(UserRole ur) {
		this.ur = ur;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}


}
