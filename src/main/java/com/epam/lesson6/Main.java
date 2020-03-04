package com.epam.lesson6;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Main {

    static void saveChanges(Book[] books, String file) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nDo you want to write changes into the file " + file + "? Y / any symbol");
        String answer = input.nextLine();
        if (answer.trim().toLowerCase().equals("y")) {
            IOBooks.writeBooksArray(books, file);
        }
    }

    static void addBook(Books books, Book book) {
        try {
            books.addBook(book);
            System.out.println("\nThe book is added:");
        } catch (ArrayIndexOutOfBoundsException err) {
            System.err.println("\nThe bookshelf is full, unable to add book:");
        }
        System.out.println(book);
    }

    public static void main(String[] args) throws IOException {
        final int MAX_NUMBER_OF_BOOKS = 14;
        final String SOURSE_FILE = "booklist.ser";
        Scanner input = new Scanner(System.in);
        Book newBook;
        Books someBooks;

//        Book[] sklad = new Book[]{
//                new Book(1, "Добрі новини з Аральського моря", "Ірена Карпа", "#книголав", 2019, 592, 300.00F),
//                new Book(2, "Тореадори з Васюківки", "Всеволод Нестайко", "А-ба-ба-га-ла-ма-га", 2013, 544, 130.00F),
//                new Book(3, "Бог завжди подорожує інкогніто", "Лоран Гунель", "Книжковий клуб \"Клуб Сімейного Дозвілля\"", 2016, 416, 95.00F),
//                new Book(4, "Моя бабуся просить їй вибачити", "Фредрік Бакман", "#книголав", 2017, 416, 200.00F),
//                new Book(5, "Музей покинутих секретів", "Оксана Забужко", "Комора", 2015, 832, 220.00F),
//                new Book(6, "Доки світло не згасне назавжди", "Макс Кідрук", "Книжковий клуб \"Клуб Сімейного Дозвілля\"", 2019, 560, 150.00F),
//                new Book(7, "Гаррі Поттер і філософський камінь", "Джоан Роулінг", "А-ба-ба-га-ла-ма-га", 2016, 248, 460.00F),
//                new Book(8, "Маленький принц", "Антуан де Сент-Экзюпери", "А-ба-ба-га-ла-ма-га", 2014, 64, 220.00F),
//                new Book(9, "Гаррі Поттер і Келих Вогню", "Джоан Роулінг", "А-ба-ба-га-ла-ма-га", 2019, 464, 540.00F),
//                new Book(10, "Маркетер", "Олег Сенцов", "Видавництво Старого Лева", 2019, 376, 150.00F),
//                new Book(11, "Мексиканські хроніки", "Макс Кідрук", "Книжковий клуб \"Клуб Сімейного Дозвілля\"", 2016, 256, 95.00F),
//                new Book(12, "Купите книгу - она смешная ", "Олег Сенцов", "Фолио", 2016, 219, 99.00F),
//                new Book(13, "Подорож на Пуп Землі ", "Макс Кідрук", "Книжковий клуб \"Клуб Сімейного Дозвілля\"", 2016, 376, 100.00F),
//                new Book(14, "Жизня", "Олег Сенцов", "Видавництво Старого Лева", 2019, 160, 90.00F)
//        };
//        Books bookShelf = new Books(sklad);

        Book[] newBooks = IOBooks.readBooksArray("booklist.ser", MAX_NUMBER_OF_BOOKS);
        Books bookShelf = new Books(newBooks);

        System.out.println("\nThe bookshelf contents:");
        bookShelf.printBooks();

        System.out.println("\nEnter the file name for storing intermediate results (without extension):");
        String fileName = input.nextLine();

        boolean validFileName = true;
        try {
            Validator.checkFile(fileName);
        } catch (InvalidInputException err) {
            System.err.println(err.getMessage());
            validFileName = false;
        }

        newBook = new Book(14, "Жизня", "Олег Сенцов", "Видавництво Старого Лева", 2019, 160, 90.00F);
        addBook(bookShelf, newBook);
        newBook = new Book(13, "Подорож на Пуп Землі ", "Макс Кідрук", "Книжковий клуб \"Клуб Сімейного Дозвілля\"", 2016, 376, 100.00F);
        addBook(bookShelf, newBook);

        if (validFileName) {
            saveChanges(bookShelf.getBooks(), fileName + ".ser");
        }
        for (int i = 1; i < 4; i++) {
            System.out.println("\nEnter author:");
            String author = input.nextLine();
            try {
                Validator.checkAuthor(author);
                someBooks = bookShelf.searchAuthor(author);
                someBooks.printBooks();
                System.out.println();
                saveChanges(someBooks.getBooks(), "author" + author + ".ser");
            } catch (InvalidInputException err) {
                System.err.println(err.getMessage());
            }
        }

        for (int i = 1; i < 4; i++) {
            System.out.println("\nEnter the year of publication:");
            try {
                int year = input.nextInt();
                try {
                    Validator.checkYear(year);
                    someBooks = bookShelf.searchYear(year);
                    someBooks.printBooks();
                    System.out.println();
                    saveChanges(someBooks.getBooks(), "year" + year + ".ser");
                } catch (InvalidInputException err) {
                    System.err.println(err.getMessage());
                }
            } catch (InputMismatchException err) {
                System.err.println("\nThe entered data should contain only figures! Search can't be executed.");
                input.nextLine();
            }
        }

        for (int i = 1; i < 4; i++) {
            System.out.println("\nEnter percent of revaluation:");
            try {
                float percent = input.nextFloat();
                try {
                    Validator.checkPercent(percent);
                    bookShelf.revaluation(percent);
                    System.out.println("\nHere are the new prices:");
                    bookShelf.printBooks();
                    System.out.println();
                    saveChanges(bookShelf.getBooks(), "revaluation" + percent + ".ser");
                    bookShelf.revaluationCancel(percent);
                } catch (InvalidInputException err) {
                    System.err.println(err.getMessage());
                }
            } catch (InputMismatchException err) {
                System.err.println("\nThe entered data should contain only figures! Revaluation can't be executed.");
                input.nextLine();
            }

            if (validFileName) {
                saveChanges(bookShelf.getBooks(), fileName + ".ser");
            }
        }

        System.out.println("\nSorted by author:");
        someBooks = bookShelf.sorteByAuthor();
        someBooks.printBooks();
        System.out.println();
        saveChanges(someBooks.getBooks(), "sortAuthor.ser");

        System.out.println("\nSorted by publisher:");
        someBooks = bookShelf.sorteByPublisher();
        someBooks.printBooks();
        saveChanges(someBooks.getBooks(), "sortPublisher.ser");

        System.out.println("\nSorted by cost:");
        someBooks = bookShelf.sorteByCost();
        someBooks.printBooks();
        saveChanges(someBooks.getBooks(),"sortCost.ser");

        System.out.println();
        saveChanges(bookShelf.getBooks(), SOURSE_FILE);

    }
}
