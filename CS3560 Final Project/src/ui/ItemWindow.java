package ui;

import javax.swing.*;
import java.awt.*;

import model.Item;

public class ItemWindow extends JFrame{

    private JTextField codeField;
    private JTextField titleField;
    private JTextField locationField;
    private JTextField lengthField;
    private JTextField publishDateField;
    private JTextField creatorField;
    private JTextField dailyPriceField;
    private JTextArea descriptionField;
    private JTextField authorsField;        // only displays if item is book
    private JTextField studentIdField;
    private JButton loanButton;
    private JButton button;                 // displays "Edit" or "Save" depending on when clicked
    private JPanel panel;

    private Item item;

    public ItemWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        setTitle("Item");   //TODO change title based on whether book or film
        

        codeField = new JTextField(20);
        titleField = new JTextField(20);
        locationField = new JTextField(20);
        lengthField = new JTextField(20);
        publishDateField = new JTextField(20);
        creatorField = new JTextField(20);
        dailyPriceField = new JTextField(20);
        descriptionField = new JTextArea();
        authorsField = new JTextField(20);  // TODO turn this into a list of authors
        studentIdField = new JTextField(20);
        loanButton = new JButton();
        button = new JButton();

        // Panels
        JPanel codePanel = new JPanel(new BorderLayout());
        codePanel.add(new JLabel("Code"),BorderLayout.NORTH);
		codePanel.add(codeField,BorderLayout.SOUTH);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(new JLabel("Title"),BorderLayout.NORTH);
		titlePanel.add(titleField,BorderLayout.SOUTH);

        JPanel locationPanel = new JPanel(new BorderLayout());
        locationPanel.add(new JLabel("Location"),BorderLayout.NORTH);
		locationPanel.add(locationField,BorderLayout.SOUTH);

        JPanel lengthPanel = new JPanel(new BorderLayout());
        // TODO "Pages" if book, "Length" if film
        lengthPanel.add(new JLabel("Length"),BorderLayout.NORTH);
		lengthPanel.add(lengthField,BorderLayout.SOUTH);

        JPanel publishDatePanel = new JPanel(new BorderLayout());
        // TODO "Publish Date" if book, "Release Date" if film
        publishDatePanel.add(new JLabel("Publish Date"),BorderLayout.NORTH);
		publishDatePanel.add(publishDateField,BorderLayout.SOUTH);

        JPanel creatorPanel = new JPanel(new BorderLayout());
        // TODO "Publisher" if book, "Author" if film
        creatorPanel.add(new JLabel("Creator"),BorderLayout.NORTH);
		creatorPanel.add(creatorField,BorderLayout.SOUTH);

        JPanel dailyPricePanel = new JPanel(new BorderLayout());
        dailyPricePanel.add(new JLabel("Daily Price"),BorderLayout.NORTH);
		dailyPricePanel.add(publishDateField,BorderLayout.SOUTH);

        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.add(new JLabel("Description"),BorderLayout.NORTH);
		descriptionPanel.add(descriptionField,BorderLayout.SOUTH);

        // TODO turn into a list of authors
        JPanel authorsPanel = new JPanel(new BorderLayout());
        authorsPanel.add(new JLabel("Authors"),BorderLayout.NORTH);
		authorsPanel.add(authorsField,BorderLayout.SOUTH);

        // TODO the box in the corner for loans, see mockup
        JPanel loanPanel = new JPanel(new BorderLayout());
        loanPanel.add(new JLabel("Loans"),BorderLayout.NORTH);

        JPanel studentIdPanel = new JPanel(new BorderLayout());
        studentIdPanel.add(new JLabel("Student Bronco ID"),BorderLayout.NORTH);
		studentIdPanel.add(studentIdField,BorderLayout.SOUTH);

        loanPanel.add(studentIdPanel,BorderLayout.SOUTH);
        loanPanel.add(loanButton,BorderLayout.SOUTH);

        // TODO fix format
        panel = new JPanel(new GridLayout(3,5, 20, 0));

        panel.add(codePanel);
        panel.add(titlePanel);
        panel.add(lengthPanel);
        panel.add(locationPanel);
        panel.add(publishDatePanel);
        panel.add(creatorPanel);
        panel.add(dailyPricePanel);
        //panel.add(descriptionPanel);

        //TODO if book, add authors list panel

        //panel.add(button);
        //panel.add(loanPanel);

        
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
