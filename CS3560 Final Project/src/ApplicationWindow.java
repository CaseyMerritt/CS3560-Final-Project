import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ApplicationWindow extends JFrame{
    public ApplicationWindow(){
        //Set title of the JFrame
        this.setTitle("Library");

        // Create a panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create title
        JLabel titleLabel = new JLabel("Library", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 35));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        // Create buttons
        JButton loanButton = new JButton("Loans");
        JButton itemsButton = new JButton("Items");
        JButton studentsButton = new JButton("Students");
        JButton authorsButton = new JButton("Authors");
        JButton directorsButton = new JButton("Directors");

        // Add action listeners to buttons
        loanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open Loans panel
                openLoans();
            }
        });

        itemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open Items panel
                openItems();
            }
        });

        studentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open Students panel
                openStudents();
            }
        });

        authorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open Authors panel
                openAuthors();
            }
        });

        directorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open Directors panel
                openDirectors();
            }
        });

        // Add buttons to panel
        buttonPanel.add(loanButton);
        buttonPanel.add(itemsButton);
        buttonPanel.add(studentsButton);
        buttonPanel.add(authorsButton);
        buttonPanel.add(directorsButton);

        // Add button panel to main panel
        panel.add(buttonPanel, BorderLayout.CENTER);


        // Add panel to JFrame
        this.add(panel);
        this.setSize(500, 500);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // Method to open Loans Dialog
    private void openLoans() {
        JPanel loansPanel = new JPanel();
        loansPanel.setLayout(new GridLayout(2, 1));

        JButton searchButton = new JButton("Search Loans");
        JButton reportButton = new JButton("Generate Report");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get loan id from user
                String searchLoanTerm = JOptionPane.showInputDialog("Enter Loan Id");

                try{
                    int loanId = Integer.parseInt(searchLoanTerm);
                    // Search loans in database
                    // database.searchLoans(loanId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid loan ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Generate financial report
                // financialReport.generate();
            }
        });

        loansPanel.add(searchButton);
        loansPanel.add(reportButton);

        JOptionPane.showMessageDialog(null, loansPanel, "Loans", JOptionPane.PLAIN_MESSAGE);
    }

    private void openItems() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(2, 1));

        JButton searchButton = new JButton("Search Items");
        JButton deleteButton = new JButton("Delete Item");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get loan id from user
                String searchItemTerm = JOptionPane.showInputDialog("Enter Item Id");

                try{
                    int itemId = Integer.parseInt(searchItemTerm);
                    // Search item in database
                    // database.searchItem(itemId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid item ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get loan id from user
                String searchItemTerm = JOptionPane.showInputDialog("Enter Item Id To Delete");

                try{
                    int itemId = Integer.parseInt(searchItemTerm);
                    // Delete item in database
                    // database.deleteItem(itemId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid item ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        itemsPanel.add(searchButton);
        itemsPanel.add(deleteButton);

        JOptionPane.showMessageDialog(null, itemsPanel, "Items", JOptionPane.PLAIN_MESSAGE);
    }

    private void openStudents() {
        JPanel studentsPanel = new JPanel();
        studentsPanel.setLayout(new GridLayout(3, 1));

        JButton searchButton = new JButton("Search Students");
        JButton deleteButton = new JButton("Delete Student");
        JButton changeButton = new JButton("Change Student");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get Student id from user
                String searchStudentTerm = JOptionPane.showInputDialog("Enter Student Id");

                try{
                    int studentId = Integer.parseInt(searchStudentTerm);
                    // Search Student in database
                    // database.searchStudent(studentId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get Student id from user
                String searchStudentTerm = JOptionPane.showInputDialog("Enter Student Id");

                try{
                    int studentId = Integer.parseInt(searchStudentTerm);
                    // delete Student in database
                    // database.deleteStudent(studentId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change Student in database
                // database.changeStudent();
            }
        });

        studentsPanel.add(searchButton);
        studentsPanel.add(deleteButton);
        studentsPanel.add(changeButton);

        JOptionPane.showMessageDialog(null, studentsPanel, "Students", JOptionPane.PLAIN_MESSAGE);
    }

    private void openAuthors() {
        JPanel authorsPanel = new JPanel();
        authorsPanel.setLayout(new GridLayout(3, 1));

        JButton searchButton = new JButton("Search Authors");
        JButton deleteButton = new JButton("Delete Author");
        JButton changeButton = new JButton("Change Author");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get Author id from user
                String searchAuthorTerm = JOptionPane.showInputDialog("Enter Author Id");

                try{
                    int authorId = Integer.parseInt(searchAuthorTerm);
                    // search Author in database
                    // database.searchAuthor(authorId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Author ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get Author id from user
                String searchAuthorTerm = JOptionPane.showInputDialog("Enter Author Id");

                try{
                    int authorId = Integer.parseInt(searchAuthorTerm);
                    // delete Author in database
                    // database.deleteAuthor(authorId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Author ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change author in database
                // database.changeAuthor();
            }
        });

        authorsPanel.add(searchButton);
        authorsPanel.add(deleteButton);
        authorsPanel.add(changeButton);

        JOptionPane.showMessageDialog(null, authorsPanel, "Authors", JOptionPane.PLAIN_MESSAGE);
    }

    private void openDirectors() {
        JPanel directorsPanel = new JPanel();
        directorsPanel.setLayout(new GridLayout(3, 1));

        JButton searchButton = new JButton("Search Directors");
        JButton deleteButton = new JButton("Delete Director");
        JButton changeButton = new JButton("Change Director");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get Director id from user
                String searchDirectorTerm = JOptionPane.showInputDialog("Enter Director Id");

                try{
                    int directorId = Integer.parseInt(searchDirectorTerm);
                    // search Director in database
                    // database.searchAuthor(directorId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Director ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get Director id from user
                String searchDirectorTerm = JOptionPane.showInputDialog("Enter Director Id");

                try{
                    int directorId = Integer.parseInt(searchDirectorTerm);
                    // delete Director in database
                    // database.deleteAuthor(directorId);
                }catch (NumberFormatException ex){
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Director ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change directors in database
                // database.changeDirectors();
            }
        });

        directorsPanel.add(searchButton);
        directorsPanel.add(deleteButton);
        directorsPanel.add(changeButton);

        JOptionPane.showMessageDialog(null, directorsPanel, "Directors", JOptionPane.PLAIN_MESSAGE);
    }
}
