import com.epam.lesson6.Book;
import com.epam.lesson6.Books;
import com.epam.lesson6.IOBooks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckRevaluation extends Preconditions {
    @Parameterized.Parameter
    public float percent;
    @Parameterized.Parameter(1)
    public Books expectedResultBooks;

    @Parameterized.Parameters(name = "{index}: the percent is {0}")
    public static Collection dataForCheckRevaluation() {
        Book[][] resultBooks = new Book[3][];
        resultBooks[0] = IOBooks.readBooksArray("revaluation-30.0.ser", NUMBER_OF_ALL_BOOKS);
        resultBooks[1] = IOBooks.readBooksArray("revaluation50.0.ser", NUMBER_OF_ALL_BOOKS);
        resultBooks[2] = IOBooks.readBooksArray("revaluation100.0.ser", NUMBER_OF_ALL_BOOKS);

        return Arrays.asList(new Object[][]{
                {-30, new Books(resultBooks[0])},
                {50, new Books(resultBooks[1])},
                {100, new Books(resultBooks[2])},
        });
    }

    @Test
    public void checkRevaluation() {
        bookShelf.revaluation(percent);
        System.out.println("\nHere are the new prices after " + percent + " % revaluation:");
        bookShelf.printBooks();
        boolean resultIsTrue = Arrays.equals(expectedResultBooks.getBooks(), bookShelf.getBooks());
        bookShelf.revaluationCancel(percent);

        Assert.assertTrue("\nTest checks the books revaluation." +
                        "\n" + percent + " % revaluation results do not match to expected results.",
                resultIsTrue);
    }
}

