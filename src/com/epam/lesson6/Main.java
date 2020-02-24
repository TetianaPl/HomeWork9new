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
        Scanner input = new Scanner(System.in);
        Book newBook;
        Books someBooks;

        Book[] newBooks = IOBooks.readBooksArray(args[0], MAX_NUMBER_OF_BOOKS);
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

        newBook = new Book(13, "Подорож на Пуп Землі ", "Макс Кідрук", "Книжковий клуб \"Клуб Сімейного Дозвілля\"", 2016, 376, 100.00F);
        addBook(bookShelf, newBook);

        newBook = new Book(14, "Жизня", "Олег Сенцов", "Видавництво Старого Лева", 2019, 160, 90.00F);
        addBook(bookShelf, newBook);

        if (validFileName) {
            saveChanges(bookShelf.getBooks(), fileName + ".ser");
        }

        System.out.println("\nEnter author:");
        String author = input.nextLine();

        try {
            Validator.checkAuthor(author);
            someBooks = bookShelf.searchAuthor(author);
            someBooks.printBooks();
        } catch (InvalidInputException err) {
            System.err.println(err.getMessage());
        }

        System.out.println("\nEnter the year of publication:");
        try {
            int year = input.nextInt();
            try {
                Validator.checkYear(year);
                someBooks = bookShelf.searchYear(year);
                someBooks.printBooks();
            } catch (InvalidInputException err) {
                System.err.println(err.getMessage());
            }
        } catch (InputMismatchException err) {
            System.err.println("\nThe entered data should contain only figures! Search can't be executed.");
            input.nextLine();
        }

        System.out.println("\nEnter percent of revaluation:");
        try {
            float percent = input.nextFloat();
            try {
                Validator.checkPercent(percent);
                bookShelf.revaluation(percent);
                System.out.println("\nHere are the new prices:");
                bookShelf.printBooks();
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

        System.out.println("\nSorted by author:");
        someBooks = bookShelf.sorteByAuthor();
        someBooks.printBooks();

        System.out.println("\nSorted by publisher:");
        someBooks = bookShelf.sorteByPublisher();
        someBooks.printBooks();

        System.out.println("\nSorted by cost:");
        someBooks = bookShelf.sorteByCost();
        someBooks.printBooks();

        System.out.println();
        saveChanges(bookShelf.getBooks(), args[0]);

    }
}
