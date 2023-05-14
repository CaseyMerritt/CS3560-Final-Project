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

import java.util.List;

import model.Loan;
import model.Student;

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
        
		// Create table with 8 columns.
        String[] columnNames = {
            "Bronco ID", "Student Name", "Balance", "Paid Amount"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        Student student = new Student();
        List<Student> studentsList = student.getAllStudents();
        
        double rev = 0;
        double totalB = 0;
        for(int i = 0; i < studentsList.size(); i++){
            String[] arr = new String[4];

            arr[0] = String.valueOf(studentsList.get(i).getBroncoId());
            arr[1] = studentsList.get(i).getName();
            arr[2] = String.valueOf(getBalance(studentsList.get(i).getLoans()));
            arr[3] = String.valueOf(getPaid(studentsList.get(i).getLoans()));

            totalB += Double.parseDouble(arr[2]);
            rev += Double.parseDouble(arr[3]);

            model.addRow(arr);
        }

        revenue.setText(String.valueOf(rev));
        totalBalance.setText(String.valueOf(totalB));

        // Create a JScrollPane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);   
        
        add(fieldPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}

    public double getPaid(List<Loan> loans){
        double totalPaid = 0;
        for(int i = 0; i < loans.size(); i++){
            totalPaid += loans.get(i).getPaidAmount();
        }

        return totalPaid;
    }

    public double getBalance(List<Loan> loans){
        double totalBalance = 0;
        for(int i = 0; i < loans.size(); i++){
            totalBalance += loans.get(i).calculatePrice();
        }

        return totalBalance;
    }
}
