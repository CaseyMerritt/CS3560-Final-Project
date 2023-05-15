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
	
	public StudentWindow(Student student) {
		setupWindow();
		this.student = student;
		this.broncoIdField.setText(student.getBroncoId() + "");
		this.broncoIdField.setEditable(false);
		this.nameField.setText(student.getName());
		
		button.setText("Save");
		button.addActionListener(e -> {
			int broncoId;
			try {
				broncoId = Integer.parseInt(broncoIdField.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid Bronco ID!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String name = nameField.getText().trim();
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid name!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			this.student.setBroncoId(broncoId);
			this.student.setName(name);
			
			try {
				this.student.update();
			} catch (IllegalStateException e1) {
				JOptionPane.showMessageDialog(null, "Unable to update student! Check for duplicate ID!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
			}
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
	}
	
	private void setupWindow() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(250, 300));
        setTitle("Student");
		
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
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
