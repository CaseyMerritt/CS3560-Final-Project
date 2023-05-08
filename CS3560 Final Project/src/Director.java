public class Director extends Person
{
	private int id;
	private String nationality;
	private String style;
	
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
	
	// TODO implement findBy() here
	
}
