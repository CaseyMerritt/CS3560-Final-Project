import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		return "Director: " + super.getName() +  "\n\tNationality: " + nationality +
				"\n\tStyle: " + style + "\n";
	}
	
	public static List<Director> findBy(String name, String nationality, String style) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Director> query = cb.createQuery(Director.class);
		Root<Director> root = query.from(Director.class);
		query = query.select(root);
		
		Predicate where = null;
		
		if (name != null) {
			where = cb.like(root.get("name"), "%" + name + "%");
		}
		
		if (nationality != null) {
			if (where == null)
				where = cb.like(root.get("nationality"), "%" + nationality + "%");
			else
				where = cb.and(where, cb.like(root.get("nationality"), "%" + nationality + "%"));
		}
		
		if (style != null) {
			if (where == null)
				where = cb.like(root.get("style"), "%" + style + "%");
			else
				where = cb.and(where, cb.like(root.get("style"), "%" + style + "%"));
		}
		
		if (where != null)
			query = query.where(where);
		
		List<Director> directors = session.createQuery(query).getResultList();
		
		session.getTransaction().commit();
		
		return directors;
	}
	
}
