package model;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import database.HibernateSessionFactory;

@Entity
@Table(name = "authors", schema = "library")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Author extends Person
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "nationality")
	private String nationality;
	@Column(name = "subject")
	private String subject;
	
	public Author() {
		super(null);
	}
	
	// constructor
	public Author(String name, String nationality, String subject)
	{
		super(name);
		this.nationality = nationality;
		this.subject = subject;
	}
	
	// setters
	public void setID(int id)
	{
		this.id = id;
	}
	
	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	// getters
	public int getID()
	{
		return id;
	}
	
	public String getNationality()
	{
		return nationality;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		return id + " - " + getName();
	}
	
	public static List<Author> findBy(String name, String nationality, String subject) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Author> query = cb.createQuery(Author.class);
		Root<Author> root = query.from(Author.class);
		query = query.select(root);
		query.distinct(true);
		
		List<Predicate> where = new ArrayList<>();
		
		if (name != null) {
			where.add(cb.like(root.get("name"), cb.concat("%", cb.concat(cb.parameter(String.class, "name"), "%"))));
		}
		
		if (nationality != null) {
			where.add(cb.like(root.get("nationality"), cb.concat("%", cb.concat(cb.parameter(String.class, "nationality"), "%"))));
		}
		
		if (subject != null) {
			where.add(cb.like(root.get("subject"), cb.concat("%", cb.concat(cb.parameter(String.class, "subject"), "%"))));
		}
		
		if (where.size() > 0)
			query = query.where(cb.and(where.toArray(new Predicate[0])));
		
		TypedQuery<Author> typedQuery = session.createQuery(query);
		
		if (name != null) {
			typedQuery.setParameter("name", name);
		}
		
		if (nationality != null) {
			typedQuery.setParameter("nationality", nationality);
		}
		
		if (subject != null) {
			typedQuery.setParameter("subject", subject);
		}
		
		List<Author> authors = typedQuery.getResultList();
		
		session.getTransaction().commit();
		
		return authors;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return id == other.id;
	}
	
}
