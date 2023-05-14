package ui;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import model.Loan;
import java.util.List;

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

        Loan loan = new Loan();
        List<Loan> loans = loan.getLoan(num);

        if(loans.size() > 0){
            numberInput.setText(String.valueOf((loans.get(0).getNumber())));
            boncoIdInput.setText(String.valueOf((loans.get(0).getStudent().getBroncoId())));
            totalPriceInput.setText(String.valueOf((loans.get(0).calculatePrice())));
            itemTitleInput.setText((loans.get(0).getItem().getTitle()));
            nameInput.setText((loans.get(0).getStudent().getName()));
            amoundPaidInput.setText(String.valueOf((loans.get(0).getPaidAmount())));
            loanDateInput.setText("");
            dueDateInput.setText("");
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

        });

        editButton.addActionListener(e -> {

        });


        pack();  // Call pack() after adding components.
        setVisible(true);  // Then make the JFrame visible.
    }

}
