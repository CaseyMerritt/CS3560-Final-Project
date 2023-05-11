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

public class DirectorsTabPanel extends JPanel {
    private JTextField nameField;
    private JTextField nationalityField;
    private JTextField styleField;

    private JTable table;

    public DirectorsTabPanel() {
        setLayout(new BorderLayout());

        // Create input fields.
        nameField = new JTextField(20);
        nationalityField = new JTextField(20);
        styleField = new JTextField(20);

        // Create a panel to hold the fields.
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(1, 3, 20, 0));

        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.add(new JLabel("Name"), BorderLayout.NORTH);
        namePanel.add(nameField, BorderLayout.CENTER);

        JPanel nationalityPanel = new JPanel(new BorderLayout());
        nationalityPanel.add(new JLabel("Nationality"), BorderLayout.NORTH);
        nationalityPanel.add(nationalityField, BorderLayout.CENTER);

        JPanel stylePanel = new JPanel(new BorderLayout());
        stylePanel.add(new JLabel("Style"), BorderLayout.NORTH);
        stylePanel.add(styleField, BorderLayout.CENTER);

        
        fieldsPanel.add(namePanel);//min pages length
        fieldsPanel.add(nationalityPanel);//published released before
        fieldsPanel.add(stylePanel);//Author/Director

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
            nationalityField.setText("");
            styleField.setText("");
        });

        // Add Add button.
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            // Handle Add.
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            // Handle delete.
            public void actionPerformed(ActionEvent e) {
                // Get name from user
                String name = JOptionPane.showInputDialog("Enter Director Name To Delete");
                // delete Director in database
                // database.deleteAuthor(name);
            }
        });           

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Create table with 5 columns.
        String[] columnNames = {
            "Name", "Nationality", "Style", "Films", ""
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
