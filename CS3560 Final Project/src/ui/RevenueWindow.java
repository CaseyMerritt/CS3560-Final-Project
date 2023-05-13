package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class RevenueWindow extends JFrame {
	private JTable table;
	private JComboBox<String> year;
	private JTextField revenue;
	private JTextField totalBalance;
	
	public RevenueWindow() {
		setupWindow();
	}

	private void setupWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        setTitle("Revenue Report");
		
        year = new JComboBox<>();
        year.addItem("2023");
        revenue = new JTextField(20);
        revenue.setText("$5.00");
        revenue.setEditable(false);
        totalBalance = new JTextField(20);
        totalBalance.setText("$63.00");
        totalBalance.setEditable(false);
        
        JPanel fieldPanel = new JPanel(new GridLayout());
        
        JPanel yearPanel = new JPanel(new BorderLayout());
        yearPanel.add(new JLabel("Year"), BorderLayout.NORTH);
        yearPanel.add(year, BorderLayout.SOUTH);
        
        JPanel revenuePanel = new JPanel(new BorderLayout());
        revenuePanel.add(new JLabel("Revenue"), BorderLayout.NORTH);
        revenuePanel.add(revenue, BorderLayout.SOUTH);
        
        JPanel balancePanel = new JPanel(new BorderLayout());
        balancePanel.add(new JLabel("Total Balances"), BorderLayout.NORTH);
        balancePanel.add(totalBalance, BorderLayout.SOUTH);
        
        fieldPanel.add(yearPanel);
        fieldPanel.add(revenuePanel);
        fieldPanel.add(balancePanel);
        
		// Create table with 8 columns.
        String[] columnNames = {
            "Bronco ID", "Student Name", "Balance", "Paid Amount"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        
        Object[][] data = {
        		{"12345", "John", "$0.00", "$0.00"},
        		{"67890", "Mary", "$63.00", "$5.00"}
        };
        
        model.addRow(data[0]);
        model.addRow(data[1]);

        // Create a JScrollPane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);   
        
        add(fieldPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
}
