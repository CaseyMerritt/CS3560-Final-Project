package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import database.HibernateSessionFactory;

@Entity
@Table(name = "books", schema = "library")
public class Book extends Item
{
	@Column(name = "pages")
	private Integer pages;
	@Column(name = "publisher")
	private String publisher;
	@Column(name = "publication_date")
	private Date publicationDate;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "books_authors",
			schema = "library",
			joinColumns = @JoinColumn(name = "book_code"),
			inverseJoinColumns = @JoinColumn(name = "author_id")
	)
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

	public void setPublicationDate(java.util.Date publicationDate)
	{
		this.publicationDate = new Date(publicationDate.getTime());
	}
	
	public void setAuthors(List<Author> a) {
		authors = a;
	}

	// getters
	public int getPages()
	{
		return pages == null ? 0 : pages;
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
		return authors;
	}
	
	@Override
	public void delete() {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		this.authors.clear();
		try {
			session.update(this);
			session.delete(this);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new IllegalStateException("Unable to delete");
		}
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
		query.distinct(true);
		
		Join<Book, Author> joined = (Join<Book, Author>) root.<Book, Author>fetch("authors", JoinType.LEFT);
		
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (bookQuery.getCode() != null) {
			predicates.add(cb.equal(root.get("code"), bookQuery.getCode()));
		}
		
		
		
		if (bookQuery.getTitle() != null) {
			predicates.add(cb.like(root.get("title"), cb.concat("%", 
					cb.concat(cb.parameter(String.class, "title"), "%")
			)));
		}
		
		if (bookQuery.getLocation() != null) {
			predicates.add(cb.like(root.get("location"), cb.concat("%", 
					cb.concat(cb.parameter(String.class, "location"), "%")
			)));
		}
		
		if (bookQuery.getMaxDailyPrice() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("dailyPrice"), bookQuery.getMaxDailyPrice()));
		}
		
		if (bookQuery.getMinDailyPrice() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("dailyPrice"), bookQuery.getMinDailyPrice()));
		}
		
		if (bookQuery.isOnlyAvailable()) {
			predicates.add(cb.equal(root.get("available"), true));
		}
		
		if (bookQuery.getMaxPages() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("pages"), bookQuery.getMaxPages()));
		}
		
		if (bookQuery.getMinPages() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("pages"), bookQuery.getMinPages()));
		}
		
		if (bookQuery.getPublishedAfter() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("publicationDate"), bookQuery.getPublishedAfter()));
		}
		
		if (bookQuery.getPublishedBefore() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("publicationDate"), bookQuery.getPublishedBefore()));
		}
		
		if (bookQuery.getPublisher() != null) {
			predicates.add(cb.like(root.get("publisher"), cb.concat("%", 
					cb.concat(cb.parameter(String.class, "publisher"), "%")
			)));
		}
		
		if (bookQuery.getAuthorName() != null) {
			predicates.add(cb.like(joined.get("name"), cb.concat("%", 
					cb.concat(cb.parameter(String.class, "authorName"), "%")
			)));
		}
		
		if (predicates.size() > 0) 
			query = query.where(cb.and(predicates.toArray(new Predicate[0])));
		
		TypedQuery<Book> typedQuery = session.createQuery(query);
		
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
		
		List<Book> books = typedQuery.getResultList();
		
		session.getTransaction().commit();
		
		return books;
	}
	
	
}
