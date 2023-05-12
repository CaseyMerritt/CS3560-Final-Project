package ui.table;

import model.Director;

public class DirectorTableModel extends EntityTableModel<Director> {

	private static final String[] COLUMNS = {"ID", "Name", "Nationality", "Style"};
	
	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= items.size())
			throw new IndexOutOfBoundsException("Row index out of bounds: " + rowIndex);
		
		Director director = items.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return director.getID();
		case 1:
			return director.getName();
		case 2:
			return director.getNationality();
		case 3:
			return director.getStyle();
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