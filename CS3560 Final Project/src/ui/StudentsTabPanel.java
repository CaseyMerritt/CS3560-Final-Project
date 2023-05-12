package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Student;
import ui.table.StudentTableModel;

public class StudentsTabPanel extends JPanel {
    private JTextField broncoIdField;
    private JTextField nameField;

    private JTable table;
    private StudentTableModel model;

    public StudentsTabPanel() {
        setLayout(new BorderLayout());

        // Create input fields.
        nameField = new JTextField(20);
        broncoIdField = new JTextField(20);

        // Create a panel to hold the fields.
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(1, 3, 20, 0));

        JPanel broncoIdPanel = new JPanel(new BorderLayout());
        broncoIdPanel.add(new JLabel("Bronco ID"), BorderLayout.NORTH);
        broncoIdPanel.add(broncoIdField, BorderLayout.CENTER);

        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.add(new JLabel("Name"), BorderLayout.NORTH);
        namePanel.add(nameField, BorderLayout.CENTER);

        
        fieldsPanel.add(broncoIdPanel);
        fieldsPanel.add(namePanel);

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
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
        	String broncoId = broncoIdField.getText().trim();
        	String name = nameField.getText().trim();
        	
        	if (name.isBlank()) {
        		JOptionPane.showMessageDialog(this, "Name cannot be blank!", "Error", JOptionPane.ERROR_MESSAGE);
    			return;
        	}
        	
        	if (broncoId.isBlank()) {
        		JOptionPane.showMessageDialog(this, "Bronco ID cannot be blank!", "Error", JOptionPane.ERROR_MESSAGE);
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
        });

        
        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int selectedRowIndex = table.getSelectedRow();
            
            if (selectedRowIndex < 0) return;
            
            Student student = model.getRow(selectedRowIndex);

            // confirm deletion
            int option = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete the student with Bronco ID: " + student.getBroncoId(), 
            "Confirm Delete", JOptionPane.YES_NO_CANCEL_OPTION);
            
            if(option == JOptionPane.YES_OPTION){
            	// TODO handle exceptions
                student.delete();
            	
                // Display confirmation of deletion
                JOptionPane.showMessageDialog(null, "Student deleted");
            } 
        });           

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Create table
        model = new StudentTableModel();
        table = new JTable(model);

        // Create a JScrollPane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);       

        // Add the fields and buttons to the main panel.
        add(fieldsPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }
    
}
