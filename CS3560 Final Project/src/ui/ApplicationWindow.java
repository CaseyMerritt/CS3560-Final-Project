package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class ApplicationWindow extends JFrame{
    public ApplicationWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 650));
        setTitle("Library Management");
        setVisible(true);

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
    }
}