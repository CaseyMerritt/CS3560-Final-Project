import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@MappedSuperclass
public abstract class Item implements CRUDOperations
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code")
	private int code;
	@Column(name = "available")
	private boolean available;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "location")
	private String location;
	@Column(name = "daily_price")
	private double dailyPrice;
	
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
		return available;
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
		return dailyPrice;
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
	
	// TODO implement makeLoanTo
	public Loan makeLoanTo(Student student) {
		return null;
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
