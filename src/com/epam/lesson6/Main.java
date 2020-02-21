package com.epam.lesson6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MAX_NUMBER_OF_BOOKS = 12;
        Scanner input = new Scanner(System.in);
        Books someBooks;

        Book[] sklad = new Book[]{
                new Book(1, "Добрі новини з Аральського моря", "Ірена Карпа", "#книголав", 2019, 592, 300.00F),
                new Book(2, "Тореадори з Васюківки", "Всеволод Нестайко", "А-ба-ба-га-ла-ма-га", 2013, 544, 130.00F),
                new Book(3, "Бог завжди подорожує інкогніто", "Лоран Гунель", "Книжковий клуб \"Клуб Сімейного Дозвілля\"", 2016, 416, 95.00F),
                new Book(4, "Моя бабуся просить їй вибачити", "Фредрік Бакман", "#книголав", 2017, 416, 200.00F),
                new Book(5, "Музей покинутих секретів", "Оксана Забужко", "Комора", 2015, 832, 220.00F),
                new Book(6, "Доки світло не згасне назавжди", "Макс Кідрук", "Книжковий клуб \"Клуб Сімейного Дозвілля\"", 2019, 560, 150.00F),
                new Book(7, "Гаррі Поттер і філософський камінь", "Джоан Роулінг", "А-ба-ба-га-ла-ма-га", 2016, 248, 460.00F),
                new Book(8, "Маленький принц", "Антуан де Сент-Экзюпери", "А-ба-ба-га-ла-ма-га", 2014, 64, 220.00F),
                new Book(9, "Гаррі Поттер і Келих Вогню", "Джоан Роулінг", "А-ба-ба-га-ла-ма-га", 2019, 464, 540.00F),
                new Book(10, "Маркетер", "Олег Сенцов", "Видавництво Старого Лева", 2019, 376, 150.00F),
                new Book(11, "Мексиканські хроніки", "Макс Кідрук", "Книжковий клуб \"Клуб Сімейного Дозвілля\"", 2016, 256, 95.00F),
                new Book(12, "Купите книгу - она смешная ", "Олег Сенцов", "Фолио", 2016, 219, 99.00F),
                new Book(13, "Подорож на Пуп Землі ", "Макс Кідрук", "Книжковий клуб \"Клуб Сімейного Дозвілля\"", 2016, 376, 100.00F),
                new Book(14, "Жизня", "Олег Сенцов", "Видавництво Старого Лева", 2019, 160, 90.00F)
        };

        Books bookShelf = new Books(MAX_NUMBER_OF_BOOKS);
        bookShelf.fillBookShelf(sklad, 13);
        System.out.println("\nThe bookshelf contents:");
        bookShelf.printBooks();

        try {
            bookShelf.addBook(sklad[13]);
            System.out.println("\nThe book is added:");
        } catch (ArrayIndexOutOfBoundsException err) {
            System.err.println("\nThe bookshelf is full, unable to add book:");
        }
        System.out.println(sklad[13]);

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

        System.out.println("\nSorted by author:");
        someBooks = bookShelf.sorteByAuthor();
        someBooks.printBooks();

        System.out.println("\nSorted by publisher:");
        someBooks = bookShelf.sorteByPublisher();
        someBooks.printBooks();

        System.out.println("\nSorted by cost:");
        someBooks = bookShelf.sorteByCost();
        someBooks.printBooks();
    }
}
