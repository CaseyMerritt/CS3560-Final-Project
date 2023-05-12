package ui.table;

import java.util.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public abstract class EntityTableModel<T> extends AbstractTableModel {

	protected List<T> items;
	
	public EntityTableModel() {
		items = new ArrayList<>();
	}
	
	public void setRows(List<T> items) {
		this.items = items;
		this.fireTableDataChanged();
	}
	
	public T getRow(int row) {
		// TODO handle exceptions
		return items.get(row);
	}
	
	public void removeRow(int row) {
		// TODO handle exceptions
		items.remove(row);
		this.fireTableRowsDeleted(row, row);
	}
	
	@Override
	public int getRowCount() {
		return items == null ? 0 : items.size();
	}
	
}
