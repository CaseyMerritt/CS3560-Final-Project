package model;
import java.util.Date;

public class FilmQuery extends ItemQuery {
	private Integer maxLength;
	private Integer minLength;
	private Date releasedAfter;
	private Date releasedBefore;
	private String directorName;
	
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public Integer getMinLength() {
		return minLength;
	}
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	public Date getReleasedAfter() {
		return releasedAfter;
	}
	public void setReleasedAfter(Date releasedAfter) {
		this.releasedAfter = releasedAfter;
	}
	public Date getReleasedBefore() {
		return releasedBefore;
	}
	public void setReleasedBefore(Date releasedBefore) {
		this.releasedBefore = releasedBefore;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
}
