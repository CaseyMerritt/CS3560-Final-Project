package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Author;
import model.Director;
import model.Book;
import model.Film;
import model.Item;

public class ItemWindow extends JFrame{

	public static final int BOOK = 1;
	public static final int FILM = 2;
	
    private JTextField codeField;
    private JTextField titleField;
    private JTextField locationField;
    private JTextField lengthField;
    private JTextField publishDateField;
    private JTextField publisherField;
    private JTextField dailyPriceField;
    private JTextArea descriptionField;
    
    private JList<Author> authorsField;
    private JComboBox<Director> directorField;
    
    private DefaultListModel<Author> model;
    
    private JButton button;

    private Item item;

    public ItemWindow(int itemType){
        setupWindow(itemType);
        
        if (itemType == BOOK) {
        	createBook();
        } else {
        	createFilm();
        }
    }
    
    public ItemWindow(Item i) {
    	setupWindow(i instanceof Book ? BOOK : FILM);
    	
    	item = i;
    	codeField.setText(i.getCode() + "");
    	titleField.setText(i.getTitle());
    	descriptionField.setText(i.getDescription());
    	locationField.setText(i.getLocation());
    	dailyPriceField.setText(i.getDailyPrice() + "");
    	
    	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    	
    	if (i instanceof Book) {
    		Book b = (Book) i;
    		publishDateField.setText(format.format(b.getPublicationDate()));
    		lengthField.setText(b.getPages() + "");
    		publisherField.setText(b.getPublisher());
    		
    		updateBook();
    	} else {
    		Film f = (Film) i;
    		publishDateField.setText(format.format(f.getReleaseDate()));
    		lengthField.setText(f.getLength() + "");
    		
    		updateFilm();
    	}
    }

