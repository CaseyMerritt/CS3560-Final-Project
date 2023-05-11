package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import model.Student;

public class StudentWindow extends JFrame {
	
	private JTextField broncoIdField;
	private JTextField nameField;
	private JButton button;
	private JPanel panel;
	private Student student;

	public StudentWindow() {
		setupWindow();
		button.setText("Save");
		button.addActionListener(e -> {
			int broncoId;
			try {
				broncoId = Integer.parseInt(broncoIdField.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Bronco ID.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String name = nameField.getText().trim();
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// TODO handle SQLException
			new Student(name, broncoId).create();
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
	}
	
	public StudentWindow(Student student) {
		this.student = student;
		this.broncoIdField.setText(student.getBroncoId() + "");
		this.broncoIdField.setEditable(false);
		this.nameField.setText(student.getName());
		setupWindow();
		button.setText("Update");
		button.addActionListener(e -> {
			int broncoId;
			try {
				broncoId = Integer.parseInt(broncoIdField.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Bronco ID.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String name = nameField.getText().trim();
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// TODO handle SQLException
			this.student.setBroncoId(broncoId);
			this.student.setName(name);
			student.update();
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
	}
	
	private void setupWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(250, 300));
        setTitle("Student");
        setVisible(true);
		
		broncoIdField = new JTextField(20);
		nameField = new JTextField(20);
		button = new JButton();
		
		JPanel broncoIdPanel = new JPanel(new BorderLayout());
		broncoIdPanel.add(new JLabel("Bronco ID"), BorderLayout.NORTH);
		broncoIdPanel.add(broncoIdField, BorderLayout.SOUTH);
		
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.add(new JLabel("Name"), BorderLayout.NORTH);
		namePanel.add(nameField, BorderLayout.SOUTH);
		
		panel = new JPanel(new GridLayout(3, 1, 20, 0));
		
		panel.add(broncoIdPanel);
		panel.add(namePanel);
		panel.add(button);
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		add(panel);
		
		pack();
	}
	
}
