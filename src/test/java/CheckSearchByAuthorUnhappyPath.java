import com.epam.lesson6.Books;
import com.epam.lesson6.InvalidInputException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckSearchByAuthorUnhappyPath extends Preconditions {
    @Parameterized.Parameter
    public String author;

    @Parameterized.Parameters(name = "{index}: results for {0} should be null")
    public static Collection dataForSearchAuthorUnhappyPath() {
        return Arrays.asList(new Object[][]{
                {"Олег Сенцо"},
                {"Фредрік Бакма"}
        });
    }

    @Test
    public void checkSearchAuthorUnhappyPath() {
        Books actualResultBooks = null;
        try {
            actualResultBooks = bookShelf.searchAuthor(author);
            actualResultBooks.printBooks();
            Assert.assertNull("\nThe test checks the search for books by author with invalid data." +
                    "\nThese books are found with author " + author + ".", actualResultBooks);
        } catch (InvalidInputException err) {
            System.err.println(err.getMessage() + " by author " + author);
        }
    }

}