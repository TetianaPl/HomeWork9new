package com.epam.lesson6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int NUMBER_OF_BOOKS = 10;
        Scanner input = new Scanner(System.in);

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

        Books books = new Books(NUMBER_OF_BOOKS, sklad);

        books.addBook(sklad[books.getLength()]);
        books.addBook(sklad[books.getLength()]);
        books.addBook(sklad[books.getLength()]);
        books.addBook(sklad[books.getLength()]);

        books.printBooks();

        System.out.print("\n\nEnter author:->");
        String author = input.nextLine();
        Books newBooks1 = new Books();
        newBooks1.setBooks(books.searchAuthor(author));
        newBooks1.printBooks();

        System.out.print("\n\nEnter year of publication:->");
        int year = input.nextInt();
        Books newBooks2 = new Books();
        newBooks2.setBooks(books.searchYear(year));
        newBooks2.printBooks();

        System.out.print("\n\nEnter percent of revaluation:->");
        float percent = input.nextFloat();
        books.revaluation(percent);
        books.printBooks();
    }
}
