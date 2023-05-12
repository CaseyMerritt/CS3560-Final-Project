package model;
import java.util.Date;

public class BookQuery extends ItemQuery {
	private Integer maxPages;
	private Integer minPages;
	private Date publishedAfter;
	private Date publishedBefore;
	private String publisher;
	private String authorName;
	
	public Integer getMaxPages() {
		return maxPages;
	}
	public void setMaxPages(Integer maxPages) {
		this.maxPages = maxPages;
	}
	public Integer getMinPages() {
		return minPages;
	}
	public void setMinPages(Integer minPages) {
		this.minPages = minPages;
	}
	public Date getPublishedAfter() {
		return publishedAfter;
	}
	public void setPublishedAfter(Date publishedAfter) {
		this.publishedAfter = publishedAfter;
	}
	public Date getPublishedBefore() {
		return publishedBefore;
	}
	public void setPublishedBefore(Date publishedBefore) {
		this.publishedBefore = publishedBefore;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
