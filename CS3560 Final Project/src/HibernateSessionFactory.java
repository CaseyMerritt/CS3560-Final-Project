import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration()
								 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Person.class)
				                 .addAnnotatedClass(Author.class)
				                 .addAnnotatedClass(Director.class)
				                 .addAnnotatedClass(Student.class)
				                 .addAnnotatedClass(Item.class)
				                 .addAnnotatedClass(Book.class)
				                 .buildSessionFactory();
		}
		
		return sessionFactory;
	}
	
}
