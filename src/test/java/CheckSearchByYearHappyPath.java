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
public class CheckSearchByYearHappyPath extends Preconditions {

    @Parameterized.Parameter
    public int year;
    @Parameterized.Parameter(1)
    public Books expectedResultBooks;

    @Parameterized.Parameters(name = "{index}: results for {0} should be not null")
    public static Collection dataForSearchYearHappyPath() {
        Book[][] resultBooks = new Book[3][];
        resultBooks[0] = IOBooks.readBooksArray("year2015.ser", 11);
        resultBooks[1] = IOBooks.readBooksArray("year2016.ser", 6);
        resultBooks[2] = IOBooks.readBooksArray("year2018.ser", 5);

        return Arrays.asList(new Object[][]{
                {2015, new Books(resultBooks[0])},
                {2016, new Books(resultBooks[1])},
                {2018, new Books(resultBooks[2])},
        });
    }

    @Test
    public void checkSearchYearHappyPath() {
        Books actualResultBooks = null;
        try {
            actualResultBooks = bookShelf.searchYear(year);
            System.out.println("\nSuch books are found with year of publication after " + year);
            actualResultBooks.printBooks();
            Assert.assertArrayEquals("\nTest checks the search for books by year of publication with valid data." +
                            "\nSearch results  do not match to list of books with year of publication after " + year,
                    actualResultBooks.getBooks(), expectedResultBooks.getBooks());
        } catch (InvalidInputException err) {
            Assert.assertNotNull("\nThe test checks the search for books by year of publication with valid data." +
                    "\nNo books found with year of publication after " + year, actualResultBooks);
        }
    }

}
