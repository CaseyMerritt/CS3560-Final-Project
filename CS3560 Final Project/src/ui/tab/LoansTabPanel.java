package ui.tab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ui.RevenueWindow;
import ui.table.LoanTableModel;

public class LoansTabPanel extends TabPanel {
    private JTextField numberField;
    private JTextField titleField;
    private JTextField broncoIdField;
    private JTextField nameField;
    private JTextField loanedAfterField;
    private JTextField loanedBeforeField;
    private JTextField dueAfterField;
    private JTextField dueBeforeField;
    private JTextField courseField;
    private JCheckBox showOverDue;

    public LoansTabPanel() {
    	super(2, 5, new LoanTableModel());

        // Create input fields.
        initializeFields();

        addFields();

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // TODO implement search
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            // Handle Reset.
            handleReset();
        });

        // Add add button.
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            // Handle Add.
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            //Handle delete.
            public void actionPerformed(ActionEvent e) {
                // Get loan id from user
                String deleteLoanTerm = JOptionPane.showInputDialog("Enter Number To Delete");

                try{
                    int loanId = Integer.parseInt(deleteLoanTerm);
                    // delete loan in database
                    // database.deleteItem(loanId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid loan Number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Add Revenue button.
        JButton revenueButton = new JButton("Revenue Report");
        revenueButton.addActionListener(e -> {
            // Handle Revenue Report.
        	new RevenueWindow();
        });        

        addButton(searchButton);
        addButton(resetButton);
        addButton(addButton);
        addButton(deleteButton);
        addButton(revenueButton);
    }

	private void handleReset() {
		numberField.setText("");
		titleField.setText("");
		broncoIdField.setText("");
		nameField.setText("");
		loanedAfterField.setText("");
		loanedBeforeField.setText("");
		dueAfterField.setText("");
		dueBeforeField.setText("");
		courseField.setText("");
		showOverDue.setSelected(false);
	}

	private void addFields() {
		addField(numberField, "Number");
        addField(broncoIdField, "Student Bronco ID");
        addField(loanedAfterField, "Loaned After");
        addField(dueAfterField, "Due After");
        addField(courseField, "Course");
        addField(titleField, "Item Title");
        addField(nameField, "Student Name");
        addField(loanedBeforeField, "Loaned Before");
        addField(dueBeforeField, "Due Before");
        addField(showOverDue);
	}

	private void initializeFields() {
		numberField = new JTextField(20);
        titleField = new JTextField(20);
        broncoIdField = new JTextField(20);
        nameField = new JTextField(20);
        loanedAfterField = new JTextField(20);
        loanedBeforeField = new JTextField(20);
        dueAfterField  = new JTextField(20);
        dueBeforeField  = new JTextField(20);
        courseField  = new JTextField(20);
        showOverDue = new JCheckBox("Only Show Overdue");
	}
}
