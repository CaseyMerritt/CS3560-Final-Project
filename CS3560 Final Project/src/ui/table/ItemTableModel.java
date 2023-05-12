package ui.table;

import model.Book;
import model.Item;
import model.Film;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class ItemTableModel extends EntityTableModel<Item> {

	private static final String[] COLUMNS = {"Code", "Title", "Description", "Location", "Daily Price", "Pages/Length", "Release/Publish Date", "Publisher", "Availability"};
	
	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Item item = getRow(rowIndex);
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		
		switch (columnIndex) {
		case 0:
			return item.getCode();
		case 1:
			return item.getTitle();
		case 2:
			return item.getDescription();
		case 3:
			return item.getLocation();
		case 4:
			return NumberFormat.getCurrencyInstance().format(item.getDailyPrice());
		case 8:
			return item.isAvailable() ? "Available" : "Not Available";
		default:
			if (item instanceof Book) {
				Book b = (Book) item;
				
				switch (columnIndex) {
				case 5: 
					return b.getPages();
				case 6:
					if (b.getPublicationDate() == null) return "";
					return format.format(b.getPublicationDate());
				case 7: 
					return b.getPublisher();
				}
			} else {
				Film f = (Film) item;
			}
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}

}
