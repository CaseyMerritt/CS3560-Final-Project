package model;

public abstract class ItemQuery {
	private Integer code;
	private String title;
	private String location;
	private Double maxDailyPrice;
	private Double minDailyPrice;
	private boolean onlyAvailable;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Double getMaxDailyPrice() {
		return maxDailyPrice;
	}
	public void setMaxDailyPrice(Double maxDailyPrice) {
		this.maxDailyPrice = maxDailyPrice;
	}
	public Double getMinDailyPrice() {
		return minDailyPrice;
	}
	public void setMinDailyPrice(Double minDailyPrice) {
		this.minDailyPrice = minDailyPrice;
	}
	public boolean isOnlyAvailable() {
		return onlyAvailable;
	}
	public void setOnlyAvailable(boolean onlyAvailable) {
		this.onlyAvailable = onlyAvailable;
	}
	
}
