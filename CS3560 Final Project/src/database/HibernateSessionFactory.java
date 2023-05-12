package database;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Author;
import model.Book;
import model.Director;
import model.Film;
import model.Item;
import model.Loan;
import model.Person;
import model.Student;

public class HibernateSessionFactory {
	
	private static SessionFactory sessionFactory;
	
	private HibernateSessionFactory() {}
	
	public synchronized static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration()
								 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Person.class)
				                 .addAnnotatedClass(Author.class)
				                 .addAnnotatedClass(Director.class)
				                 .addAnnotatedClass(Student.class)
				                 .addAnnotatedClass(Item.class)
				                 .addAnnotatedClass(Book.class)
				                 .addAnnotatedClass(Film.class)
				                 .addAnnotatedClass(Loan.class)
				                 .buildSessionFactory();
		}
		
		return sessionFactory;
	}
	
}
