/* This class is to mimic the Date type in pgAdmin, It is used in the
 * Book and Film classes. I'm not sure how it will convert over to pgAdmin,
 * so this class is just a placeholder in Eclipse and may alter the contents
 * of the Book and Film classes if we make changes
 */
public class Date
{
	private String month;
	private int day;
	private int year;
	
	public Date(String month, int day, int year)
	{
		this.month = month;
		this.day = day;
		this.year = year;
	}

	// setters
	public void setMonth(String month)
	{
		this.month = month;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	// getters
	public String getMonth()
	{
		return month;
	}

	public int getDay()
	{
		return day;
	}

	public int getYear()
	{
		return year;
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		return month + " " + day + ", " + year + "\n";
	}
}
