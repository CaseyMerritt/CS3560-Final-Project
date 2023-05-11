package model;
import java.util.Date;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

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
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Entity
@Table(name = "loans", schema = "library")
public class Loan implements CRUDOperations
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
	private double paidAmount;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	private Student student;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_code")
	private Item item;
	
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
		return paidAmount;
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
			   "\nLoan date: " + loanDate + "Due date: " + dueDate;
	}
	
	public double calculateEstimatedPrice()
	{
		double price = 0.0;
		/* TODO
		 * price = item.dailyPrice * number of days between dueDate and loanDate
		 */
		return price;
	}
	
	public double calculateFines()
	{
		double fines = 0.0;
		/* TODO
		 * fines = item.dailyPrice * number of days between dueDate and returnDate
		 * fines += fines * 0.1
		 */
		return fines;
	}
	
	public double calculatePrice()
	{
		double price = 0.0;
		/* TODO
		 * if returnDate is past dueDate
		 * 		price = calculateEstimatedPrice() + calculateFines()
		 * else
		 * 		price = item.dailyPrice * number of days between loanDate and returnDate
		 */
		return price;
	}

	/*
	 * Dates stored in instance and database using java.sql.Date class
	 * Converted into java.time.LocalDate class so they can be operated on
	 */
	public long calculateDaysBetweenDates(Date start, Date end){
		long days = ChronoUnit.DAYS.between(start.toInstant(),end.toInstant());

		return days;
	}
	
	public static List<Loan> findBy(LoanQuery loanQuery) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Loan> query = cb.createQuery(Loan.class);
		Root<Loan> root = query.from(Loan.class);
		Join<Loan, Student> joinStudent = root.join("student");
		Join<Loan, Item> joinItem = root.join("item");
		
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
			predicates.add(cb.equal(joinStudent.get("bronco_id"), loanQuery.getBroncoId()));
		}
		
		if (loanQuery.getCourse() != null) {
			predicates.add(cb.like(root.get("course"), cb.concat("%", 
					cb.concat(cb.parameter(String.class, "course"), "%")
			)));
		}
		
		if (loanQuery.isOnlyOverdue()) {
			predicates.add(cb.lessThan(root.get("due_date"), new Date()));
		}
		
		if (loanQuery.getDueAfter() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("due_date"), loanQuery.getDueAfter()));
		}
		
		if (loanQuery.getDueBefore() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("due_date"), loanQuery.getDueBefore()));
		}
		
		if (loanQuery.getLoanedAfter() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("loan_date"), loanQuery.getLoanedAfter()));
		}
		
		if (loanQuery.getLoanedBefore() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("loan_date"), loanQuery.getLoanedBefore()));
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
		
		session.beginTransaction();
		session.delete(this);
		session.getTransaction().commit();
	}

	public boolean isOverdue() {
		return dueDate.compareTo(new Date()) < 0;
	}
	
}
