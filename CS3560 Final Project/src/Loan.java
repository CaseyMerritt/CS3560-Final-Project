import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan
{
	private int number; // is this the id for the loan?
	private Date loanDate; // date loan was created
	private Date dueDate;
	private Date returnDate; // actual date returned
	private String course;
	private double paidAmount;
	private Student student;
	private Item item;
	
	// constructor
	public Loan(int number, Date loanDate, Date dueDate, String course, Student student, Item item)
	{
		// FIXME object model has numberDays parameter, how does that fit here?
		this.number = number;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
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
		long days = 0;
		try {
			LocalDate startDate = start.toLocalDate();
			LocalDate endDate = end.toLocalDate();
			days = ChronoUnit.DAYS.between(startDate,endDate);
		} catch (Exception e) {
			// TODO: handle exception, might happen if returnDate has not be initialized
		}
		return days;
	}
	
	// TODO implement findBy() here
}
