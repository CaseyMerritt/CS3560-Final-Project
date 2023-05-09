import java.util.List;

public class PersonDriver {
	public static void main(String[] args) {
		// testing the Student class
		Student student = new Student("John", 123456789);
		
		student.create();
		
		student.setName("Alice");
		student.update();
		
		List<Student> shouldBeAlice = Student.findBy("Alice", null);
		
		for (Student s : shouldBeAlice) {
			System.out.println(s);
		}
		
		List<Student> stillShouldBeAlice = Student.findBy(null, 123456789);
		
		for (Student s : stillShouldBeAlice) {
			System.out.println(s);
		}
		
		stillShouldBeAlice = Student.findBy("Ali", null);
		
		for (Student s : stillShouldBeAlice) {
			System.out.println(s);
		}
		
		student.delete();
		
		
		// testing the Author class
		Author author = new Author("Name", "Nationality", "Subject");
		
		author.create();
		
		author.setName("Robert");
		author.setSubject("Other Subject");
		author.update();
		
		List<Author> shouldBeRobert = Author.findBy("Robert", null, null);
		
		for (Author s : shouldBeRobert) {
			System.out.println(s);
		}
		
		shouldBeRobert = Author.findBy("Rob", null, null);
		
		for (Author s : shouldBeRobert) {
			System.out.println(s);
		}
		
		shouldBeRobert = Author.findBy(null, "Nationality", null);
		
		for (Author s : shouldBeRobert) {
			System.out.println(s);
		}
		
		shouldBeRobert = Author.findBy(null, "Nat", null);
		
		for (Author s : shouldBeRobert) {
			System.out.println(s);
		}
		
		shouldBeRobert = Author.findBy(null, null, "Other Subject");
		
		for (Author s : shouldBeRobert) {
			System.out.println(s);
		}
		
		shouldBeRobert = Author.findBy(null, null, "Other");
		
		for (Author s : shouldBeRobert) {
			System.out.println(s);
		}
		
		author.delete();
		
		
		// test Director class
		Director director = new Director("Mary", "Nationality", "Style");
		
		director.create();
		
		director.setNationality("Other Nationality");
		director.setStyle("Other Style");
		director.update();
		
		List<Director> shouldBeMary = Director.findBy("Mary", null, null);
		
		for (Director s : shouldBeMary) {
			System.out.println(s);
		}
		
		shouldBeMary = Director.findBy("Mar", null, null);
		
		for (Director s : shouldBeMary) {
			System.out.println(s);
		}
		
		shouldBeMary = Director.findBy(null, "Other Nationality", null);
		
		for (Director s : shouldBeMary) {
			System.out.println(s);
		}
		
		shouldBeMary = Director.findBy(null, "Other", null);
		
		for (Director s : shouldBeMary) {
			System.out.println(s);
		}
		
		shouldBeMary = Director.findBy(null, null, "Other Style");
		
		for (Director s : shouldBeMary) {
			System.out.println(s);
		}
		
		shouldBeMary = Director.findBy(null, null, "Other");
		
		for (Director s : shouldBeMary) {
			System.out.println(s);
		}
		
		director.delete();
	}
}
