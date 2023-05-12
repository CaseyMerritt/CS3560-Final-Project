package model;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import database.HibernateSessionFactory;

@MappedSuperclass
public abstract class Person implements CRUDOperations
{
	@Column(name = "name")
	private String name;
	
	// constructor
	public Person(String name)
	{
		this.name = name;
	}
	
	// setters
	public void setName(String name)
	{
		this.name = name;
	}
	
	// getters
	public String getName()
	{
		return name;
	}

	@Override
	public void create() {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}

	@Override
	public void update() {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.update(this);
		session.getTransaction().commit();
	}

	@Override
	public void delete() {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.delete(this);
		session.getTransaction().commit();
	}
}
