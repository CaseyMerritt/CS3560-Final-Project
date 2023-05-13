package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import database.HibernateSessionFactory;

@Entity
@Table(name = "items", schema = "library")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item implements CRUDOperations
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code")
	private int code;
	@Column(name = "available")
	private Boolean available;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "location")
	private String location;
	@Column(name = "daily_price")
	private Double dailyPrice;
	
	public Item() {
		
	}
	
	// constructor
	public Item(String title, String description, String location, double dailyPrice)
	{
		available = true;
		this.title = title;
		this.description = description;
		this.location = location;
		this.dailyPrice = dailyPrice;
	}
	
	// setters
	public void setCode(int code)
	{
		this.code = code;
	}
	
	public void setAvailable(boolean available)
	{
		this.available = available;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public void setDailyPrice(double dailyPrice)
	{
		this.dailyPrice = dailyPrice;
	}
	
	// getters
	public int getCode()
	{
		return code;
	}
	
	public boolean isAvailable()
	{
		return available == null ? false : available;
	}
	
	public String getTitle()
	{
		return title;
	}

	public String getDescription()
	{
		return description;
	}

	public String getLocation()
	{
		return location;
	}

	public double getDailyPrice()
	{
		return dailyPrice == null ? 0 : dailyPrice;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		String msg, formatPrice;
		if (available)
			msg = "available";
		else
			msg = "not available";
		formatPrice = String.format("%.2f", dailyPrice);
		return "Title: " + title + "\nDescription: " + description + "\nLocation: " +
				location + "\nDaily price: $" + formatPrice + "\nAvailability: " + msg + "\n";
	}
	
	public Loan makeLoanTo(Student student, int numberDays, String course) {
		Loan loan = new Loan(student, this, course, numberDays);
		
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(loan);
		loan.getItem().setAvailable(false);
		session.update(loan.getItem());
		session.getTransaction().commit();
		
		return loan;
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
