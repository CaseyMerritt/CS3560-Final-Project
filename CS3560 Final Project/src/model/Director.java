package model;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Date;

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
@Table(name = "directors", schema = "library")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Director extends Person
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nationality")
	private String nationality;
	@Column(name = "style")
	private String style;
	
	public Director() {
		super(null);
	}
	
	// constructor
	public Director(String name, String nationality, String style)
	{
		super(name);
		this.nationality = nationality;
		this.style = style;
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
	
	public void setStyle(String style)
	{
		this.style = style;
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
	
	public String getStyle()
	{
		return style;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		return id + " - " + getName();
	}
	
	public static List<Director> findBy(String name, String nationality, String style) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Director> query = cb.createQuery(Director.class);
		Root<Director> root = query.from(Director.class);
		query = query.select(root);
		query.distinct(true);
		
		List<Predicate> where = new ArrayList<>();
		
		if (name != null) {
			where.add(cb.like(root.get("name"), cb.concat("%", cb.concat(cb.parameter(String.class, "name"), "%"))));
		}
		
		if (nationality != null) {
			where.add(cb.like(root.get("nationality"), cb.concat("%", cb.concat(cb.parameter(String.class, "nationality"), "%"))));
		}
		
		if (style != null) {
			where.add(cb.like(root.get("style"), cb.concat("%", cb.concat(cb.parameter(String.class, "style"), "%"))));
		}
		
		if (where.size() > 0)
			query = query.where(cb.and(where.toArray(new Predicate[0])));
		
		TypedQuery<Director> typedQuery = session.createQuery(query);
		
		if (name != null) {
			typedQuery.setParameter("name", name);
		}
		
		if (nationality != null) {
			typedQuery.setParameter("nationality", nationality);
		}
		
		if (style != null) {
			typedQuery.setParameter("style", style);
		}
		
		List<Director> directors = typedQuery.getResultList();
		
		session.getTransaction().commit();
		
		return directors;
	}
	
}
