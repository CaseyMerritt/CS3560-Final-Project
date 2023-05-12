package ui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Author;
import ui.tab.TabPanel;
import ui.table.AuthorTableModel;

public class AuthorsTabPanel extends TabPanel {
    private JTextField nameField;
    private JTextField nationalityField;
    private JTextField subjectField;

    public AuthorsTabPanel() {
    	super(1, 3, new AuthorTableModel());

        // Create input fields.
        nameField = new JTextField(20);
        nationalityField = new JTextField(20);
        subjectField = new JTextField(20);

        addField(nameField, "Name");
        addField(nationalityField, "Nationality");
        addField(subjectField, "Subject");

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
        	handleSearch();
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            handleReset();
        });

        // Add Add button.
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
        	handleAddNew();
        });
        
        // Add edit button.
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
        	// TODO implement editing author
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            handleDelete(); 
        });           

        addButton(searchButton);
        addButton(resetButton);
        addButton(addButton);
        addButton(editButton);
        addButton(deleteButton);
    }

	private void handleDelete() {
		int selectedRowIndex = getSelectedRow();
		
		if (selectedRowIndex < 0) return;
		
		Author author = (Author) model.getRow(selectedRowIndex);

		// confirm deletion
		int option = JOptionPane.showConfirmDialog(this, 
		        "Are you sure you want to delete the author " + author.getName(), 
		        "Confirm Delete", JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(option == JOptionPane.YES_OPTION){
			// TODO handle exceptions
		    author.delete();
		    model.removeRow(selectedRowIndex);
			
		    // Display confirmation of deletion
		    JOptionPane.showMessageDialog(null, "Author deleted");
		}
	}

	private void handleAddNew() {
		String name = nameField.getText().trim();
		String nationality = nationalityField.getText().trim();
		String subject = subjectField.getText().trim();
		
		if (name.isBlank()) {
			JOptionPane.showMessageDialog(this, "Name cannot be blank!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (nationality.isBlank()) {
			nationality = null;
		}
		
		if (subject.isBlank()) {
			subject = null;
		}
		
		Author author = new Author(name, nationality, subject);
		
		// TODO handle exceptions
		author.create();
		
		JOptionPane.showMessageDialog(this, "Author added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	private void handleReset() {
		// Handle Reset.
		nameField.setText("");
		nationalityField.setText("");
		subjectField.setText("");
	}

	private void handleSearch() {
		String name = nameField.getText().trim();
		String nationality = nationalityField.getText().trim();
		String subject = subjectField.getText().trim();
		
		List<Author> authors = Author.findBy(name.isBlank() ? null : name, 
				nationality.isBlank() ? null : nationality,
			    subject.isBlank() ? null : subject);
		
		model.setRows(authors);
	}
}
