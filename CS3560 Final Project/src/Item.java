public class Item
{
	private int code;
	private boolean available;
	private String title;
	private String description;
	private String location;
	private double dailyPrice;
	
	// constructor
	public Item(String title, String description, String location, double dailyPrice)
	{
		available = true;
		this.title = title;
		this.description = description;
		this.location = location;
		this.dailyPrice = dailyPrice;
	}
	
	// setters
	public void setCode(int code)
	{
		this.code = code;
	}
	
	public void setAvailable(boolean available)
	{
		this.available = available;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public void setDailyPrice(double dailyPrice)
	{
		this.dailyPrice = dailyPrice;
	}
	
	// getters
	public int getCode()
	{
		return code;
	}
	
	public boolean isAvailable()
	{
		return available;
	}
	
	public String getTitle()
	{
		return title;
	}

	public String getDescription()
	{
		return description;
	}

	public String getLocation()
	{
		return location;
	}

	public double getDailyPrice()
	{
		return dailyPrice;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		String msg, formatPrice;
		if (available)
			msg = "available";
		else
			msg = "not available";
		formatPrice = String.format("%.2f", dailyPrice);
		return "Title: " + title + "\nDescription: " + description + "\nLocation: " +
				location + "\nDaily price: $" + formatPrice + "\nAvailability: " + msg + "\n";
	}
	
	// TODO implement makeLoanTo();
}
