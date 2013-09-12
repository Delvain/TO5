package biebActions.visitor;

import java.util.ArrayList;
import java.util.List;

import biebDomain.Book;
import biebService.IBiebService;
import biebService.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BookList extends ActionSupport {

	private IBiebService ibs = ServiceProvider.getBiebService();
	private List<Book> books = new ArrayList<Book>();
	
	public String execute() {
		books = ibs.getBooks();
		
		return ActionSupport.SUCCESS;
	}

	public List<Book> getBooks() {
		return books;
	}
}
