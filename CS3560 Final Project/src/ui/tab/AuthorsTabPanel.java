package ui.tab;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Author;
import ui.AuthorWindow;
import ui.table.AuthorTableModel;

public class AuthorsTabPanel extends TabPanel<Author> {
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
        	handleEdit();
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

	private void handleEdit() {
		int selectedRowIndex = getSelectedRow();
		
		if (selectedRowIndex < 0) return;
		
		Author author = (Author) model.getRow(selectedRowIndex);
		
		new AuthorWindow(author);
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
			try {
				author.delete();
			} catch (IllegalStateException e) {
				JOptionPane.showMessageDialog(null, "Unable to delete author!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
			}
		    
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
			createErrorMessage("Invalid name!");
			return;
		}
		
		if (nationality.isBlank()) {
			createErrorMessage("Invalid nationality!");
			return;
		}
		
		if (subject.isBlank()) {
			createErrorMessage("Invalid subject!");
			return;
		}
		
		Author author = new Author(name, nationality, subject);
		
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
