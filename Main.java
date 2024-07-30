import javax.swing.*;
import java.awt.*;
import java.util.List;

// Enum for Book Availability Status
enum Availability {
    AVAILABLE,
    BORROWED
}

// Book class to represent a book
class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private Availability availability;

    public Book(int id, String title, String author, String genre, Availability availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Availability getAvailability() {
        return availability;
    }

    // toString method for displaying book information
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availability=" + availability +
                '}';
    }
}

// Patron class to represent a library patron
class Patron {
    private int id;
    private String name;
    private String contactInfo;

    public Patron(int id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters (omitted for brevity)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    // toString method for displaying patron information
    @Override
    public String toString() {
        return "Patron{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}

// Interface for Library Management System operations
interface LibraryManager {
    void addBook(Book book);
    Book findBookById(int bookId);
    List<Book> searchBooks(String keyword);
    void addPatron(Patron patron);
    Patron findPatronById(int patronId);
    List<Patron> searchPatrons(String keyword);
    void borrowBook(int bookId, int patronId);
    void returnBook(int bookId);
    void calculateFine(int patronId);
    void generateReports();
}

// Example implementation of LibraryManager (without actual database operations)
class LibraryManagerImpl implements LibraryManager {

    private List<Book> books;
    private List<Patron> patrons;

    public LibraryManagerImpl(List<Book> books, List<Patron> patrons) {
        this.books = books;
        this.patrons = patrons;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null; // Return null if book with given ID is not found
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> result = new java.util.ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result; // Return empty list if no books found matching the keyword
    }

    @Override
    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    @Override
    public Patron findPatronById(int patronId) {
        for (Patron patron : patrons) {
            if (patron.getId() == patronId) {
                return patron;
            }
        }
        return null; // Return null if patron with given ID is not found
    }

    @Override
    public List<Patron> searchPatrons(String keyword) {
        List<Patron> result = new java.util.ArrayList<>();
        for (Patron patron : patrons) {
            if (patron.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(patron);
            }
        }
        return result; // Return empty list if no patrons found matching the keyword
    }

    @Override
    public void borrowBook(int bookId, int patronId) {
        // Implement logic to borrow a book
    }

    @Override
    public void returnBook(int bookId) {
        // Implement logic to return a book
    }

    @Override
    public void calculateFine(int patronId) {
        // Implement logic to calculate fines
    }

    @Override
    public void generateReports() {
        // Implement logic to generate reports
    }
}

// Main class for testing LibraryManager operations with GUI
public class Main extends JFrame {

    private LibraryManager libraryManager;

    private JTextField searchField;
    private JTextArea resultArea;

    public Main() {
        // Example data initialization
        List<Book> books = new java.util.ArrayList<>();
        List<Patron> patrons = new java.util.ArrayList<>();
        libraryManager = new LibraryManagerImpl(books, patrons);

        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Search Books");
        searchButton.addActionListener(e -> searchBooks());
        searchPanel.add(searchButton);

        topPanel.add(searchPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 10)); // GridLayout with vertical arrangement
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> addBook());
        buttonPanel.add(addBookButton);

        JButton addPatronButton = new JButton("Add Patron");
        addPatronButton.addActionListener(e -> addPatron());
        buttonPanel.add(addPatronButton);

        JButton clearButton = new JButton("Clear Results");
        clearButton.addActionListener(e -> clearResults());
        buttonPanel.add(clearButton);

        topPanel.add(buttonPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        resultArea = new JTextArea(20, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void searchBooks() {
        String keyword = searchField.getText();
        List<Book> foundBooks = libraryManager.searchBooks(keyword);
        displaySearchResults(foundBooks);
    }

    private void addBook() {
        // Placeholder for adding book functionality
        JOptionPane.showMessageDialog(this, "Add Book functionality not implemented yet.");
    }

    private void addPatron() {
        // Placeholder for adding patron functionality
        JOptionPane.showMessageDialog(this, "Add Patron functionality not implemented yet.");
    }

    private void clearResults() {
        resultArea.setText("");
    }

    private void displaySearchResults(List<Book> books) {
        resultArea.setText("");
        if (books.isEmpty()) {
            resultArea.append("No books found.\n");
        } else {
            resultArea.append("Books found:\n");
            for (Book book : books) {
                resultArea.append(book.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
