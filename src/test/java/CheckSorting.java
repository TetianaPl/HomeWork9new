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
public class CheckSorting extends Preconditions {
    @Parameterized.Parameter
    public String sortCriteria;
    @Parameterized.Parameter(1)
    public Books expectedResultBooks;

    @Parameterized.Parameters(name = "{index}: sorting by {0}")
    public static Collection dataForCheckSorting() {
        Book[][] resultBooks = new Book[3][];
        resultBooks[0] = IOBooks.readBooksArray("sortAuthor.ser", NUMBER_OF_ALL_BOOKS);
        resultBooks[1] = IOBooks.readBooksArray("sortPublisher.ser", NUMBER_OF_ALL_BOOKS);
        resultBooks[2] = IOBooks.readBooksArray("sortCost.ser", NUMBER_OF_ALL_BOOKS);

        return Arrays.asList(new Object[][]{
                {"author", new Books(resultBooks[0])},
                {"publisher", new Books(resultBooks[1])},
                {"cost", new Books(resultBooks[2])},
        });
    }

    @Test
    public void checkSorting() {
        Books actualResultBooks = null;

        switch (sortCriteria) {
            case "author":
                actualResultBooks = bookShelf.sorteByAuthor();
                break;

            case "publisher":
                actualResultBooks = bookShelf.sorteByPublisher();
                break;

            case "cost":
                actualResultBooks = bookShelf.sorteByCost();
                break;

            default:
                System.out.println("Wrong sort criteria");
                break;
        }

        System.out.println("\nSorted by " + sortCriteria + ":");
        actualResultBooks.printBooks();
        Assert.assertArrayEquals("\nTest checks the books sorting by " + sortCriteria + "." +
                        "\nResults do not match to expected results.",
                expectedResultBooks.getBooks(), actualResultBooks.getBooks());
// Я понимаю, что при сортировке могут быть правильные несколько вариантов, но уже некогда это реализовать
    }
}

