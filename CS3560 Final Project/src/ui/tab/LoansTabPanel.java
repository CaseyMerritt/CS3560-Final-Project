package ui.tab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Loan;
import model.LoanQuery;
import ui.LoanWindow;
import ui.RevenueWindow;
import ui.table.LoanTableModel;

public class LoansTabPanel extends TabPanel<Loan> {
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
            handleSearch();
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            // Handle Reset.
            handleReset();
        });
        
        // Add edit button.
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            // Handle edit
        	int selectedRowIndex = getSelectedRow();
    		
    		if (selectedRowIndex < 0) return;
    		
    		Loan loan = (Loan) model.getRow(selectedRowIndex);
    		
    		new LoanWindow(loan, LoanWindow.EDIT);
        });
        
        // Add return button.
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> {
            // Handle edit
        	int selectedRowIndex = getSelectedRow();
    		
    		if (selectedRowIndex < 0) return;
    		
    		Loan loan = (Loan) model.getRow(selectedRowIndex);
    		
    		if (loan.getReturnDate() == null)
    			new LoanWindow(loan, LoanWindow.RETURN);
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
        	handleDelete();
        });
        
        // Add Revenue button.
        JButton revenueButton = new JButton("Revenue Report");
        revenueButton.addActionListener(e -> {
            // Handle Revenue Report.
        	new RevenueWindow();
        });        

        addButton(searchButton);
        addButton(resetButton);
        addButton(editButton);
        addButton(returnButton);
        addButton(deleteButton);
        addButton(revenueButton);
    }

	private void handleDelete() {
		int selectedRowIndex = getSelectedRow();
		
		if (selectedRowIndex < 0) return;
		
		Loan loan = (Loan) model.getRow(selectedRowIndex);

		// confirm deletion
		int option = JOptionPane.showConfirmDialog(this, 
		"Are you sure you want to delete the loan with number: " + loan.getNumber(), 
		"Confirm Delete", JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(option == JOptionPane.YES_OPTION){
			try {
				loan.delete();
			} catch (IllegalStateException e) {
				JOptionPane.showMessageDialog(null, "Unable to delete loan!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
			}
			
		    model.removeRow(selectedRowIndex);
			
		    // Display confirmation of deletion
		    JOptionPane.showMessageDialog(null, "Loan deleted");
		}
	}

	private void handleSearch() {
		LoanQuery q = new LoanQuery();
		
		String number = numberField.getText().trim();
		String title = titleField.getText().trim();
		String broncoId = broncoIdField.getText().trim();
		String name = nameField.getText().trim();
		String loanedAfter = loanedAfterField.getText().trim();
		String loanedBefore = loanedBeforeField.getText().trim();
		String dueAfter = dueAfterField.getText().trim();
		String dueBefore = dueBeforeField.getText().trim();
		String course = courseField.getText().trim();
		
		int numInt = 0;
		if (!number.isBlank())
			try {
				numInt = Integer.parseInt(number);
			} catch (NumberFormatException e) {
				createErrorMessage("Invalid number!");
				return;
			}
		int broncoIdInt = 0;
		if (!broncoId.isBlank())
			try {
				broncoIdInt = Integer.parseInt(broncoId);
			} catch (NumberFormatException e) {
				createErrorMessage("Invalid Bronco ID!");
				return;
			}
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date loanAfterDate = null;
		if (!loanedAfter.isBlank())
			try {
				loanAfterDate = format.parse(loanedAfter);
			} catch (ParseException e) {
				createErrorMessage("Invalid loaned after date!");
				return;
			}
		Date loanBeforeDate = null;
		if (!loanedBefore.isBlank())
			try {
				loanBeforeDate = format.parse(loanedBefore);
			} catch (ParseException e) {
				createErrorMessage("Invalid loaned before date!");
				return;
			}
		Date dueAfterDate = null;
		if (!dueAfter.isBlank())
			try {
				dueAfterDate = format.parse(dueAfter);
			} catch (ParseException e) {
				createErrorMessage("Invalid due after date!");
				return;
			}
		Date dueBeforeDate = null;
		if (!dueBefore.isBlank())
			try {
				dueBeforeDate = format.parse(dueBefore);
			} catch (ParseException e) {
				createErrorMessage("Invalid due before date!");
				return;
			}
		
		if (!number.isBlank())
			q.setNumber(numInt);
		
		if (!title.isBlank())
			q.setItemTitle(title);
		
		if (!broncoId.isBlank())
			q.setBroncoId(broncoIdInt);
		
		if (!name.isBlank())
			q.setStudentName(name);
		
		if (!loanedAfter.isBlank())
			q.setLoanedAfter(loanAfterDate);
		
		if (!loanedBefore.isBlank())
			q.setLoanedBefore(loanBeforeDate);
		
		if (!dueAfter.isBlank())
			q.setDueAfter(dueAfterDate);
		
		if (!dueBefore.isBlank())
			q.setDueBefore(dueBeforeDate);
		
		if (!course.isBlank())
			q.setCourse(course);
		
		q.setOnlyOverdue(showOverDue.isSelected());
		
		List<Loan> loans = Loan.findBy(q);
		
		model.setRows(loans);
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
