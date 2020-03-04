import com.epam.lesson6.Book;
import com.epam.lesson6.Books;
import com.epam.lesson6.IOBooks;
import com.epam.lesson6.InvalidInputException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckSearchByAuthorHappyPath extends Preconditions{
    @Parameterized.Parameter
    public String author;
    @Parameterized.Parameter(1)
    public Books expectedResultBooks;

    @Parameterized.Parameters(name = "{index}: results for {0} should be not null")
    public static Collection dataForSearchAuthorHappyPath() {
        Book[][] resultBooks = new Book[3][];
        resultBooks[0] = IOBooks.readBooksArray("authorОлег Сенцов.ser", 3);
        resultBooks[1] = IOBooks.readBooksArray("authorМакс Кідрук.ser", 3);
        resultBooks[2] = IOBooks.readBooksArray("authorДжоан Роулінг.ser", 2);

        return Arrays.asList(new Object[][]{
                {"Олег Сенцов", new Books(resultBooks[0])},
                {"Макс Кідрук", new Books(resultBooks[1])},
                {"Джоан Роулінг", new Books(resultBooks[2])},
        });
    }

    @Test
    public void checkSearchAuthorHappyPath() {
        Books actualResultBooks = null;
        try {
            actualResultBooks = bookShelf.searchAuthor(author);
            System.out.println("\nSuch books are found with author " + author);
            actualResultBooks.printBooks();
            Assert.assertArrayEquals("\nTest checks the search for books by author with valid data." +
                            "\nSearch results  do not match to list of books with author " + author,
                    actualResultBooks.getBooks(), expectedResultBooks.getBooks());
        } catch (InvalidInputException err) {
            Assert.assertNotNull("\nTest checks the search for books by author with valid data." +
                    "\nNo books found with author " + author, actualResultBooks);
        }
    }

}
