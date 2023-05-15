package model;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import database.HibernateSessionFactory;

@Entity
@Table(name = "loans", schema = "library")
public class Loan implements CrudOperations
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "number")
	private int number;
	@Column(name = "loan_date")
	private Date loanDate; // date loan was created
	@Column(name = "due_date")
	private Date dueDate;
	@Column(name = "return_date")
	private Date returnDate; // actual date returned
	@Column(name = "course")
	private String course;
	@Column(name = "paid_amount")
	private Double paidAmount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_code")
	private Item item;
	
	public Loan() {}
	
	// constructor
	public Loan(Student student, Item item, String course, int numberDays)
	{
		this.loanDate = new Date();
		this.dueDate = new Date(loanDate.getTime() + TimeUnit.DAYS.toMillis(numberDays));
		this.course = course;
		this.student = student;
		this.item = item;
		
		// returnDate and paidAmount not initialized since it will be set on the day the item is returned
	}

	// setters
	public void setNumber(int number)
	{
		this.number = number;
	}

	public void setLoanDate(Date loanDate)
	{
		this.loanDate = loanDate;
	}

	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}

	public void setReturnDate(Date returnDate)
	{
		this.returnDate = returnDate;
	}

	public void setCourse(String course)
	{
		this.course = course;
	}

	public void setPaidAmount(double paidAmount)
	{
		this.paidAmount = paidAmount;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	// getters
	public int getNumber()
	{
		return number;
	}

	public Date getLoanDate()
	{
		return loanDate;
	}

	public Date getDueDate()
	{
		return dueDate;
	}

	public Date getReturnDate()
	{
		return returnDate;
	}

	public String getCourse()
	{
		return course;
	}

	public double getPaidAmount()
	{
		return paidAmount == null ? 0 : paidAmount;
	}

	public Student getStudent()
	{
		return student;
	}

	public Item getItem()
	{
		return item;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		return student.toString() + "\n-- Item details --\n" + item.toString() + "Course: " + course +
			   "\nLoan date: " + loanDate + "\nDue date: " + dueDate + "\nReturn date: " + returnDate;
	}
	
	public double calculateEstimatedPrice()
	{
		double price = item.getDailyPrice() * calculateDaysBetweenDates(loanDate, dueDate);
		return price;
	}
	
	public double calculateFines()
	{
		if (new Date().compareTo(dueDate) <= 0) return 0.0;
		
		Date endDate = returnDate == null ? new Date() : returnDate;
		
		double fines = (item.getDailyPrice() * calculateDaysBetweenDates(dueDate, endDate)) * 0.1;

		return fines;
	}
	
	public double calculatePrice()
	{	
		Date endDate = returnDate == null ? new Date() : returnDate;
		
		double price = item.getDailyPrice() * calculateDaysBetweenDates(loanDate, endDate) + calculateFines();

		return price;
	}

	/*
	 * Dates stored in instance and database using java.sql.Date class
	 * Converted into java.time.LocalDate class so DAYS.between() can be used
	 */
	private long calculateDaysBetweenDates(Date start, Date end){
		long days = ChronoUnit.DAYS.between(start.toInstant(),end.toInstant());
    
		return days;
	}
	
	public void returnItem(Date returnDate) {
		this.returnDate = returnDate;
		
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		try {
			this.item.setAvailable(true);
			session.update(this.item);
			session.update(this);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new IllegalStateException("Cannot return item!");
		}
	}
	
	public static List<Loan> findBy(LoanQuery loanQuery) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Loan> query = cb.createQuery(Loan.class);
		Root<Loan> root = query.from(Loan.class);
		Join<Loan, Student> joinStudent = (Join<Loan, Student>) root.<Loan, Student>fetch("student");
		Join<Loan, Item> joinItem = (Join<Loan, Item>) root.<Loan, Item>fetch("item");
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (loanQuery.getNumber() != null) {
			predicates.add(cb.equal(root.get("number"), loanQuery.getNumber()));
		}
		
		if (loanQuery.getItemTitle() != null) {
			predicates.add(cb.like(joinItem.get("title"), cb.concat("%", 
					cb.concat(cb.parameter(String.class, "itemTitle"), "%")
			)));
		}
		
		if (loanQuery.getStudentName() != null) {
			predicates.add(cb.like(joinStudent.get("name"), cb.concat("%", 
					cb.concat(cb.parameter(String.class, "studentName"), "%")
			)));
		}
		
		if (loanQuery.getBroncoId() != null) {
			predicates.add(cb.equal(joinStudent.get("broncoId"), loanQuery.getBroncoId()));
		}
		
		if (loanQuery.getCourse() != null) {
			predicates.add(cb.like(root.get("course"), cb.concat("%", 
					cb.concat(cb.parameter(String.class, "course"), "%")
			)));
		}
		
		if (loanQuery.isOnlyOverdue()) {
			predicates.add(cb.lessThan(root.get("dueDate"), new Date()));
		}
		
		if (loanQuery.getDueAfter() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("dueDate"), loanQuery.getDueAfter()));
		}
		
		if (loanQuery.getDueBefore() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("dueDate"), loanQuery.getDueBefore()));
		}
		
		if (loanQuery.getLoanedAfter() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("loanDate"), loanQuery.getLoanedAfter()));
		}
		
		if (loanQuery.getLoanedBefore() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("loanDate"), loanQuery.getLoanedBefore()));
		}
		
		if (predicates.size() > 0) 
			query = query.where(cb.and(predicates.toArray(new Predicate[0])));
		
		TypedQuery<Loan> typedQuery = session.createQuery(query);
		
		if (loanQuery.getItemTitle() != null) {
			typedQuery.setParameter("itemTitle", loanQuery.getItemTitle());
		}
		
		if (loanQuery.getStudentName() != null) {
			typedQuery.setParameter("studentName", loanQuery.getStudentName());
		}
		
		if (loanQuery.getCourse() != null) {
			typedQuery.setParameter("course", loanQuery.getCourse());
		}
		
		List<Loan> loans = typedQuery.getResultList();
		
		session.getTransaction().commit();
		
		return loans;
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
		
		try {
			session.beginTransaction();
			item.setAvailable(true);
			session.update(item);
			session.delete(this);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new IllegalStateException("Unable to delete loan");
		}
	}

	public boolean isOverdue() {
		return dueDate.compareTo(new Date()) < 0;
	}
	
}
