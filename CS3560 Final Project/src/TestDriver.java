// use this class to test attributes and functions during development
import java.sql.Date;
public class TestDriver
{
	public static void main(String args[])
	{
		
		new ApplicationWindow();
		
		Date date1 = new Date(2019-1900,10,15);
		Author author = new Author("You Suck At Cooking", "American", "cooking");
		Book book = new Book("You Suck At Cooking", "A comedic cook book for beginners", "C120",
				 2.25, 224, "Clarkson Potter", date1, author);
		Date loanDate1 = new Date(2023-1900,1,5);
		Date dueDate1 = new Date(2023-1900,1,25);
		
		Date date2 = new Date(2023-1900,5,5);
		Director director = new Director("James Gunn", "American", "Action, Horror-Comedy");
		Film film = new Film("Guardians of the Galaxy Vol. 3", "The third installment of the franchise",
							 "H331", 15.5, 149, date2, director);
		Date loanDate2 = new Date(2023-1900,5,5);
		Date dueDate2 = new Date(2023-1900,5,10);
		
		Student me = new Student("John", 123456789);
		
		Loan loan1 = new Loan(1, loanDate1, dueDate1, "Culinary", me, book);
		Loan loan2 = new Loan(2, loanDate2, dueDate2, "Entertainment", me, film);
		
		System.out.println(loan1);
		System.out.println(loan2);		
	}
}