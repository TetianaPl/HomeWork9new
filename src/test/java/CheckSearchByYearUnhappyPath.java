import com.epam.lesson6.Books;
import com.epam.lesson6.InvalidInputException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckSearchByYearUnhappyPath extends Preconditions {
    @Parameterized.Parameter
    public int year;

    @Parameterized.Parameters(name = "{index}: results for {0} should be null")
    public static Collection dataForSearchYearUnhappyPath() {
        return Arrays.asList(new Object[][]{
                {2025},
                {2027},
        });
    }

    @Test
    public void checkSearchYearUnhappyPath() {
        Books actualResultBooks = null;
        try {
            actualResultBooks = bookShelf.searchYear(year);
            actualResultBooks.printBooks();
            Assert.assertNull("\nThe test checks the search for books by year of publication with invalid data." +
                    "\nThese books are found with year of publication after " + year + ".", actualResultBooks);
        } catch (InvalidInputException err) {
            System.err.println(err.getMessage() + " with year of publication after  " + year);
        }
    }

}
