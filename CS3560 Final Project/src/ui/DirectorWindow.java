package ui;

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;

import model.Director;

public class DirectorWindow extends JFrame{

    private JTextField nameField;
    private JTextField nationalityField;
    private JTextField styleField;
    private JButton button;
    private JPanel panel;
    private Director director;

	// TODO make ui functional

    public DirectorWindow() {
		setupWindow();
		button.setText("Save");
		button.addActionListener(e -> {
			String name = nameField.getText().trim();
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

            String nationality = nationalityField.getText().trim();
			if (nationality.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Nationality.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

            String style = styleField.getText().trim();
			if (style.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Style.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// TODO handle SQLException
			new Director(name,nationality,style).create();
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
	}

    public DirectorWindow(Director director) {
		this.director = director;
		this.nameField.setText(director.getName());
		this.nationalityField.setText(director.getNationality());
        this.styleField.setText(director.getStyle());
		setupWindow();
		button.setText("Update");
		button.addActionListener(e -> {
			String name = nameField.getText().trim();
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

            String nationality = nationalityField.getText().trim();
			if (nationality.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Nationality.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

            String style = styleField.getText().trim();
			if (style.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Subject.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// TODO handle SQLException
			this.director.setName(name);
			this.director.setNationality(nationality);
            this.director.setStyle(style);
			director.update();
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
	}

    private void setupWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(250, 300));
        setTitle("Director");
        setVisible(true);
		
		nameField = new JTextField(20);
		nationalityField = new JTextField(20);
        styleField = new JTextField(20);
		button = new JButton();
		
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.add(new JLabel("Name"), BorderLayout.NORTH);
		namePanel.add(nameField, BorderLayout.SOUTH);
		
		JPanel nationalityPanel = new JPanel(new BorderLayout());
		nationalityPanel.add(new JLabel("Nationality"), BorderLayout.NORTH);
		nationalityPanel.add(nationalityField, BorderLayout.SOUTH);

        JPanel stylePanel = new JPanel(new BorderLayout());
		stylePanel.add(new JLabel("Style"), BorderLayout.NORTH);
		stylePanel.add(styleField, BorderLayout.SOUTH);
		
		panel = new JPanel(new GridLayout(4, 1, 20, 0));
		
		panel.add(namePanel);
		panel.add(nationalityPanel);
        panel.add(stylePanel);
		panel.add(button);
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		add(panel);
		
		pack();
	}


}
