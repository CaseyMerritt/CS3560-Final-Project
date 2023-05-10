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

class ItemTabPanel extends JPanel {

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

class LoansTabPanel extends JPanel {
    private JTextField numberField;
    private JTextField titleField;
    private JTextField broncoIdField;
    private JTextField nameField;
    private JTextField loanedAfterField;
    private JTextField loanedBeforeField;
    private JTextField dueAfterField;
    private JTextField dueBeforeField;
    private JTextField CourseField;
    private JCheckBox showOverDue;

    private JTable table;

    public LoansTabPanel() {
        setLayout(new BorderLayout());

        // Create input fields.
        numberField = new JTextField(20);
        titleField = new JTextField(20);
        broncoIdField = new JTextField(20);
        nameField = new JTextField(20);
        loanedAfterField = new JTextField(20);
        loanedBeforeField = new JTextField(20);
        dueAfterField  = new JTextField(20);
        dueBeforeField  = new JTextField(20);
        CourseField  = new JTextField(20);
        showOverDue = new JCheckBox("Only Show Overdue");

        // Create a panel to hold the fields.
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2, 5, 20, 0));

        JPanel numberPanel = new JPanel(new BorderLayout());
        numberPanel.add(new JLabel("Number"), BorderLayout.NORTH);
        numberPanel.add(numberField, BorderLayout.CENTER);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(new JLabel("Item Title"), BorderLayout.NORTH);
        titlePanel.add(titleField, BorderLayout.CENTER);

        JPanel broncoIdPanel = new JPanel(new BorderLayout());
        broncoIdPanel.add(new JLabel("Student Bronco ID"), BorderLayout.NORTH);
        broncoIdPanel.add(broncoIdField, BorderLayout.CENTER);

        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.add(new JLabel("Student Name"), BorderLayout.NORTH);
        namePanel.add(nameField, BorderLayout.CENTER);

        JPanel dueAfterPanel = new JPanel(new BorderLayout());
        dueAfterPanel.add(new JLabel("Due After"), BorderLayout.NORTH);
        dueAfterPanel.add(dueAfterField, BorderLayout.CENTER);

        JPanel dueBeforePanel = new JPanel(new BorderLayout());
        dueBeforePanel.add(new JLabel("Due Before"), BorderLayout.NORTH);
        dueBeforePanel.add(dueBeforeField, BorderLayout.CENTER);

        JPanel loanedAfterPanel = new JPanel(new BorderLayout());
        loanedAfterPanel.add(new JLabel("Loaned After"), BorderLayout.NORTH);
        loanedAfterPanel.add(loanedAfterField, BorderLayout.CENTER);

        JPanel loanedBeforePanel = new JPanel(new BorderLayout());
        loanedBeforePanel.add(new JLabel("Loaned Before"), BorderLayout.NORTH);
        loanedBeforePanel.add(loanedBeforeField, BorderLayout.CENTER);

        JPanel coursePanel = new JPanel(new BorderLayout());
        coursePanel.add(new JLabel("Course"), BorderLayout.NORTH);
        coursePanel.add(CourseField, BorderLayout.CENTER);

        fieldsPanel.add(numberPanel);
        fieldsPanel.add(broncoIdPanel);
        fieldsPanel.add(loanedAfterPanel);
        fieldsPanel.add(dueAfterPanel);
        fieldsPanel.add(coursePanel);
        fieldsPanel.add(titlePanel);
        fieldsPanel.add(namePanel);
        fieldsPanel.add(loanedBeforePanel);
        fieldsPanel.add(dueBeforePanel);
        fieldsPanel.add(showOverDue);

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // Handle search.
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            // Handle Reset.

