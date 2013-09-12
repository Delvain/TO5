package biebService;

import java.util.ArrayList;
import java.util.List;

import biebDomain.Book;
import biebDomain.BookStatus;
import biebDomain.User;
import biebDomain.UserRole;

public class BiebService implements IBiebService {

	public ArrayList<Book> books = new ArrayList<Book>();
	public ArrayList<User> users = new ArrayList<User>();

	public BiebService() {
		addBook(3,"Struts 2 in Action", "Donald Brown", "978-1933988078",404);
		addBook(8,"De kracht van Scrum", "Rini van Solingen", "978-9043020473",143);
		addBook(9,"Pro JPA 2", "Mike Keith", "978-1430219569",532);
		addBook(23,"Oracle Database 11g Release 2", "Richard Niemiec","978-0071780261",1168);
		addBook(25,"Algorithms (4th Edition) ", "Robert Sedgewick","978-0321573513",976);
		
		addUser(new User("m1","w",UserRole.Member));
		addUser(new User("m2","w",UserRole.Member));
		addUser(new User("m3","w",UserRole.Member));
		addUser(new User("c1","w",UserRole.Coworker));
		addUser(new User("c2","w",UserRole.Coworker));
		addUser(new User("c3","w",UserRole.Coworker));
		addUser(new User("boss1","w",UserRole.Manager));
		addUser(new User("boss2","w",UserRole.Manager));
		addUser(new User("boss3","w",UserRole.Manager));
	}

	public void addUser(User user) {
		users.add(user);
	}
	
	public void addBook(int bookId, String titel, String auteur, String isbn, int pages) {
		Book boek = new Book(bookId,titel, auteur, isbn,pages);
		books.add(boek);
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public Book getBookById(int id) {
		for (Book book:books)
			if (book.getId() == id)
				return book;
		return null;
	}
	
	public boolean bookExists(int id)
	{
		return getBookById(id) != null;  
	}
	
	public void deleteBook(int BookId) {
		Book book = getBookById(BookId);
		books.remove(book);
	}	

	public void takeBackBook(int BookId) {
		Book book = getBookById(BookId);
		User user = book.getUser();
		user.getBooks().remove(book);
		book.setStatus(BookStatus.AVAILABLE);
		book.setUser(null);
	}	
	
	public void addMember(String username, String password) {
		User user = new User(username,password,UserRole.Member);
		users.add(user);
	}
	
	public void addCoworker(String username, String password) {
		User user = new User(username,password,UserRole.Coworker);
		users.add(user);
	}
	
	public List<User> getUsers() {
		return users;
	}

	public List<User> getMembers() {
		ArrayList<User> members = new ArrayList<User>();
		for (User user:users)
			if (user.getUr().equals(UserRole.Member))
			{
				members.add(user);
			}		
		return members;
	}	
	
	public User getUserByUsername(String username) {
		for (User user:users)
			if (user.getUsername().equals(username))
				return user;
		return null;
	}
	
	public boolean hasBorrowedBooks(String username)
	{
		List<Book> books = getUserByUsername(username).getBooks();
		for (Book book:books)
			if (book.getStatus().equals(BookStatus.BORROWED))
				return true;
		return false;
	}
	
	public void deleteUser(String username) {
		User user = getUserByUsername(username);
		// geef de gereserveerde boeken van user vrij
		List<Book> books = user.getBooks();
		for (Book book:books)
			if (book.getStatus().equals(BookStatus.RESERVED))
			{
				book.setStatus(BookStatus.AVAILABLE);
				book.setUser(null); 
			}
		
		users.remove(user);
	}
	
	public void changePassword(String username,String password)
	{
		User user = getUserByUsername(username);
		user.setPassword(password);
	}
	
	public boolean userExists(String username) {
		return getUserByUsername(username) != null;  
	}
	
	public void reserveBook(String username, int BookId)
	{
		Book book = getBookById(BookId);
		User user = getUserByUsername(username);		
		book.setStatus(BookStatus.RESERVED);
		book.setUser(user);
		user.getBooks().add(book);
	}
	
	public void cancelReservationBook(String username, int BookId)
	{
		Book book = getBookById(BookId);
		User user = getUserByUsername(username);
		if((book.getStatus().equals(BookStatus.RESERVED)) && book.getUser().equals(user))
		{
			book.setStatus(BookStatus.AVAILABLE);
			book.setUser(null);
			user.getBooks().remove(book);
		}
	}	
	
	public void lendBook(String username, int BookId)
	{
		Book book = getBookById(BookId);
		User user = getUserByUsername(username);
		book.setStatus(BookStatus.BORROWED);
		book.setUser(user);
		if (!(user.getBooks().contains(book)))
		   user.getBooks().add(book);
	}
	
	public List<Book> getBooks(String username)
	{
		return getUserByUsername(username).getBooks();
	}
}
