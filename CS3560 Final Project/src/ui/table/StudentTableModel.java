package ui.table;

import java.text.NumberFormat;

import model.Student;

public class StudentTableModel extends EntityTableModel<Student> {

	private static final String[] COLUMNS = {"Bronco ID", "Name", "Items Loaned", "Items Overdue", "Balance"};

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= items.size())
			throw new IndexOutOfBoundsException("Row index out of bounds: " + rowIndex);
		
		Student student = items.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return student.getBroncoId() + "";
		case 1:
			return student.getName() + "";
		case 2:
			return student.getNumberLoans();
		case 3:
			return student.getNumberLoansOverdue();
		case 4:
			return NumberFormat.getCurrencyInstance().format(student.calculateBalance());
		default:
			throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
		}
	}
	
	@Override
	public String getColumnName(int column) {
		if (column < 0 || column >= COLUMNS.length)
			throw new IndexOutOfBoundsException("Column index out of bounds: " + column);
		
		return COLUMNS[column];
	}

}
