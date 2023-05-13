package ui.table;

import model.Loan;

import java.text.SimpleDateFormat;

public class LoanTableModel extends EntityTableModel<Loan> {

	private static final String[] COLUMNS = { "Number", "Item Title", "Student Bronco ID", "Student Name", "Course", "Loan Date", "Due Date" };
	
	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Loan l = getRow(rowIndex);
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		
		switch (columnIndex) {
		case 0:
			return l.getNumber() + "";
		case 1:
			return l.getItem().getTitle();
		case 2:
			return l.getStudent().getBroncoId() + "";
		case 3:
			return l.getStudent().getName();
		case 4: 
			return l.getCourse();
		case 5:
			return format.format(l.getLoanDate());
		case 6:
			return format.format(l.getDueDate());
		}
		
		return "";
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}

}
