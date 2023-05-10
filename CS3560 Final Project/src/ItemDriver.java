import java.util.Date;
import java.util.List;

public class ItemDriver {
	
	public static void main(String[] args) {
		Author author = new Author("Name", "Nationality", "Subject");
		
		author.create();
		
		Book book = new Book("Title", "Description", "Location", 10.0);
		book.setPages(100);
		book.setPublisher("Publisher");
		book.setPublicationDate(new Date());
		book.addAuthor(author);
		
		book.create();
		
		BookQuery query = new BookQuery();
		query.setAuthorName("Name");
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
