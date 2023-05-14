package ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import org.hibernate.HibernateException;

import database.HibernateSessionFactory;
import ui.tab.AuthorsTabPanel;
import ui.tab.DirectorsTabPanel;
import ui.tab.ItemTabPanel;
import ui.tab.LoansTabPanel;
import ui.tab.StudentsTabPanel;

public class ApplicationWindow extends JFrame{
	
	public static void main(String[] args) {
		try {
			HibernateSessionFactory.getSessionFactory();
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(null, "Unable to connect to database!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		new ApplicationWindow();
	}
	
    public ApplicationWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 650));
        setTitle("Library Management");
        

        // Create the tabbed pane.
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs.
        tabbedPane.addTab("Loans", new LoansTabPanel());
        tabbedPane.addTab("Items", new ItemTabPanel());
        tabbedPane.addTab("Students", new StudentsTabPanel());
        tabbedPane.addTab("Authors", new AuthorsTabPanel());
        tabbedPane.addTab("Directors", new DirectorsTabPanel());

        add(tabbedPane);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}