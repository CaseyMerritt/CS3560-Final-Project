package ui.table;

import model.Author;

public class AuthorTableModel extends EntityTableModel<Author> {

	private static final String[] COLUMNS = {"ID", "Name", "Nationality", "Subject"};
	
	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= items.size())
			throw new IndexOutOfBoundsException("Row index out of bounds: " + rowIndex);
		
		Author author = items.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return author.getID();
		case 1:
			return author.getName();
		case 2:
			return author.getNationality();
		case 3:
			return author.getSubject();
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
