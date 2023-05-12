package ui.tab;

import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ui.LoanWindow;
import ui.table.ItemTableModel;
import model.Book;
import model.BookQuery;
import model.FilmQuery;
import model.ItemQuery;
import model.Student;
import model.Item;

public class ItemTabPanel extends TabPanel {

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
        	ItemQuery itemQuery = bookButton.isSelected() ? new BookQuery() : new FilmQuery();
        	
        	String code = codeField.getText().trim();
        	String location = locationField.getText().trim();
        	String maxDailyPrice = maxDailyPriceField.getText().trim();
        	String minDailyPrice = minDailyPriceField.getText().trim();
        	String title = titleField.getText().trim();
        	
        	// TODO handle exceptions
        	int codeInt = Integer.parseInt(code);
        	double maxDailyPriceDouble = Double.parseDouble(maxDailyPrice);
        	double minDailyPriceDouble = Double.parseDouble(minDailyPrice);
        	
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
        	
        	List<Item> result = null;
        	
        	if (bookButton.isSelected()) {
        		// search for books
        		BookQuery bookQuery = (BookQuery) itemQuery;
        		
        		String publisher = publisherField.getText().trim();
        		String authorName = authorOrDirectorField.getText().trim();
        		
        		String maxPages = maxPageLengthField.getText().trim();
        		String minPages = minPageLengthField.getText().trim();
        		
        		String publishedAfter = publishedAfterField.getText().trim();
        		String publishedBefore = publishedBeforeField.getText().trim();
        		
        		// TODO handle exceptions
        		int maxPagesInt = 0;
        		try {
        			maxPagesInt = Integer.parseInt(maxPages);
        		} catch (NumberFormatException ex) {
        			
        		}
        		
        		int minPagesInt = 0;
        		try {
        			minPagesInt = Integer.parseInt(minPages);
        		} catch (NumberFormatException ex) {
        			
        		}
        		
        		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        		Date publishedAfterDate = null;
        		try {
					publishedAfterDate = dateFormat.parse(publishedAfter);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		Date publishedBeforeDate = null;
        		try {
					publishedBeforeDate = dateFormat.parse(publishedBefore);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
        		
        		// TODO finish this
        		Book.findBy(bookQuery);
        	} else {
        		// search for films
        	}
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            resetFields();
        });

        // Add add button.
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            // TODO implement adding new item
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
    		handleDelete();
        });     
        
        JButton loanButton = new JButton("Loan");
        loanButton.addActionListener(e -> {
        	// TODO implement loans
        	new LoanWindow();
        });

        addButton(searchButton);
        addButton(resetButton);
        addButton(addButton);
        addButton(deleteButton);
        addButton(loanButton);
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