	private void createFilm() {
		List<Director> directors = Director.findBy(null, null, null);
		for (Director d : directors) {
			directorField.addItem(d);
		}
		
		List<Author> authors = Author.findBy(null, null, null);
		model.addAll(authors);
		authorsField.setSelectedIndex(0);
		
		button.addActionListener(e -> {
			String title = titleField.getText().trim();
			String location = locationField.getText().trim();
			String length = lengthField.getText().trim();
			String releaseDate = publishDateField.getText().trim();
			String dailyPrice = dailyPriceField.getText().trim();
			String description = descriptionField.getText().trim();
			Director selDir = (Director) directorField.getSelectedItem();
			
			int lenInt = 0;
			try {
				lenInt = Integer.parseInt(length);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Invalid length!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
			
			Date pubDate;
			try {
				pubDate = f.parse(releaseDate);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(this, "Invalid release date!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			double price;
			try {
				price = Double.parseDouble(dailyPrice);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Invalid daily price!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (title.isBlank() || location.isBlank() || description.isBlank()) {
				JOptionPane.showMessageDialog(this, "Invalid fields!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (selDir == null) {
				JOptionPane.showMessageDialog(this, "Invalid director!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Film film = new Film(title, description, location, price);
			film.setDirector(selDir);
			film.setLength(lenInt);
			film.setReleaseDate(pubDate);
			film.setAvailable(true);
			film.create();
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
	}
	
	private void updateFilm() {
		List<Director> directors = Director.findBy(null, null, null);
		for (Director d : directors) {
			directorField.addItem(d);
		}
		
		Film currFilm = (Film) item;
		directorField.setSelectedItem(currFilm.getDirector());
		
		button.addActionListener(e -> {
			String title = titleField.getText().trim();
			String location = locationField.getText().trim();
			String length = lengthField.getText().trim();
			String releaseDate = publishDateField.getText().trim();
			String dailyPrice = dailyPriceField.getText().trim();
			String description = descriptionField.getText().trim();
			Director selDir = (Director) directorField.getSelectedItem();
			
			int lenInt = 0;
			try {
				lenInt = Integer.parseInt(length);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Invalid length!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
			
			Date pubDate;
			try {
				pubDate = f.parse(releaseDate);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(this, "Invalid release date!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			double price;
			try {
				price = Double.parseDouble(dailyPrice);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Invalid daily price!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (title.isBlank() || location.isBlank() || description.isBlank()) {
				JOptionPane.showMessageDialog(this, "Invalid fields!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (selDir == null) {
				JOptionPane.showMessageDialog(this, "Invalid director!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			currFilm.setTitle(title);
			currFilm.setDescription(description);
			currFilm.setDailyPrice(price);
			currFilm.setLocation(location);
			currFilm.setDirector(selDir);
			currFilm.setLength(lenInt);
			currFilm.setReleaseDate(pubDate);
			currFilm.update();
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
	}

	private void createBook() {
		List<Author> authors = Author.findBy(null, null, null);
		model.addAll(authors);
		authorsField.setSelectedIndex(0);
		
		button.addActionListener(e -> {
			String title = titleField.getText().trim();
			String location = locationField.getText().trim();
			String length = lengthField.getText().trim();
			String publishDate = publishDateField.getText().trim();
			String publisher = publisherField.getText().trim();
			String dailyPrice = dailyPriceField.getText().trim();
			String description = descriptionField.getText().trim();
			List<Author> selAuthor = authorsField.getSelectedValuesList();
			
			int lenInt = 0;
			try {
				lenInt = Integer.parseInt(length);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Invalid pages!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
			
			Date pubDate;
			try {
				pubDate = f.parse(publishDate);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(this, "Invalid publish date!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			double price;
			try {
				price = Double.parseDouble(dailyPrice);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Invalid daily price!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (title.isBlank() || location.isBlank() || publisher.isBlank() || description.isBlank()) {
				JOptionPane.showMessageDialog(this, "Invalid fields!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (selAuthor.size() == 0) {
				JOptionPane.showMessageDialog(this, "Invalid author(s)!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Book book = new Book(title, description, location, price);
			book.setPages(lenInt);
			book.setPublicationDate(pubDate);
			book.setPublisher(publisher);
			book.setAuthors(selAuthor);
			book.setAvailable(true);
			book.create();
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
	}
		
	
	private void updateBook() {		
		Book currBook = (Book) item;
		
		List<Author> authors = Author.findBy(null, null, null);
		model.addAll(authors);
		
		for (Author a : currBook.getAuthors()) {
			authorsField.setSelectedValue(a, true);
			System.out.println("select");
		}
		
		button.addActionListener(e -> {
			String title = titleField.getText().trim();
			String location = locationField.getText().trim();
			String length = lengthField.getText().trim();
			String publishDate = publishDateField.getText().trim();
			String publisher = publisherField.getText().trim();
			String dailyPrice = dailyPriceField.getText().trim();
			String description = descriptionField.getText().trim();
			List<Author> selAuthor = authorsField.getSelectedValuesList();
			
			int lenInt = 0;
			try {
				lenInt = Integer.parseInt(length);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Invalid pages!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
			
			Date pubDate;
			try {
				pubDate = f.parse(publishDate);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(this, "Invalid publish date!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			double price;
			try {
				price = Double.parseDouble(dailyPrice);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Invalid daily price!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (title.isBlank() || location.isBlank() || publisher.isBlank() || description.isBlank()) {
				JOptionPane.showMessageDialog(this, "Invalid fields!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (selAuthor.size() == 0) {
				JOptionPane.showMessageDialog(this, "Invalid author(s)!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			currBook.setTitle(title);
			currBook.setDescription(description);
			currBook.setDailyPrice(price);
			currBook.setLocation(location);
			currBook.setAuthors(selAuthor);
			currBook.setPages(lenInt);
			currBook.setPublicationDate(pubDate);
			currBook.setPublisher(publisher);
			currBook.update();
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
	}

	private void setupWindow(int itemType) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        setLayout(new BorderLayout());
        
        if (itemType == BOOK)
        	setTitle("Book");
        else
        	setTitle("Film");

        codeField = new JTextField(20);
        titleField = new JTextField(20);
        locationField = new JTextField(20);
        lengthField = new JTextField(20);
        publishDateField = new JTextField(20);
        publisherField = new JTextField(20);
        dailyPriceField = new JTextField(20);
        descriptionField = new JTextArea();
        model = new DefaultListModel<>();
        authorsField = new JList<>(model);
        directorField = new JComboBox<>();
        
        authorsField.setSelectionModel(new DefaultListSelectionModel() {
        	@Override
        	public void setSelectionInterval(int index0, int index1) {
        		if (super.isSelectedIndex(index0)) {
        			super.removeSelectionInterval(index0, index1);
        		} else {
        			super.addSelectionInterval(index0, index1);
        		}
        	}
        });
        
        JScrollPane authorScroll = new JScrollPane(authorsField);

        // Panels
        JPanel codePanel = new JPanel(new BorderLayout());
        codePanel.add(new JLabel("Code"),BorderLayout.NORTH);
		codePanel.add(codeField,BorderLayout.SOUTH);
		codeField.setEditable(false);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(new JLabel("Title"),BorderLayout.NORTH);
		titlePanel.add(titleField,BorderLayout.SOUTH);

        JPanel locationPanel = new JPanel(new BorderLayout());
        locationPanel.add(new JLabel("Location"),BorderLayout.NORTH);
		locationPanel.add(locationField,BorderLayout.SOUTH);

        JPanel lengthPanel = new JPanel(new BorderLayout());
        lengthPanel.add(new JLabel(itemType == BOOK ? "Pages" : "Length"),BorderLayout.NORTH);
		lengthPanel.add(lengthField,BorderLayout.SOUTH);

        JPanel publishDatePanel = new JPanel(new BorderLayout());
        publishDatePanel.add(new JLabel((itemType == BOOK ? "Publish" : "Release") + " Date"),BorderLayout.NORTH);
		publishDatePanel.add(publishDateField,BorderLayout.SOUTH);

        JPanel publisherPanel = new JPanel(new BorderLayout());
        publisherPanel.add(new JLabel("Publisher"),BorderLayout.NORTH);
        publisherPanel.add(publisherField,BorderLayout.SOUTH);

        JPanel dailyPricePanel = new JPanel(new BorderLayout());
        dailyPricePanel.add(new JLabel("Daily Price"),BorderLayout.NORTH);
		dailyPricePanel.add(dailyPriceField,BorderLayout.SOUTH);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BorderLayout());
        
        descriptionPanel.add(new JLabel("Description"), BorderLayout.NORTH);
		descriptionPanel.add(descriptionField, BorderLayout.CENTER);

        JPanel creatorsPanel = new JPanel();
        creatorsPanel.setLayout(new BoxLayout(creatorsPanel, BoxLayout.Y_AXIS));
        creatorsPanel.add(new JLabel(itemType == BOOK ? "Authors" : "Director"));
        creatorsPanel.add(itemType == BOOK ? authorScroll : directorField);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        
        JPanel fieldPanel = new JPanel(new GridLayout(3, 3, 20, 0));
        fieldPanel.add(codePanel);
        fieldPanel.add(titlePanel);
        fieldPanel.add(lengthPanel);
        fieldPanel.add(locationPanel);
        if (itemType == BOOK) fieldPanel.add(publisherPanel);
        fieldPanel.add(publishDatePanel);
        fieldPanel.add(dailyPricePanel);
        
        topPanel.add(fieldPanel);
        topPanel.add(creatorsPanel);
        
        button = new JButton("Save");
        
        add(topPanel, BorderLayout.NORTH);
        add(descriptionPanel, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
    
    

}
