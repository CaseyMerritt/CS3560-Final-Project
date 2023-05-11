package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class LoansTabPanel extends JPanel {
    private JTextField numberField;
    private JTextField titleField;
    private JTextField broncoIdField;
    private JTextField nameField;
    private JTextField loanedAfterField;
    private JTextField loanedBeforeField;
    private JTextField dueAfterField;
    private JTextField dueBeforeField;
    private JTextField CourseField;
    private JCheckBox showOverDue;

    private JTable table;

    public LoansTabPanel() {
        setLayout(new BorderLayout());

        // Create input fields.
        numberField = new JTextField(20);
        titleField = new JTextField(20);
        broncoIdField = new JTextField(20);
        nameField = new JTextField(20);
        loanedAfterField = new JTextField(20);
        loanedBeforeField = new JTextField(20);
        dueAfterField  = new JTextField(20);
        dueBeforeField  = new JTextField(20);
        CourseField  = new JTextField(20);
        showOverDue = new JCheckBox("Only Show Overdue");

        // Create a panel to hold the fields.
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2, 5, 20, 0));

        JPanel numberPanel = new JPanel(new BorderLayout());
        numberPanel.add(new JLabel("Number"), BorderLayout.NORTH);
        numberPanel.add(numberField, BorderLayout.CENTER);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(new JLabel("Item Title"), BorderLayout.NORTH);
        titlePanel.add(titleField, BorderLayout.CENTER);

        JPanel broncoIdPanel = new JPanel(new BorderLayout());
        broncoIdPanel.add(new JLabel("Student Bronco ID"), BorderLayout.NORTH);
        broncoIdPanel.add(broncoIdField, BorderLayout.CENTER);

        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.add(new JLabel("Student Name"), BorderLayout.NORTH);
        namePanel.add(nameField, BorderLayout.CENTER);

        JPanel dueAfterPanel = new JPanel(new BorderLayout());
        dueAfterPanel.add(new JLabel("Due After"), BorderLayout.NORTH);
        dueAfterPanel.add(dueAfterField, BorderLayout.CENTER);

        JPanel dueBeforePanel = new JPanel(new BorderLayout());
        dueBeforePanel.add(new JLabel("Due Before"), BorderLayout.NORTH);
        dueBeforePanel.add(dueBeforeField, BorderLayout.CENTER);

        JPanel loanedAfterPanel = new JPanel(new BorderLayout());
        loanedAfterPanel.add(new JLabel("Loaned After"), BorderLayout.NORTH);
        loanedAfterPanel.add(loanedAfterField, BorderLayout.CENTER);

        JPanel loanedBeforePanel = new JPanel(new BorderLayout());
        loanedBeforePanel.add(new JLabel("Loaned Before"), BorderLayout.NORTH);
        loanedBeforePanel.add(loanedBeforeField, BorderLayout.CENTER);

        JPanel coursePanel = new JPanel(new BorderLayout());
        coursePanel.add(new JLabel("Course"), BorderLayout.NORTH);
        coursePanel.add(CourseField, BorderLayout.CENTER);

        fieldsPanel.add(numberPanel);
        fieldsPanel.add(broncoIdPanel);
        fieldsPanel.add(loanedAfterPanel);
        fieldsPanel.add(dueAfterPanel);
        fieldsPanel.add(coursePanel);
        fieldsPanel.add(titlePanel);
        fieldsPanel.add(namePanel);
        fieldsPanel.add(loanedBeforePanel);
        fieldsPanel.add(dueBeforePanel);
        fieldsPanel.add(showOverDue);

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // Handle search.
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            // Handle Reset.

            numberField.setText("");
            titleField.setText("");
            broncoIdField.setText("");
            nameField.setText("");
            loanedAfterField.setText("");
            loanedBeforeField.setText("");
            dueAfterField.setText("");
            dueBeforeField.setText("");
            CourseField.setText("");
            showOverDue.setText("");
            showOverDue.setSelected(false);
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
        addButton.addActionListener(e -> {
            // Handle Revenue Report.
        });        

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(revenueButton);

        // Create table with 8 columns.
        String[] columnNames = {
            "Number", "Item Title", "Student Bronco ID", "Student Name", "Course", "Loan Date", "Due Date", ""
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Create a JScrollPane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);          

        // Add the fields and buttons to the main panel.
        add(fieldsPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }
}
