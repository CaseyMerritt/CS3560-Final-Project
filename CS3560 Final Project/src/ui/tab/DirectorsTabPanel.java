package ui.tab;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Director;
import ui.DirectorWindow;
import ui.table.DirectorTableModel;

public class DirectorsTabPanel extends TabPanel {
    private JTextField nameField;
    private JTextField nationalityField;
    private JTextField styleField;

    public DirectorsTabPanel() {
    	super(1, 3, new DirectorTableModel());

        // Create input fields.
        nameField = new JTextField(20);
        nationalityField = new JTextField(20);
        styleField = new JTextField(20);

        addField(nameField, "Name");
        addField(nationalityField, "Nationality");
        addField(styleField, "Style");

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
		
		Director director = (Director) model.getRow(selectedRowIndex);
		
		new DirectorWindow(director);
	}

	private void handleDelete() {
		int selectedRowIndex = getSelectedRow();
		
		if (selectedRowIndex < 0) return;
		
		Director director = (Director) model.getRow(selectedRowIndex);

		// confirm deletion
		int option = JOptionPane.showConfirmDialog(this, 
		        "Are you sure you want to delete the director " + director.getName(), 
		        "Confirm Delete", JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(option == JOptionPane.YES_OPTION){
			// TODO handle exceptions
		    director.delete();
		    model.removeRow(selectedRowIndex);
			
		    // Display confirmation of deletion
		    JOptionPane.showMessageDialog(null, "Director deleted");
		}
	}

	private void handleAddNew() {
		String name = nameField.getText().trim();
		String nationality = nationalityField.getText().trim();
		String style = styleField.getText().trim();
		
		if (name.isBlank()) {
			createErrorMessage("Invalid name!");
			return;
		}
		
		if (nationality.isBlank()) {
			createErrorMessage("Invalid nationality!");
			return;
		}
		
		if (style.isBlank()) {
			createErrorMessage("Invalid style!");
			return;
		}
		
		Director director = new Director(name, nationality, style);
		
		// TODO handle exceptions
		director.create();
		
		JOptionPane.showMessageDialog(this, "Director added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	private void handleReset() {
		// Handle Reset.
		nameField.setText("");
		nationalityField.setText("");
		styleField.setText("");
	}

	private void handleSearch() {
		String name = nameField.getText().trim();
		String nationality = nationalityField.getText().trim();
		String style = styleField.getText().trim();
		
		List<Director> directors = Director.findBy(name.isBlank() ? null : name, 
				nationality.isBlank() ? null : nationality,
			    style.isBlank() ? null : style);
		
		model.setRows(directors);
	}
}
