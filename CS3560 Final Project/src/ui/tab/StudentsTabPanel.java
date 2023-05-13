package ui.tab;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Student;
import ui.StudentWindow;
import ui.table.StudentTableModel;

public class StudentsTabPanel extends TabPanel {
    private JTextField broncoIdField;
    private JTextField nameField;

    public StudentsTabPanel() {
    	super(1, 3, new StudentTableModel());

        // Create input fields.
        nameField = new JTextField(20);
        broncoIdField = new JTextField(20);

        // Create a panel to hold the fields.
        addField(nameField, "Name");
        addField(broncoIdField, "Bronco ID");

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
        	handleSearch();
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            // Handle Reset.
            nameField.setText("");
            broncoIdField.setText("");
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
		
		Student student = (Student) model.getRow(selectedRowIndex);
		
		new StudentWindow(student);
	}

	private void handleDelete() {
		int selectedRowIndex = getSelectedRow();
        
        if (selectedRowIndex < 0) return;
        
        Student student = (Student) model.getRow(selectedRowIndex);

        // confirm deletion
        int option = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to delete the student with Bronco ID: " + student.getBroncoId(), 
        "Confirm Delete", JOptionPane.YES_NO_CANCEL_OPTION);
        
        if(option == JOptionPane.YES_OPTION){
        	// TODO handle exceptions
            student.delete();
            model.removeRow(selectedRowIndex);
        	
            // Display confirmation of deletion
            JOptionPane.showMessageDialog(null, "Student deleted");
        } 
	}

	private void handleAddNew() {
		String broncoId = broncoIdField.getText().trim();
    	String name = nameField.getText().trim();
    	
    	if (name.isBlank()) {
    		JOptionPane.showMessageDialog(this, "Invalid name!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	
    	if (broncoId.isBlank()) {
    		JOptionPane.showMessageDialog(this, "Invalid Bronco ID!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	
    	int broncoIdInt = 0;
    	
    	try {
			broncoIdInt = Integer.parseInt(broncoId);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Invalid Bronco ID!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
    	
    	Student student = new Student(name, broncoIdInt);
    	
    	// TODO handle exceptions
    	student.create();
    	
    	JOptionPane.showMessageDialog(this, "Student added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	private void handleSearch() {
		String name = nameField.getText().trim();
    	String broncoIdText = broncoIdField.getText().trim();
    	int broncoId = 0;
    	// TODO handle exceptions
    	try {
    		broncoId = Integer.parseInt(broncoIdText);
    	} catch (NumberFormatException ex) {
    		
    	}
    	
    	
        List<Student> students = Student.findBy(name.isBlank() ? null : name, broncoIdText.isBlank() ? null : broncoId);
        
        model.setRows(students);
	}
    
}
