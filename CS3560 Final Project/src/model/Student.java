package model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import database.HibernateSessionFactory;

@Entity
@Table(name = "students", schema = "library")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Student extends Person
{
	@Id
	@Column(name = "bronco_id")
	private int broncoId;
	
	public Student() {
		super(null);
	}
	
	// constructor
	public Student(String name, int broncoId)
	{
		super(name);
		this.broncoId = broncoId;
	}
	
	// setter
	public void setBroncoId(int broncoId)
	{
		this.broncoId = broncoId;
	}
	
	// getter
	public int getBroncoId()
	{
		return broncoId;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		return "Student: " + super.getName() + "\n\tBronco ID: " + broncoId + "\n";
	}
	
	public boolean hasOverdueItems() {
		LoanQuery loanQuery = new LoanQuery();
		loanQuery.setBroncoId(broncoId);
		List<Loan> loans = Loan.findBy(loanQuery);
		for (Loan loan : loans) {
			if (loan.isOverdue()) return true;
		}
		
		return false;
	}
	
	public double calculateBalance() {
		LoanQuery loanQuery = new LoanQuery();
		loanQuery.setBroncoId(broncoId);
		List<Loan> loans = Loan.findBy(loanQuery);
		double totalBalance = 0.0;
		
		for (Loan loan : loans) {
			totalBalance += loan.calculatePrice() - loan.getPaidAmount();
		}
		
		return totalBalance;
	}
	
	public static List<Student> findBy(String name, Integer broncoId) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> query = cb.createQuery(Student.class);
		Root<Student> root = query.from(Student.class);
		query = query.select(root);
		
		Predicate where = null;
		
		if (name != null) {
			where = cb.like(root.get("name"), "%" + name + "%");
		}
		
		if (broncoId != null) {
			if (where == null)
				where = cb.equal(root.get("broncoId"), broncoId.toString());
			else
				where = cb.and(where, cb.equal(root.get("broncoId"), broncoId.toString()));
		}
		
		if (where != null)
			query = query.where(where);
		
		List<Student> students = session.createQuery(query).getResultList();
		
		session.getTransaction().commit();
		
		return students;
	}
	
}
