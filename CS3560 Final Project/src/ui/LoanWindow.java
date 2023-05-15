package ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Loan;  

public class LoanWindow extends JFrame {
	
	public static final int RETURN = 1;
	public static final int EDIT = 2;
	
    private JTextField numberInput;
    private JTextField broncoIdInput;
    private JTextField totalPriceInput;
    private JTextField itemTitleInput;
    private JTextField nameInput;
    private JTextField amoundPaidInput;
    private JTextField loanDateInput;
    private JTextField dueDateInput;
    private JTextField returnDateInput;

    public LoanWindow(Loan loan, int editType){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 400));
        setTitle("Loan Window");
        setLayout(new BorderLayout());

        JPanel inputFields = new JPanel(new GridLayout(7,3, 20, 10));
        Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        inputFields.setBorder(emptyBorder);

        inputFields.add(new JLabel("Number"));
        inputFields.add(new JLabel("Student Bronco ID"));
        inputFields.add(new JLabel("Total Price"));

        numberInput = new JTextField(20);
        inputFields.add(numberInput);
        numberInput.setEditable(false);

        broncoIdInput = new JTextField(20);
        inputFields.add(broncoIdInput);
        broncoIdInput.setEditable(false);

        totalPriceInput = new JTextField(20);
        inputFields.add(totalPriceInput);
        totalPriceInput.setEditable(false);
        totalPriceInput.setText(NumberFormat.getCurrencyInstance().format(loan.calculatePrice()));

        inputFields.add(new JLabel("Item Title"));
        inputFields.add(new JLabel("Student Name"));
        inputFields.add(new JLabel("Amound Paid"));

        itemTitleInput = new JTextField(20);
        inputFields.add(itemTitleInput);
        itemTitleInput.setEditable(false);

        nameInput = new JTextField(20);
        inputFields.add(nameInput);
        nameInput.setEditable(false);

        amoundPaidInput = new JTextField(20);
        inputFields.add(amoundPaidInput);

        inputFields.add(new JLabel("Loan Date"));
        inputFields.add(new JLabel("Due Date"));
        inputFields.add(new JLabel("Return Date"));

        loanDateInput = new JTextField(20);
        inputFields.add(loanDateInput);

        dueDateInput = new JTextField(20);
        inputFields.add(dueDateInput);

        returnDateInput = new JTextField(20);
        inputFields.add(returnDateInput);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton editButton = new JButton();

        numberInput.setText(String.valueOf((loan.getNumber())));
        broncoIdInput.setText(String.valueOf((loan.getStudent().getBroncoId())));
        totalPriceInput.setText(String.valueOf((loan.calculatePrice())));
        itemTitleInput.setText((loan.getItem().getTitle()));
        nameInput.setText((loan.getStudent().getName()));
        amoundPaidInput.setText(String.valueOf((loan.getPaidAmount())));

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Date loanDate = loan.getLoanDate();
        loanDateInput.setText(dateFormat.format(loanDate));
        
        Date dueDate = loan.getDueDate();
        dueDateInput.setText(dateFormat.format(dueDate));

        if(editType == RETURN){
        	returnDateInput.setText(dateFormat.format(new Date()));
        	editButton.setText("Return");
        } else {
        	if (loan.getReturnDate() == null)
        		returnDateInput.setEditable(false);
        	else
        		returnDateInput.setText(dateFormat.format(loan.getLoanDate()));
        	
        	editButton.setText("Save");
        }
        
        buttonPanel.add(editButton);

        inputFields.add(buttonPanel);
        add(inputFields, BorderLayout.NORTH);

        editButton.addActionListener(e -> {
            Loan loanToUpdate = loan;
            
            try {
				Date dateLoan = dateFormat.parse(loanDateInput.getText());
				loanToUpdate.setLoanDate(dateLoan);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Invalid loan date!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
			}

            try {
				Date dateDue = dateFormat.parse(dueDateInput.getText());
				loanToUpdate.setDueDate(dateDue);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Invalid due date!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
			}
            
            try {
				loanToUpdate.setPaidAmount((Double.parseDouble(amoundPaidInput.getText())));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Invalid paid amount!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
			}

            
            Date returnDate = null;
            try {
            	returnDate = dateFormat.parse(returnDateInput.getText());
            } catch (ParseException ex) {
            	if (editType == RETURN) {
            		JOptionPane.showMessageDialog(null, "Invalid return date!", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
            	} else {
            		loanToUpdate.setReturnDate(returnDate);
            	}
            }
            
            try {
            	if (editType == RETURN)
            		loanToUpdate.returnItem(returnDate);
            	else
            		loanToUpdate.update();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Error Something went wrong!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
			}
            
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });


        pack();  // Call pack() after adding components.
        setVisible(true);  // Then make the JFrame visible.
    }

}
