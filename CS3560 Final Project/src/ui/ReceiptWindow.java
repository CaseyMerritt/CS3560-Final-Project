package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import model.Loan;

public class ReceiptWindow extends JFrame {

	public ReceiptWindow(Loan loan) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(250, 300));
        setTitle("Loan #" + loan.getNumber() + " Receipt");
        setVisible(true);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		
		JTextField numberField = new JTextField();
		numberField.setEditable(false);
		numberField.setText(loan.getNumber() + "");
		
		JPanel numberPanel = new JPanel(new BorderLayout());
		numberPanel.add(new JLabel("Loan Number:"), BorderLayout.WEST);
		numberPanel.add(numberField, BorderLayout.EAST);
		
		JTextField studentField = new JTextField();
		studentField.setEditable(false);
		studentField.setText(loan.getStudent().getName());
		
		JPanel studentPanel = new JPanel(new BorderLayout());
		studentPanel.add(new JLabel("Student:"), BorderLayout.WEST);
		studentPanel.add(studentField, BorderLayout.EAST);
		
		JTextField loanDateField = new JTextField();
		loanDateField.setEditable(false);
		loanDateField.setText(dateFormat.format(loan.getLoanDate()));
		
		JPanel loanDatePanel = new JPanel(new BorderLayout());
		loanDatePanel.add(new JLabel("Loan Date:"), BorderLayout.WEST);
		loanDatePanel.add(loanDateField, BorderLayout.EAST);
		
		JTextField dueDateField = new JTextField();
		dueDateField.setEditable(false);
		dueDateField.setText(dateFormat.format(loan.getDueDate()));
		
		JPanel dueDatePanel = new JPanel(new BorderLayout());
		dueDatePanel.add(new JLabel("Due Date:"), BorderLayout.WEST);
		dueDatePanel.add(dueDateField, BorderLayout.EAST);
		
		JTextField priceField = new JTextField();
		priceField.setEditable(false);
		priceField.setText(currencyFormat.format(loan.getItem().getDailyPrice()));
		
		JPanel pricePanel = new JPanel(new BorderLayout());
		pricePanel.add(new JLabel("Daily Price:"), BorderLayout.WEST);
		pricePanel.add(priceField, BorderLayout.EAST);
		
		JTextField titleField = new JTextField();
		titleField.setEditable(false);
		titleField.setText(loan.getItem().getTitle());
		
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.add(new JLabel("Item Title:"), BorderLayout.WEST);
		titlePanel.add(titleField, BorderLayout.EAST);
		
		JTextField estimateField = new JTextField();
		estimateField.setEditable(false);
		estimateField.setText(currencyFormat.format(30));
		
		JPanel estimatePanel = new JPanel(new BorderLayout());
		estimatePanel.add(new JLabel("Estimated Price:"), BorderLayout.WEST);
		estimatePanel.add(estimateField, BorderLayout.EAST);
		
		JPanel panel = new JPanel(new GridLayout(7, 1, 20, 0));
		
		panel.add(numberPanel);
		panel.add(studentPanel);
		panel.add(loanDatePanel);
		panel.add(dueDatePanel);
		panel.add(titlePanel);
		panel.add(pricePanel);
		panel.add(estimatePanel);
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		add(panel);
		
		pack();
	}
	
}
