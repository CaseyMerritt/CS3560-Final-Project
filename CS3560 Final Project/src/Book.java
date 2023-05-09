import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Entity
@Table(name = "books", schema = "library")
@Inheritance(strategy = InheritanceType.JOINED)
public class Book extends Item
{
	@Column(name = "pages")
	private int pages;
	@Column(name = "publisher")
	private String publisher;
	@Column(name = "publication_date")
	private Date publicationDate;
	// TODO finish annotations
	@ManyToMany
	@JoinTable(name = "books_authors")
	private List<Author> authors;
	
	public Book() {
		super(null, null, null, 0);
	}
	
	// constructor
	public Book(String title, String description, String location, double dailyPrice)
	{
		super(title, description, location, dailyPrice);
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
	
	public void addAuthor(Author author) {
		authors.add(author);
	}
	
	public void removeAuthor(Author author) {
		authors.remove(author);
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

	public List<Author> getAuthors()
	{
		List<Author> result = new ArrayList<Author>();
		Collections.copy(result, authors);
		return result;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		String result = "Page count: " + pages + "\nPublisher: " + publisher +
				   	    "\nPublication Date: " + publicationDate + super.toString() +
				   	    "\nAuthor(s):";
		
		for (Author author : authors) {
			result += "\n" + author.toString();
		}
		
		return result;
	}
	
	public static List<Book> findBy(BookQuery bookQuery) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Book> query = cb.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		Join<Book, Author> joined = root.join("authors");
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (bookQuery.getCode() != null) {
			predicates.add(cb.equal(joined.get("code"), cb.parameter(String.class, "code")));
		}
		
		if (bookQuery.getTitle() != null) {
			predicates.add(cb.like(joined.get("title"), cb.parameter(String.class, "title")));
		}
		
		if (bookQuery.getLocation() != null) {
			predicates.add(cb.like(joined.get("location"), cb.parameter(String.class, "location")));
		}
		
		if (bookQuery.getMaxDailyPrice() != null) {
			predicates.add(cb.lessThanOrEqualTo(joined.get("daily_price"), bookQuery.getMaxDailyPrice()));
		}
		
		if (bookQuery.getMinDailyPrice() != null) {
			predicates.add(cb.greaterThanOrEqualTo(joined.get("daily_price"), bookQuery.getMinDailyPrice()));
		}
		
		if (bookQuery.isOnlyAvailable()) {
			predicates.add(cb.equal(joined.get("available"), true));
		}
		
		if (bookQuery.getMaxPages() != null) {
			predicates.add(cb.lessThanOrEqualTo(joined.get("pages"), bookQuery.getMaxPages()));
		}
		
		if (bookQuery.getMinPages() != null) {
			predicates.add(cb.greaterThanOrEqualTo(joined.get("pages"), bookQuery.getMinPages()));
		}
		
		// TODO implement logic for published after and published before
		if (bookQuery.getPublishedAfter() != null) {
			predicates.add(cb.greaterThanOrEqualTo(joined.get("publication_date"), bookQuery.getPublishedAfter()));
		}
		
		if (bookQuery.getPublisher() != null) {
			predicates.add(cb.like(joined.get("publisher"), cb.parameter(String.class, "publisher")));
		}
		
		if (bookQuery.getAuthorName() != null) {
			predicates.add(cb.like(joined.get("name"), cb.parameter(String.class, "authorName")));
		}
		
		if (predicates.size() > 0) 
			query = query.where(cb.and(predicates.toArray(new Predicate[0])));
		
		TypedQuery<Book> typedQuery = session.createQuery(query);
		
		if (bookQuery.getCode() != null) {
			typedQuery.setParameter("code", bookQuery.getCode());
		}
		
		if (bookQuery.getTitle() != null) {
			typedQuery.setParameter("title", bookQuery.getTitle());
		}
		
		if (bookQuery.getLocation() != null) {
			typedQuery.setParameter("location", bookQuery.getLocation());
		}
		
		if (bookQuery.getPublisher() != null) {
			typedQuery.setParameter("publisher", bookQuery.getPublisher());
		}
		
		if (bookQuery.getAuthorName() != null) {
			typedQuery.setParameter("authorName", bookQuery.getAuthorName());
		}
		
		List<Book> books = session.createQuery(query).getResultList();
		
		session.getTransaction().commit();
		
		return books;
	}
	
	
}
