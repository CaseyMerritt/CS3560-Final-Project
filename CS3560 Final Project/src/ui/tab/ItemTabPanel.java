package ui.tab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Book;
import model.BookQuery;
import model.Film;
import model.FilmQuery;
import model.Item;
import model.ItemQuery;
import ui.ItemWindow;
import ui.MakeLoanWindow;
import ui.table.ItemTableModel;

public class ItemTabPanel extends TabPanel<Item> {

    private JTextField codeField;
    private JTextField titleField;
    private JTextField locationField;
    private JTextField maxDailyPriceField;
    private JTextField minDailyPriceField;
    private JTextField maxPageLengthField;
    private JTextField minPageLengthField;
    private JTextField publisherField;
    private JTextField authorOrDirectorField;
    private JTextField publishedAfterField;
    private JTextField publishedBeforeField;
    private JCheckBox showAvailable;
    
    private JRadioButton bookButton;
    private JRadioButton filmButton;
    
    public ItemTabPanel() {
    	super(3, 5, new ItemTableModel());
    	
        // Create input fields.
        initialiseFields();
        
        addFields();

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
        	handleSearch();
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            resetFields();
        });

        // Add add button.
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            new ItemWindow(bookButton.isSelected() ? ItemWindow.BOOK : ItemWindow.FILM);
        });
        
        // Add add button.
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
        	int selectedRowIndex = getSelectedRow();
    		
    		if (selectedRowIndex < 0) return;
    		
    		Item item = (Item) model.getRow(selectedRowIndex);;
        	
            new ItemWindow(item);
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
    		handleDelete();
        });     
        
        JButton loanButton = new JButton("Loan");
        loanButton.addActionListener(e -> {
        	int selectedRowIndex = getSelectedRow();
    		
    		if (selectedRowIndex < 0) return;
    		
    		Item item = (Item) model.getRow(selectedRowIndex);;
    		
    		if (item.isAvailable())
    			new MakeLoanWindow(item);
        });

        addButton(searchButton);
        addButton(resetButton);
        addButton(addButton);
        addButton(editButton);
        addButton(deleteButton);
        addButton(loanButton);
    }

	private void handleSearch() {
		ItemQuery itemQuery = bookButton.isSelected() ? new BookQuery() : new FilmQuery();
		
		String code = codeField.getText().trim();
		String location = locationField.getText().trim();
		String maxDailyPrice = maxDailyPriceField.getText().trim();
		String minDailyPrice = minDailyPriceField.getText().trim();
		String title = titleField.getText().trim();
		
		int codeInt = 0;
		try {
			if (!code.isBlank())
				codeInt = Integer.parseInt(code);
		} catch (NumberFormatException e) {
			createErrorMessage("Invalid code!");
			return;
		}
		double maxDailyPriceDouble = 0;
		try {
			if (!maxDailyPrice.isBlank())
				maxDailyPriceDouble = Double.parseDouble(maxDailyPrice);
		} catch (NumberFormatException e) {
			createErrorMessage("Invalid max daily price!");
			return;
		}
		double minDailyPriceDouble = 0;
		try {
			if (!minDailyPrice.isBlank())
				minDailyPriceDouble = Double.parseDouble(minDailyPrice);
		} catch (NumberFormatException e) {
			createErrorMessage("Invalid min daily price!");
			return;
		}
		
		if (!code.isBlank())
			itemQuery.setCode(codeInt);
		
		if (!location.isBlank())
			itemQuery.setLocation(location);
		
		if (!maxDailyPrice.isBlank())
			itemQuery.setMaxDailyPrice(maxDailyPriceDouble);
		
		if (!minDailyPrice.isBlank())
			itemQuery.setMinDailyPrice(minDailyPriceDouble);
		
		if(!title.isBlank())
			itemQuery.setTitle(title);
		
		itemQuery.setOnlyAvailable(showAvailable.isSelected());
		
		if (bookButton.isSelected()) {
			// search for books
			handleSearchBook(itemQuery);
		} else {
			// search for films
			handleSearchFilm(itemQuery);
		}
	}

	private void handleSearchFilm(ItemQuery itemQuery) {
		FilmQuery filmQuery = (FilmQuery) itemQuery;
		
		String directorName = authorOrDirectorField.getText().trim();
		
		String maxLength = maxPageLengthField.getText().trim();
		String minLength = minPageLengthField.getText().trim();
		
		String releasedAfter = publishedAfterField.getText().trim();
		String releasedBefore = publishedBeforeField.getText().trim();
		
		int maxLenInt = 0;
		try {
			if (!maxLength.isBlank()) maxLenInt = Integer.parseInt(maxLength);
		} catch (NumberFormatException ex) {
			createErrorMessage("Invalid max length!");
			return;
		}
		
		int minLenInt = 0;
		try {
			if (!minLength.isBlank()) minLenInt = Integer.parseInt(minLength);
		} catch (NumberFormatException ex) {
			createErrorMessage("Invalid min length!");
			return;
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date afterDate = null;
		try {
			if (!releasedAfter.isBlank()) afterDate = dateFormat.parse(releasedAfter);
		} catch (ParseException e1) {
			createErrorMessage("Invalid released after date!");
			return;
		}
		
		Date beforeDate = null;
		try {
			if (!releasedBefore.isBlank()) beforeDate = dateFormat.parse(releasedBefore);
		} catch (ParseException e1) {
			createErrorMessage("Invalid released before date!");
			return;
		}
		
		if (!maxLength.isBlank())
			filmQuery.setMaxLength(maxLenInt);
		
		if (!minLength.isBlank())
			filmQuery.setMinLength(minLenInt);
		
		if (!releasedAfter.isBlank())
			filmQuery.setReleasedAfter(afterDate);
		
		if (!releasedBefore.isBlank())
			filmQuery.setReleasedBefore(beforeDate);
		
		if (!directorName.isBlank())
			filmQuery.setDirectorName(directorName);
		
		List<Film> films = Film.findBy(filmQuery);
		
		List<Item> items = new ArrayList<>();
		for (Film f : films) {
			items.add(f);
		}
		
		model.setRows(items);
	}

	private void handleSearchBook(ItemQuery itemQuery) {
		BookQuery bookQuery = (BookQuery) itemQuery;
		
		String publisher = publisherField.getText().trim();
		String authorName = authorOrDirectorField.getText().trim();
		
		String maxPages = maxPageLengthField.getText().trim();
		String minPages = minPageLengthField.getText().trim();
		
		String publishedAfter = publishedAfterField.getText().trim();
		String publishedBefore = publishedBeforeField.getText().trim();
		
		int maxPagesInt = 0;
		try {
			if (!maxPages.isBlank()) maxPagesInt = Integer.parseInt(maxPages);
		} catch (NumberFormatException ex) {
			createErrorMessage("Invalid max pages!");
			return;
		}
		
		int minPagesInt = 0;
		try {
			if (!minPages.isBlank()) minPagesInt = Integer.parseInt(minPages);
		} catch (NumberFormatException ex) {
			createErrorMessage("Invalid min pages!");
			return;
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishedAfterDate = null;
		try {
			if (!publishedAfter.isBlank()) publishedAfterDate = dateFormat.parse(publishedAfter);
		} catch (ParseException e1) {
			createErrorMessage("Invalid published after date!");
			return;
		}
		
		Date publishedBeforeDate = null;
		try {
			if (!publishedBefore.isBlank()) publishedBeforeDate = dateFormat.parse(publishedBefore);
		} catch (ParseException e1) {
			createErrorMessage("Invalid published before date!");
			return;
		}
		
		if (!maxPages.isBlank())
			bookQuery.setMaxPages(maxPagesInt);
		
		if (!minPages.isBlank())
			bookQuery.setMinPages(minPagesInt);
		
		if (!publishedAfter.isBlank())
			bookQuery.setPublishedAfter(publishedAfterDate);
		
		if (!publishedBefore.isBlank())
			bookQuery.setPublishedBefore(publishedBeforeDate);
		
		if (!publisher.isBlank())
			bookQuery.setPublisher(publisher);
		
		if (!authorName.isBlank())
			bookQuery.setAuthorName(authorName);
		
		List<Book> books = Book.findBy(bookQuery);
		
		List<Item> items = new ArrayList<>();
		for (Book b : books) {
			items.add(b);
		}
		
		model.setRows(items);
	}

	private void handleDelete() {
		int selectedRowIndex = getSelectedRow();
		
		if (selectedRowIndex < 0) return;
		
		Item item = (Item) model.getRow(selectedRowIndex);;

		// confirm deletion
		int option = JOptionPane.showConfirmDialog(this, 
		"Are you sure you want to delete the item with title: " + item.getTitle(), 
		"Confirm Delete", JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(option == JOptionPane.YES_OPTION){
			// TODO handle exceptions
		    item.delete();
		    model.removeRow(selectedRowIndex);
			
		    // Display confirmation of deletion
		    JOptionPane.showMessageDialog(null, "Item deleted");
		}
	}

	private void resetFields() {
		codeField.setText("");
		titleField.setText("");;
		locationField.setText("");;
		maxDailyPriceField.setText("");;
		minDailyPriceField.setText("");;
		maxPageLengthField.setText("");;
		minPageLengthField.setText("");
		publisherField.setText("");
		authorOrDirectorField.setText("");
		publishedAfterField.setText("");
		publishedBeforeField.setText("");
		showAvailable.setSelected(false);
		bookButton.setSelected(true);
	}

	private void addFields() {
		// Create a panel for the radio buttons.
        JPanel radioPanel = new JPanel();
        radioPanel.add(bookButton);
        radioPanel.add(filmButton);
        
        addField(codeField, "Code");
        addField(maxDailyPriceField, "Max Daily Price");
        addField(radioPanel);
        addField(new JPanel());
        addField(new JPanel());
        addField(titleField, "Title");
        addField(minDailyPriceField, "Min Daily Price");
        addField(maxPageLengthField, "Max Pages/Length");
        addField(publishedAfterField, "Published After");
        addField(publisherField, "Publisher");
        addField(locationField, "Location");
        addField(showAvailable);
        addField(minPageLengthField, "Min Pages/Length");
        addField(publishedBeforeField, "Published Before");
        addField(authorOrDirectorField, "Author/Director Name");
	}

	private void initialiseFields() {
		codeField = new JTextField(20);
        titleField = new JTextField(20);
        locationField = new JTextField(20);
        maxDailyPriceField = new JTextField(20);
        minDailyPriceField = new JTextField(20);
        showAvailable = new JCheckBox("Only Show Avaliable");
        maxPageLengthField = new JTextField(20);
        minPageLengthField  = new JTextField(20);
        publisherField  = new JTextField(20);
        authorOrDirectorField  = new JTextField(20);
        publishedAfterField  = new JTextField(20);
        publishedBeforeField  = new JTextField(20);
        
        bookButton = new JRadioButton("Book");
        filmButton = new JRadioButton("Film");

        // Group the radio buttons to make them mutually exclusive.
        ButtonGroup group = new ButtonGroup();
        group.add(bookButton);
        group.add(filmButton);
        
        bookButton.setSelected(true);
	}
}