public class Student extends Person
{
	private int broncoID;
	
	// constructor
	public Student(String name, int broncoID)
	{
		super(name);
		this.broncoID = broncoID;
	}
	
	// setter
	public void setBroncoID(int broncoID)
	{
		this.broncoID = broncoID;
	}
	
	// getter
	public int getBroncoID()
	{
		return broncoID;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		return "Student: " + super.getName() + "\n\tBronco ID: " + broncoID + "\n";
	}
	
	// TODO implement hasOverdueItems() and findBy() here
}
