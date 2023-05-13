package ui.tab;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import ui.table.EntityTableModel;

public abstract class TabPanel<T> extends JPanel {
	
	private final JPanel fieldsPanel;
	private final JPanel buttonPanel;
	private final JTable table;
	protected final EntityTableModel<T> model;
	
	public TabPanel(int fieldRows, int fieldColumns, EntityTableModel<T> model) {
		this.model = model;
		
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		
		fieldsPanel = new JPanel(new GridLayout(fieldRows, fieldColumns, 20, 0));
		topPanel.add(fieldsPanel);
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(buttonPanel);
		
		add(topPanel, BorderLayout.NORTH);
		
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);  
        
        add(scrollPane, BorderLayout.CENTER);
	}
	
	protected final void addField(JComponent field, String label) {
		JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        fieldsPanel.add(panel);
	}
	
	protected final void addField(JComponent field) {
		fieldsPanel.add(field);
	}
	
	protected final void addButton(JButton button) {
		buttonPanel.add(button);
	}
	
	protected int getSelectedRow() {
		return table.getSelectedRow();
	}
	
	protected final void createErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
