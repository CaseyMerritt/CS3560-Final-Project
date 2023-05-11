import ui.ApplicationWindow;

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
		System.out.println("------------");
		System.out.println(loan2);
		System.out.println("------------");

		// Testing loan calculation
		loan1.setReturnDate(new Date(2023-1900,1,28));
		System.out.println(loan1);
		System.out.println("Estimated Price: $"+loan1.calculateEstimatedPrice());
		System.out.println("Total Price: $"+loan1.calculatePrice());

		System.out.println("------------");

		loan2.setReturnDate(new Date(2023-1900,5,8));
		System.out.println(loan2);
		System.out.println("Estimated Price: $"+loan2.calculateEstimatedPrice());
		System.out.println("Total Price: $"+loan2.calculatePrice());
    
//		Date date1 = new Date("October", 15, 2019);
//		Author author = new Author("You Suck At Cooking", "American", "cooking");
//		Book book = new Book("You Suck At Cooking", "A comedic cook book for beginners", "C120",
//				 2.25, 224, "Clarkson Potter", date1, author);
//		Date loanDate1 = new Date("January", 5, 2023);
//		Date dueDate1 = new Date("January", 25, 2023);
//		
//		Date date2 = new Date("May", 5, 2023);
//		Director director = new Director("James Gunn", "American", "Action, Horror-Comedy");
//		Film film = new Film("Guardians of the Galaxy Vol. 3", "The third installment of the franchise",
//							 "H331", 15.5, 149, date2, director);
//		Date loanDate2 = new Date("May", 5, 2023);
//		Date dueDate2 = new Date("May", 10, 2023);
//		
//		Student me = new Student("John", 123456789);
		
//		Loan loan1 = new Loan(1, loanDate1, dueDate1, "Culinary", me, book);
//		Loan loan2 = new Loan(2, loanDate2, dueDate2, "Entertainment", me, film);
//		
//		System.out.println(loan1);
//		System.out.println(loan2);		
	}
}