package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ItemTabPanel extends JPanel {

    private JTextField codeField;
    private JTextField titleField;
    private JTextField locationField;
    private JTextField maxDailyPrice;
    private JTextField minDailyPrice;
    private JTextField maxPageLength;
    private JTextField minPageLength;
    private JTextField publisher;
    private JTextField authorOrDirector;
    private JTextField publishedAfter;
    private JTextField publishedBefore;
    private JCheckBox showAvailiable;

    private JTable table;
    
    public ItemTabPanel() {
        setLayout(new BorderLayout());

        // Create input fields.
        codeField = new JTextField(20);
        titleField = new JTextField(20);
        locationField = new JTextField(20);
        maxDailyPrice = new JTextField(20);
        minDailyPrice = new JTextField(20);
        showAvailiable = new JCheckBox("Only Show Avaliable");
        maxPageLength = new JTextField(20);
        minPageLength  = new JTextField(20);
        publisher  = new JTextField(20);
        authorOrDirector  = new JTextField(20);
        publishedAfter  = new JTextField(20);
        publishedBefore  = new JTextField(20);
        
        // Create a panel to hold the fields.
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(3, 5, 20, 0));
        
        //Create Panels
        JPanel empty1 = new JPanel(new BorderLayout());
        JPanel empty2 = new JPanel(new BorderLayout());

        JPanel code = new JPanel(new BorderLayout());
        code.add(new JLabel("Code"), BorderLayout.NORTH);
        code.add(codeField, BorderLayout.CENTER);

        JPanel title = new JPanel(new BorderLayout());
        title.add(new JLabel("Title"), BorderLayout.NORTH);
        title.add(titleField, BorderLayout.CENTER);

        JPanel location = new JPanel(new BorderLayout());
        location.add(new JLabel("Location"), BorderLayout.NORTH);
        location.add(locationField, BorderLayout.CENTER);

        JPanel mxDaily = new JPanel(new BorderLayout());
        mxDaily.add(new JLabel("Max Daily Price"), BorderLayout.NORTH);
        mxDaily.add(maxDailyPrice, BorderLayout.CENTER);

        JPanel mnDaily = new JPanel(new BorderLayout());
        mnDaily.add(new JLabel("Min Daily Price"), BorderLayout.NORTH);
        mnDaily.add(minDailyPrice, BorderLayout.CENTER);

        JPanel mxPL = new JPanel(new BorderLayout());
        mxPL.add(new JLabel("Max Pages Length"), BorderLayout.NORTH);
        mxPL.add(maxPageLength, BorderLayout.CENTER);

        JPanel mnPL = new JPanel(new BorderLayout());
        mnPL.add(new JLabel("Min Pages Length"), BorderLayout.NORTH);
        mnPL.add(minPageLength, BorderLayout.CENTER);

        JPanel pblshr = new JPanel(new BorderLayout());
        pblshr.add(new JLabel("Publisher"), BorderLayout.NORTH);
        pblshr.add(publisher, BorderLayout.CENTER);

        JPanel aOd = new JPanel(new BorderLayout());
        aOd.add(new JLabel("Author/Director"), BorderLayout.NORTH);
        aOd.add(authorOrDirector, BorderLayout.CENTER);

        JPanel pblshAfter = new JPanel(new BorderLayout());
        pblshAfter.add(new JLabel("Published Release After"), BorderLayout.NORTH);
        pblshAfter.add(publishedAfter, BorderLayout.CENTER);

        JPanel pblshBefore = new JPanel(new BorderLayout());
        pblshBefore.add(new JLabel("Published Release Before"), BorderLayout.NORTH);
        pblshBefore.add(publishedBefore, BorderLayout.CENTER);

            // Create radio buttons for the "Book" and "Film" options.
        JRadioButton bookButton = new JRadioButton("Book");
        JRadioButton filmButton = new JRadioButton("Film");

        // Group the radio buttons to make them mutually exclusive.
        ButtonGroup group = new ButtonGroup();
        group.add(bookButton);
        group.add(filmButton);

        // Create a panel for the radio buttons.
        JPanel radioPanel = new JPanel();
        radioPanel.add(bookButton);
        radioPanel.add(filmButton);

        fieldsPanel.add(code);
        fieldsPanel.add(mxDaily);
        fieldsPanel.add(radioPanel);
        fieldsPanel.add(empty1);
        fieldsPanel.add(empty2);
        fieldsPanel.add(title);
        fieldsPanel.add(mnDaily);
        fieldsPanel.add(mxPL);//max pages length
        fieldsPanel.add(pblshAfter);//published released after
        fieldsPanel.add(pblshr);//publisher
        fieldsPanel.add(location);
        fieldsPanel.add(showAvailiable);
        fieldsPanel.add(mnPL);//min pages length
        fieldsPanel.add(pblshBefore);//published released before
        fieldsPanel.add(aOd);//Author/Director

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // Handle search.
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            // Handle Reset.

            codeField.setText("");
            titleField.setText("");;
            locationField.setText("");;
            maxDailyPrice.setText("");;
            minDailyPrice.setText("");;
            maxPageLength.setText("");;
            minPageLength.setText("");
            publisher.setText("");
            authorOrDirector.setText("");
            publishedAfter.setText("");
            publishedBefore.setText("");
            showAvailiable.setSelected(false);
            group.clearSelection();
        });

        // Add add button.
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            // Handle Add.
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            // Handle delete.
            public void actionPerformed(ActionEvent e) {
                // Get Item id from user
                String searchLoanTerm = JOptionPane.showInputDialog("Enter Code To Delete");

                try{
                    int itemId = Integer.parseInt(searchLoanTerm);
                    // delete item in database
                    // database.deleteItem(itemId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Item Code.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });        

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Create table with 10 columns.
        String[] columnNames = {
            "Code", "Title", "Description", "Location", "Daily Price",
            "Pages/Length", "Release/Publish Date", "Publisher", "Availability", ""
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Create a JScrollPane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the fields and buttons to the main panel.
        add(fieldsPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }
}