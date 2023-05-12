package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.Item;
import model.Book;
import model.Film;
import model.Author;

public class ItemWindow extends JFrame{

    private JTextField codeField;
    private JTextField titleField;
    private JTextField locationField;
    private JTextField lengthField;
    private JTextField publishField;
    private JTextField creatorField;
    private JTextField dailyPriceField;
    private JTextArea descriptionField;
    private JList<Author> authorsField;     // only displays if item is book
    private JTextField studentIdField;
    private JButton loanButton;
    private JButton button;                 // displays "Edit" or "Save" depending on when clicked
    private JPanel panel;

    private Item item;

    public ItemWindow(Item item){
        setupWindow(item);
        // TODO get data from existing book or film object
        // TODO create new book or film
        // TODO edit/save button functionality
        // TODO loan button functionality
    }

    public void setupWindow(Item item){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        if(item instanceof Book){
            setTitle("Book");
        } else if (item instanceof Film){
            setTitle("Film");
        } else {
            setTitle("Item");
        }
        setVisible(true);

        codeField = new JTextField(20);
        titleField = new JTextField(20);
        locationField = new JTextField(20);
        lengthField = new JTextField(20);
        publishField = new JTextField(20);
        creatorField = new JTextField(20);
        dailyPriceField = new JTextField(20);
        descriptionField = new JTextArea(5,50);
        if(item instanceof Book){
            authorsField = new JList<Author>();
        } else {
            authorsField = null;
        }
        studentIdField = new JTextField(20);
        loanButton = new JButton();
        button = new JButton();

        // Field Panel
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
        if(item instanceof Book){
            lengthPanel.add(new JLabel("Pages"),BorderLayout.NORTH);
        } else {
            lengthPanel.add(new JLabel("Length"),BorderLayout.NORTH);
        }
		lengthPanel.add(lengthField,BorderLayout.SOUTH);

        JPanel publishDatePanel = new JPanel(new BorderLayout());
        if(item instanceof Book){
            publishDatePanel.add(new JLabel("Publish Date"),BorderLayout.NORTH);
        } else {
            publishDatePanel.add(new JLabel("Release Date"),BorderLayout.NORTH);
        }
		publishDatePanel.add(publishField,BorderLayout.SOUTH);

        JPanel creatorPanel = new JPanel(new BorderLayout());
        if(item instanceof Book){
            creatorPanel.add(new JLabel("Publisher"),BorderLayout.NORTH);
        } else if (item instanceof Film){
            creatorPanel.add(new JLabel("Author"),BorderLayout.NORTH);
        } else {
            creatorPanel.add(new JLabel("Creator"),BorderLayout.NORTH);
        }
		creatorPanel.add(creatorField,BorderLayout.SOUTH);

        JPanel dailyPricePanel = new JPanel(new BorderLayout());
        dailyPricePanel.add(new JLabel("Daily Price"),BorderLayout.NORTH);
		dailyPricePanel.add(dailyPriceField,BorderLayout.SOUTH);

        JPanel fieldPanel = new JPanel(new GridLayout(2, 4));
        fieldPanel.add(codePanel);
        fieldPanel.add(titlePanel);
        fieldPanel.add(lengthPanel);
        fieldPanel.add(locationPanel);
        fieldPanel.add(publishDatePanel);
        fieldPanel.add(creatorPanel);
        fieldPanel.add(dailyPricePanel);

        // Middle Panel
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.add(new JLabel("Description"),BorderLayout.NORTH);
		descriptionPanel.add(descriptionField,BorderLayout.SOUTH);

        JPanel authorsPanel = new JPanel(new BorderLayout());
        authorsPanel.add(new JLabel("Authors"),BorderLayout.NORTH);
		authorsPanel.add(new JScrollPane(authorsField),BorderLayout.SOUTH);

        JPanel middlePanel = new JPanel(new BorderLayout(1,2));
        middlePanel.add(descriptionPanel,BorderLayout.WEST);
        if(item instanceof Book){
            middlePanel.add(authorsPanel,BorderLayout.EAST);
        }

        // Bottom Panel
        JPanel loanPanel = new JPanel(new BorderLayout());
        loanPanel.add(new JLabel("Loans"),BorderLayout.NORTH);

        JPanel loanBoxPanel = new JPanel(new BorderLayout());

        JPanel studentIdPanel = new JPanel(new BorderLayout());
        studentIdPanel.add(new JLabel("Student Bronco ID"),BorderLayout.NORTH);
		studentIdPanel.add(studentIdField,BorderLayout.SOUTH);

        loanBoxPanel.add(studentIdPanel,BorderLayout.WEST);
        loanBoxPanel.add(loanButton,BorderLayout.EAST);
        loanButton.setText("Loan");
        loanBoxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        loanPanel.add(loanBoxPanel);

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 5;
        c.gridheight = 2;
        

        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.SOUTHWEST;
        bottomPanel.add(button,c);

        c.gridx = 1;
        bottomPanel.add(Box.createHorizontalStrut(150),c);

        c.weightx = 2;
        c.gridx = 4;
        c.gridy = 1;
        c.anchor = GridBagConstraints.SOUTHEAST;
        bottomPanel.add(loanBoxPanel,c);

        
        button.setText("Edit");
        //TODO when button is clicked, change text to "Save"

        panel = new JPanel(new GridLayout(3,1));
        panel.add(fieldPanel);
        panel.add(middlePanel);
        panel.add(bottomPanel);

        add(panel);
        pack();
    }

}
