public class Film extends Item
{
	private int length; // length is measured in minutes
	private Date releaseDate;
	private Director director;
	
	public Film(String title, String description, String location, double dailyPrice,
				int length, Date releaseDate, Director director)
	{
		super(title, description, location, dailyPrice);
		this.length = length;
		this.releaseDate = releaseDate;
		this.director = director;
	}

	// setters
	public void setLength(int length)
	{
		this.length = length;
	}

	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public void setDirector(Director director)
	{
		this.director = director;
	}
	
	// getters
	public int getLength()
	{
		return length;
	}

	public Date getReleaseDate()
	{
		return releaseDate;
	}

	public Director getDirector()
	{
		return director;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		return director + "Film length: " + length + " minutes\nRelease date: " +
			   releaseDate + super.toString();
	}
	
	// TODO implement findBy() here
}
