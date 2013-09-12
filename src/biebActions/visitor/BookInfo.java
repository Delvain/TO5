package biebActions.visitor;

import plarktmaatsDomein.Book;
import plarktmaatsService.IBiebService;
import plarktmaatsService.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

public class BookInfo extends ActionSupport {
	private IBiebService ibs = ServiceProvider.getBiebService();
	private Book book = new Book();
	private int bookId;
	
	public String execute() {
		book = ibs.getBookById(bookId);
		return ActionSupport.SUCCESS;
	}
	
	public void validate() {
		if (! ibs.bookExists(bookId)) {
			addFieldError("bookId", "boekId is onbekend");
		}
	}
	
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int bi) {
		bookId = bi;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book b) {
		book = b;
	}

}
