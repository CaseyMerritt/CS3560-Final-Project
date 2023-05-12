package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

import model.*;

public class MakeLoanWindow extends JFrame {
	private JTextField course;
	private JTextField itemTitle;
	private JComboBox student;
	private JTextField numberDays;
	private Item item;
	
	public MakeLoanWindow(Item item) {
		this.item = item;
		setupWindow();
	}

	private void setupWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 250));
        setTitle("Loan Item");
        
        itemTitle = new JTextField(20);
        student = new JComboBox<String>();
        course = new JTextField(20);
        numberDays = new JTextField(20);
        
        itemTitle.setEditable(false);
        
        itemTitle.setText(item.getTitle());
        
        List<Student> students = Student.findBy(null, null);
        
        for (Student s : students) {
        	student.addItem(s);
        }
        
        JPanel fieldPanel = new JPanel(new GridLayout(4, 1, 20, 0));
        
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.add(new Label("Book Title"), BorderLayout.NORTH);
        itemPanel.add(itemTitle, BorderLayout.SOUTH);
        
        JPanel studentPanel = new JPanel(new BorderLayout());
        studentPanel.add(new Label("Student"), BorderLayout.NORTH);
        studentPanel.add(student, BorderLayout.SOUTH);
        
        JPanel coursePanel = new JPanel(new BorderLayout());
        coursePanel.add(new Label("Course"), BorderLayout.NORTH);
        coursePanel.add(course, BorderLayout.SOUTH);
        
        JPanel dayPanel = new JPanel(new BorderLayout());
        dayPanel.add(new Label("Number of Days"), BorderLayout.NORTH);
        dayPanel.add(numberDays, BorderLayout.SOUTH);
        
        fieldPanel.add(itemPanel);
        fieldPanel.add(studentPanel);
        fieldPanel.add(coursePanel);
        fieldPanel.add(dayPanel);
        
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton save = new JButton("Save");
        
        save.addActionListener(e -> {
        	int numDays;
        	
        	try {
				numDays = Integer.parseInt(numberDays.getText().trim());
				
				if (numDays < 0) {
					JOptionPane.showMessageDialog(this, "Invalid number of days!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Invalid number of days!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
        	
        	Student s = (Student) student.getSelectedItem();
        	
        	if (s.getNumberLoansOverdue() > 0) {
        		JOptionPane.showMessageDialog(this, "Student has too many overdue loans!", "Error", JOptionPane.ERROR_MESSAGE);
        		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				return;
        	}
        	
        	Loan loan = item.makeLoanTo(s, numDays, course.getText().trim());

        	new ReceiptWindow(loan);
        	this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        
        buttonPanel.add(save);
        
        setLayout(new BorderLayout());
        add(fieldPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
}
