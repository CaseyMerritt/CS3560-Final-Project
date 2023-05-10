import java.sql.Date;
public class Book extends Item
{
	private int pages;
	private String publisher;
	private Date publicationDate;
	private Author author;
	
	// constructor
	public Book(String title, String description, String location, double dailyPrice,
				int pages, String publisher, Date publicationDate, Author author)
	{
		super(title, description, location, dailyPrice);
		this.pages = pages;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.author = author;
	}

	// setters
	public void setPages(int pages)
	{
		this.pages = pages;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public void setPublicationDate(Date publicationDate)
	{
		this.publicationDate = publicationDate;
	}
	
	public void setAuthor(Author author)
	{
		this.author = author;
	}

	// getters
	public int getPages()
	{
		return pages;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public Date getPublicationDate()
	{
		return publicationDate;
	}

	public Author getAuthor()
	{
		return author;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		return author + "Page count: " + pages + "\nPublisher: " + publisher +
			   "\nPublication Date: " + publicationDate + super.toString();
	}
	
	// TODO implement findBy() here
}
