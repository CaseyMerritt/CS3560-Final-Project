public class Author extends Person
{
	private int id;
	private String nationality;
	private String subject;
	
	// constructor
	public Author(String name, String nationality, String subject)
	{
		super(name);
		this.nationality = nationality;
		this.subject = subject;
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
	
	public void setSubject(String subject)
	{
		this.subject = subject;
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
	
	public String getSubject()
	{
		return subject;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		return "Author: " + super.getName() +  "\n\tNationality: " + nationality +
				"\n\tSubject: " + subject + "\n";
	}
	
	// TODO implement findBy() here
	
}
