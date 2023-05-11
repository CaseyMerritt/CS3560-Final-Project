package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class StudentsTabPanel extends JPanel {
    private JTextField broncoIdField;
    private JTextField nameField;

    private JTable table;

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
            // Handle search.
            
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
        addButton.addActionListener(new ActionListener() {
            // Handle Add.
            public void actionPerformed(ActionEvent e) {
                // Get values from input fields
                String name = nameField.getText();
                String broncoId = broncoIdField.getText();

                // Validate if the required fields are filled
                if (name.isEmpty() || broncoId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a new row object with the input values
                Object[] rowData = {broncoId, name, "", "", "", ""}; // Modify the last two empty columns as per your requirements

                // Add the new row to the table model
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(rowData);

                // Clear the input fields
                nameField.setText("");
                broncoIdField.setText("");

                // Display a success message
                JOptionPane.showMessageDialog(null, "Student added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        
        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            // Handle delete.
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = table.getSelectedRow();

                if(selectedRowIndex >= 0) {
                    // Get broncoId from row you want to delete
                    String broncoId = table.getValueAt(selectedRowIndex, 0).toString();

                    // confirm deletion
                    int option = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to delete the student with this BroncoId", 
                    "Confirm Delete", JOptionPane.YES_NO_CANCEL_OPTION);
                    
                    DefaultTableModel model= (DefaultTableModel) table.getModel();
                    
                    if(option == JOptionPane.YES_OPTION){
                        model.removeRow(selectedRowIndex);

                        // Display confirmation of deletion
                        JOptionPane.showMessageDialog(null, "Student deleted");
                    } 
                    else {
                        JOptionPane.showMessageDialog(null, "Please select a student to delete.",
                         "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });           

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Create table with 7 columns.
        String[] columnNames = {
            "Bronco ID", "Name", "Items Loaned", "Items Overdue", "Balance", ""
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
