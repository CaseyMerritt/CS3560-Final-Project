
public class ItemDriver {
	
	public static void main(String[] args) {
		Author author = new Author("Name", "Nationality", "Subject");
		
		author.create();
		
		Book book = new Book("Title", "Description", "Location", 10.0);
		book.setPages(100);
		book.setPublisher("Publisher");
		book.setPublicationDate(new Date("May", 2, 2023));
		book.addAuthor(author);
		
		book.create();
		
		book.removeAuthor(author);
		book.update();
		
		author.delete();
		book.delete();
	}
	
}
