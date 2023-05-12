package model;
import java.util.Date;

public class LoanQuery {
	private Integer number;
	private String itemTitle;
	private Integer broncoId;
	private String studentName;
	private Date loanedAfter;
	private Date loanedBefore;
	private Date dueAfter;
	private Date dueBefore;
	private String course;
	private boolean onlyOverdue;
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String getItemTitle() {
		return itemTitle;
	}
	
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	
	public Integer getBroncoId() {
		return broncoId;
	}
	
	public void setBroncoId(Integer broncoId) {
		this.broncoId = broncoId;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public Date getLoanedAfter() {
		return loanedAfter;
	}
	
	public void setLoanedAfter(Date loanedAfter) {
		this.loanedAfter = loanedAfter;
	}
	
	public Date getLoanedBefore() {
		return loanedBefore;
	}
	
	public void setLoanedBefore(Date loanedBefore) {
		this.loanedBefore = loanedBefore;
	}
	
	public Date getDueAfter() {
		return dueAfter;
	}
	
	public void setDueAfter(Date dueAfter) {
		this.dueAfter = dueAfter;
	}
	
	public Date getDueBefore() {
		return dueBefore;
	}
	
	public void setDueBefore(Date dueBefore) {
		this.dueBefore = dueBefore;
	}
	
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public boolean isOnlyOverdue() {
		return onlyOverdue;
	}
	
	public void setOnlyOverdue(boolean onlyOverdue) {
		this.onlyOverdue = onlyOverdue;
	}
}
