package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	private List<Loan> loans;
	
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
		return broncoId + " - " + super.getName();
	}
	
	public int getNumberLoansOverdue() {
		int loansOverdue = 0;
		
		for (Loan loan : loans) {
			if (loan.isOverdue()) loansOverdue++;
		}
		
		return loansOverdue;
	}
	
	public int getNumberLoans() {
		int numberActiveLoans = 0;
		
		for (Loan loan : loans) {
			if (loan.getReturnDate() == null) numberActiveLoans++;
		}
		
		return numberActiveLoans;
	}

	public List<Loan> getLoans(){
		return loans;
	}
	
	public double calculateBalance() {
		double totalBalance = 0.0;
		
		for (Loan loan : loans) {
			totalBalance += loan.calculatePrice() - loan.getPaidAmount();
		}
		
		return totalBalance < 0 ? 0 : totalBalance;
	}
	
	public double calculateBalance(int year) {
		double totalBalance = 0.0;
		Calendar cal = Calendar.getInstance();
		
		for (Loan loan : loans) {
			cal.setTime(loan.getLoanDate());
			if (cal.get(Calendar.YEAR) <= year)
				totalBalance += loan.calculatePrice() - loan.getPaidAmount();
		}
		
		return totalBalance < 0 ? 0 : totalBalance;
	}
	
	public double calculateTotalPaid(int year) {
		double paid = 0.0;
		Calendar cal = Calendar.getInstance();
		
		for (Loan loan : loans) {
			cal.setTime(loan.getLoanDate());
			if (cal.get(Calendar.YEAR) == year)
				paid += loan.getPaidAmount();
		}
		
		return paid;
	}
	
	public static List<Student> findBy(String name, Integer broncoId) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> query = cb.createQuery(Student.class);
		Root<Student> root = query.from(Student.class);
		query.distinct(true);
		
		root.fetch("loans", JoinType.LEFT).fetch("item", JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (name != null) {
			predicates.add(cb.like(root.get("name"), cb.concat("%", cb.concat(cb.parameter(String.class, "name"), "%"))));
		}
		
		if (broncoId != null) {
			predicates.add(cb.equal(root.get("broncoId"), broncoId.toString()));
		}
		
		if (predicates.size() > 0)
			query.where(cb.and(predicates.toArray(new Predicate[0])));
		
		TypedQuery<Student> typedQuery = session.createQuery(query);
		
		if (name != null) {
			typedQuery.setParameter("name", name);
		}
		
		List<Student> students = typedQuery.getResultList();
		
		session.getTransaction().commit();
		
		return students;
	}
}
