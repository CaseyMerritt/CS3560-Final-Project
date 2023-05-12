package ui.table;

public class LoanTableModel extends EntityTableModel {

	private static final String[] COLUMNS = { "Number", "Item Title", "Student Bronco ID", "Student Name", "Course", "Loan Date", "Due Date" };
	
	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}

}