            numberField.setText("");
            titleField.setText("");
            broncoIdField.setText("");
            nameField.setText("");
            loanedAfterField.setText("");
            loanedBeforeField.setText("");
            dueAfterField.setText("");
            dueBeforeField.setText("");
            CourseField.setText("");
            showOverDue.setText("");
            showOverDue.setSelected(false);
        });

        // Add add button.
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            // Handle Add.
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            //Handle delete.
            public void actionPerformed(ActionEvent e) {
                // Get loan id from user
                String deleteLoanTerm = JOptionPane.showInputDialog("Enter Number To Delete");

                try{
                    int loanId = Integer.parseInt(deleteLoanTerm);
                    // delete loan in database
                    // database.deleteItem(loanId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid loan Number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Add Revenue button.
        JButton revenueButton = new JButton("Revenue Report");
        addButton.addActionListener(e -> {
            // Handle Revenue Report.
        });        

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(revenueButton);

        // Create table with 8 columns.
        String[] columnNames = {
            "Number", "Item Title", "Student Bronco ID", "Student Name", "Course", "Loan Date", "Due Date", ""
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

class StudentsTabPanel extends JPanel {
    private JTextField broncoIdField;
    private JTextField nameField;

    private JTable table;

    public StudentsTabPanel() {
        setLayout(new BorderLayout());

        // Create input fields.
        nameField = new JTextField(20);
        broncoIdField = new JTextField(20);

        // Create a panel to hold the fields.
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(1, 3, 20, 0));

        JPanel broncoIdPanel = new JPanel(new BorderLayout());
        broncoIdPanel.add(new JLabel("Bronco ID"), BorderLayout.NORTH);
        broncoIdPanel.add(broncoIdField, BorderLayout.CENTER);

        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.add(new JLabel("Name"), BorderLayout.NORTH);
        namePanel.add(nameField, BorderLayout.CENTER);

        
        fieldsPanel.add(broncoIdPanel);
        fieldsPanel.add(namePanel);

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // Handle search.
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            // Handle Reset.
            nameField.setText("");
            broncoIdField.setText("");
        });

        // Add Add button.
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            // Handle Add.
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            // Handle delete.
            public void actionPerformed(ActionEvent e) {
                // Get bronco id from user
                String deleteStudentTerm = JOptionPane.showInputDialog("Enter Bronco ID To Delete");

                try{
                    int broncoId = Integer.parseInt(deleteStudentTerm);
                    // delete Student in database
                    // database.deleteStudent(broncoId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid loan Number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });           

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Create table with 7 columns.
        String[] columnNames = {
            "Bronco ID", "Name", "Items Loaned", "Items Overdue", "Balance", ""
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

class AuthorsTabPanel extends JPanel {
    private JTextField nameField;
    private JTextField nationalityField;
    private JTextField styleField;

    private JTable table;

    public AuthorsTabPanel() {
        setLayout(new BorderLayout());

        // Create input fields.
        nameField = new JTextField(20);
        nationalityField = new JTextField(20);
        styleField = new JTextField(20);

        // Create a panel to hold the fields.
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(1, 3, 20, 0));

        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.add(new JLabel("Code"), BorderLayout.NORTH);
        namePanel.add(nameField, BorderLayout.CENTER);

        JPanel nationalityPanel = new JPanel(new BorderLayout());
        nationalityPanel.add(new JLabel("Title"), BorderLayout.NORTH);
        nationalityPanel.add(nationalityField, BorderLayout.CENTER);

        JPanel stylePanel = new JPanel(new BorderLayout());
        stylePanel.add(new JLabel("Location"), BorderLayout.NORTH);
        stylePanel.add(styleField, BorderLayout.CENTER);

        
        fieldsPanel.add(namePanel);//min pages length
        fieldsPanel.add(nationalityPanel);//published released before
        fieldsPanel.add(stylePanel);//Author/Director

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // Handle search.
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            // Handle Reset.
            nameField.setText("");
            nationalityField.setText("");
            styleField.setText("");
        });

        // Add Add button.
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            // Handle Add.
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            // Handle delete.
            public void actionPerformed(ActionEvent e) {
                // Get author from user
                String name = JOptionPane.showInputDialog("Enter Author Name To Delete");
                // delete Author in database
                // database.deleteAuthor(name);
            }
        });           

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Create table with 5 columns.
        String[] columnNames = {
            "Name", "Nationality", "Subject", "Books", ""
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

class DirectorsTabPanel extends JPanel {
    private JTextField nameField;
    private JTextField nationalityField;
    private JTextField styleField;

    private JTable table;

    public DirectorsTabPanel() {
        setLayout(new BorderLayout());

        // Create input fields.
        nameField = new JTextField(20);
        nationalityField = new JTextField(20);
        styleField = new JTextField(20);

        // Create a panel to hold the fields.
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(1, 3, 20, 0));

        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.add(new JLabel("Code"), BorderLayout.NORTH);
        namePanel.add(nameField, BorderLayout.CENTER);

        JPanel nationalityPanel = new JPanel(new BorderLayout());
        nationalityPanel.add(new JLabel("Title"), BorderLayout.NORTH);
        nationalityPanel.add(nationalityField, BorderLayout.CENTER);

        JPanel stylePanel = new JPanel(new BorderLayout());
        stylePanel.add(new JLabel("Location"), BorderLayout.NORTH);
        stylePanel.add(styleField, BorderLayout.CENTER);

        
        fieldsPanel.add(namePanel);//min pages length
        fieldsPanel.add(nationalityPanel);//published released before
        fieldsPanel.add(stylePanel);//Author/Director

        // Add search button.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // Handle search.
        });

        // Add reset button button.
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            // Handle Reset.

            nameField.setText("");
            nationalityField.setText("");
            styleField.setText("");
        });

        // Add Add button.
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            // Handle Add.
        });

        // Add delete button.
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            // Handle delete.
            public void actionPerformed(ActionEvent e) {
                // Get name from user
                String name = JOptionPane.showInputDialog("Enter Director Name To Delete");
                // delete Director in database
                // database.deleteAuthor(name);
            }
        });           

        // Add buttons to the panel.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Create table with 5 columns.
        String[] columnNames = {
            "Name", "Nationality", "Style", "Films", ""
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
    
