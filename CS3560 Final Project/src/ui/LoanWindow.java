package ui;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import model.Loan;
import model.LoanQuery;

import java.util.List;
import java.util.Date;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  

public class LoanWindow extends JFrame {
    JTextField numberInput;
    JTextField boncoIdInput;
    JTextField totalPriceInput;
    JTextField itemTitleInput;
    JTextField nameInput;
    JTextField amoundPaidInput;
    JTextField loanDateInput;
    JTextField dueDateInput;
    JTextField returnDateInput;

    public LoanWindow(String state, int num){
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

        boncoIdInput = new JTextField(20);
        inputFields.add(boncoIdInput);

        totalPriceInput = new JTextField(20);
        inputFields.add(totalPriceInput);

        inputFields.add(new JLabel("Item Title"));
        inputFields.add(new JLabel("Student Name"));
        inputFields.add(new JLabel("Amound Paid"));

        itemTitleInput = new JTextField(20);
        inputFields.add(itemTitleInput);

        nameInput = new JTextField(20);
        inputFields.add(nameInput);

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

        JButton returnButton = new JButton("Return");
        JButton editButton = new JButton("Edit");

        LoanQuery lq = new LoanQuery();
        lq.setNumber(num);
        List<Loan> loans = Loan.findBy(lq);

        if(loans.size() > 0){
            numberInput.setText(String.valueOf((loans.get(0).getNumber())));
            boncoIdInput.setText(String.valueOf((loans.get(0).getStudent().getBroncoId())));
            totalPriceInput.setText(String.valueOf((loans.get(0).calculatePrice())));
            itemTitleInput.setText((loans.get(0).getItem().getTitle()));
            nameInput.setText((loans.get(0).getStudent().getName()));
            amoundPaidInput.setText(String.valueOf((loans.get(0).getPaidAmount())));

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            Date loanDate = loans.get(0).getLoanDate();
            loanDateInput.setText(dateFormat.format(loanDate));
            
            Date dueDate = loans.get(0).getDueDate();
            dueDateInput.setText(dateFormat.format(dueDate));

            returnDateInput.setText("");
        }else{
            System.out.println("Empty Query");
        }

        if(state == "info"){
        }else if(state == "return"){
            buttonPanel.add(returnButton);
        }else if(state == "edit"){
            buttonPanel.add(editButton);
        }

        inputFields.add(buttonPanel);
        add(inputFields, BorderLayout.NORTH);

        returnButton.addActionListener(e -> {
            Date todaysDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            loans.get(0).setReturnDate(todaysDate);

            returnDateInput.setText(dateFormat.format(todaysDate));

            loans.get(0).getItem().setAvailable(true);

            try {
                loans.get(0).update();   
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Incorrect Date Format!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        editButton.addActionListener(e -> {
            Loan loanToUpdate = loans.get(0);

            loanToUpdate.setNumber(Integer.parseInt(numberInput.getText()));
            loanToUpdate.getStudent().setBroncoId((Integer.parseInt(boncoIdInput.getText())));
            loanToUpdate.setPaidAmount((Integer.parseInt(amoundPaidInput.getText())));
            loanToUpdate.getItem().setTitle(itemTitleInput.getText());
            loanToUpdate.getStudent().setName(nameInput.getText());

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            try {
                Date loanDate = dateFormat.parse(loanDateInput.getText());
                loanToUpdate.setLoanDate(loanDate);

                Date dueDate = dateFormat.parse(dueDateInput.getText());
                loanToUpdate.setDueDate(dueDate);

                loanToUpdate.update();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error Something went wrong!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        pack();  // Call pack() after adding components.
        setVisible(true);  // Then make the JFrame visible.
    }

}
