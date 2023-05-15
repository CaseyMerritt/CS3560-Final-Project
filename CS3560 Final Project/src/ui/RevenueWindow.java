package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.time.Year;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Student;

public class RevenueWindow extends JFrame {
	private JTable table;
	private JComboBox<Integer> year;
	private JTextField revenue;
	private JTextField totalBalance;
	
	private DefaultTableModel model;
	
	private static final int STARTING_YEAR = 1980;
	
	public RevenueWindow() {
		setupWindow();
	}

	private void setupWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        setTitle("Revenue Report");
		
        year = new JComboBox<>();
        
        for (int i = Year.now().getValue(); i >= STARTING_YEAR; i--)
        	year.addItem(i);
        
        revenue = new JTextField(20);
        revenue.setText("");
        revenue.setEditable(false);
        totalBalance = new JTextField(20);
        totalBalance.setText("");
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
        
		// Create table
        String[] columnNames = {
            "Bronco ID", "Student Name", "Balance", "Paid Amount"
        };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        
        year.addActionListener(e -> {
        	handleYearChange();
        });

        // Create a JScrollPane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);   
        
        add(fieldPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // setup initial values of revenue report
        handleYearChange();
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}

	private void handleYearChange() {
		model.setRowCount(0);
		
		List<Student> studentsList = Student.findBy(null, null);
		
		// add information for students
		int currentYear = (int)year.getSelectedItem();
		NumberFormat format = NumberFormat.getCurrencyInstance();
		double rev = 0;
		double totalBal = 0;
		for(int i = 0; i < studentsList.size(); i++){
		    String[] arr = new String[4];

		    arr[0] = String.valueOf(studentsList.get(i).getBroncoId());
		    arr[1] = studentsList.get(i).getName();
		    
		    double bal = studentsList.get(i).calculateBalance(currentYear);
		    double paid = studentsList.get(i).calculateTotalPaid(currentYear);
		    
		    arr[2] = format.format(bal);
		    arr[3] = format.format(paid);

		    totalBal += bal;
		    rev += paid;
		    
		    if (bal > 0 || paid > 0)
		    	model.addRow(arr);
		}
		
		revenue.setText(format.format(rev));
		totalBalance.setText(format.format(totalBal));
	}

}
