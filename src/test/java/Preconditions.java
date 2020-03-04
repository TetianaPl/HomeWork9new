import com.epam.lesson6.Book;
import com.epam.lesson6.Books;
import com.epam.lesson6.IOBooks;
import org.junit.BeforeClass;

public class Preconditions {
    public static Books bookShelf;
    public static final int NUMBER_OF_ALL_BOOKS = 14;

    @BeforeClass
    public static void readBooks() {
        Book[] newBooks = IOBooks.readBooksArray("booklist.ser", NUMBER_OF_ALL_BOOKS);
        bookShelf = new Books(newBooks);
        System.out.println("\nThe bookshelf contents:");
        bookShelf.printBooks();
    }
}
