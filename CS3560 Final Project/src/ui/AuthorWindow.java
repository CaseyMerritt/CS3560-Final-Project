package ui;

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;

import model.Author;

public class AuthorWindow extends JFrame{

    private JTextField nameField;
    private JTextField nationalityField;
    private JTextField subjectField;
    private JButton button;
    private JPanel panel;
    private Author author;

    public AuthorWindow(Author author) {
    	setupWindow();
		this.author = author;
		this.nameField.setText(author.getName());
		this.nationalityField.setText(author.getNationality());
        this.subjectField.setText(author.getSubject());
		
		button.setText("Save");
		button.addActionListener(e -> {
			handleUpdate(author);
		});
	}

	private void handleUpdate(Author author) {
		String name = nameField.getText().trim();
		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Invalid name!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String nationality = nationalityField.getText().trim();
		if (nationality.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Invalid nationality!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String subject = subjectField.getText().trim();
		if (subject.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Invalid subject!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.author.setName(name);
		this.author.setNationality(nationality);
		this.author.setSubject(subject);
		author.update();
		
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

    private void setupWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(250, 300));
        setTitle("Author");
		
		nameField = new JTextField(20);
		nationalityField = new JTextField(20);
        subjectField = new JTextField(20);
		button = new JButton();
		
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.add(new JLabel("Name"), BorderLayout.NORTH);
		namePanel.add(nameField, BorderLayout.SOUTH);
		
		JPanel nationalityPanel = new JPanel(new BorderLayout());
		nationalityPanel.add(new JLabel("Nationality"), BorderLayout.NORTH);
		nationalityPanel.add(nationalityField, BorderLayout.SOUTH);

        JPanel subjectPanel = new JPanel(new BorderLayout());
		subjectPanel.add(new JLabel("Subject"), BorderLayout.NORTH);
		subjectPanel.add(subjectField, BorderLayout.SOUTH);
		
		panel = new JPanel(new GridLayout(4, 1, 20, 0));
		
		panel.add(namePanel);
		panel.add(nationalityPanel);
        panel.add(subjectPanel);
		panel.add(button);
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		add(panel);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}


}
