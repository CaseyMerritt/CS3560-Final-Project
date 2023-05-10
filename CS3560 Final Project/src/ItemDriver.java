import java.util.Date;
import java.util.List;

public class ItemDriver {
	
	public static void main(String[] args) {
		Author author = new Author("Book Author", "Book Author Nationality", "Book Author Subject");
		
		author.create();
		
		Book book = new Book("Book Title", "Book Description", "Book Location", 10.0);
		book.setPages(100);
		book.setPublisher("Book Publisher");
		book.setPublicationDate(new Date());
		book.addAuthor(author);
		
		book.create();
		
		BookQuery query = new BookQuery();
		query.setTitle("Book");
		List<Book> theBooks = Book.findBy(query);
		
		for (Book b : theBooks) {
			System.out.println(b);
		}
		
		book.removeAuthor(author);
		book.update();
		
		author.delete();
		book.delete();
	}
	
}
