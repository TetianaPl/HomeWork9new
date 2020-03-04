package com.epam.lesson6;

import java.util.Arrays;
import java.util.Comparator;

public class Books {

    private int quantity;
    private Book[] books;

    public Books() {
    }

    public Books(int length) {
        this.books = new Book[length];
        this.quantity = 0;
    }

    public Books(Book[] books) {
        this.books = books;
        int i = 0;
        for (Book elem : books) {
            if (elem != null) {
                i++;
            } else break;
        }
        this.quantity = i;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books[quantity] = book;
        quantity++;
    }

    public void fillBookShelf(Book[] sklad, int numberOfBooks) {
        int count = Math.min(numberOfBooks, sklad.length);
        for (int i = 0; i < count; i++) {
            try {
                this.addBook(sklad[i]);
            } catch (ArrayIndexOutOfBoundsException err) {
                System.err.println("\nThe bookshelf is full, unable to add book:");
                System.out.println(sklad[i]);
            }
        }
    }

    public Books searchAuthor(String author) throws InvalidInputException {
        Book[] newBooks = new Book[books.length];
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getAuthor().equals(author)) {
                newBooks[count] = books[i];
                count++;
            }
        }
        if (count == 0) {
            throw new InvalidInputException(5);
        }
        newBooks = Arrays.copyOf(newBooks, count);
        return new Books(newBooks);
    }

    public Books searchYear(int year) throws InvalidInputException {
        Book[] newBooks = new Book[books.length];
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getYearOfPublication() > year) {
                newBooks[count] = books[i];
                count++;
            }
        }
        if (count == 0) {
            throw new InvalidInputException(5);
        }
        newBooks = Arrays.copyOf(newBooks, count);
        return new Books(newBooks);
    }

    public void revaluation(float percent) {
        for (int i = 0; i < books.length; i++) {
            books[i].setCost(books[i].getCost() * (1 + percent / 100));
        }
    }

    public void revaluationCancel(float percent) {
        for (int i = 0; i < books.length; i++) {
            books[i].setCost(books[i].getCost() / (1 + percent / 100));
        }
    }

    public void printBooks() {
        for (int i = 0; i < quantity; i++) {
            System.out.println(books[i]);
        }
    }

    public Books sorteByAuthor() {
        Book[] other = Arrays.copyOf(books, quantity);
        Arrays.sort(other, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String author1 = ((Book) o1).getAuthor();
                String author2 = ((Book) o2).getAuthor();
                return author1.compareTo(author2);
            }
        });
        return new Books(other);
    }

    public Books sorteByPublisher() {
        Book[] other = Arrays.copyOf(books, quantity);
        Arrays.sort(other, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String publisher1 = ((Book) o1).getPublisher();
                String publisher2 = ((Book) o2).getPublisher();
                return publisher1.compareTo(publisher2);
            }
        });
        return new Books(other);
    }

    public Books sorteByCost() {
        Book[] other = Arrays.copyOf(books, quantity);
        Arrays.sort(other, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                float cost1 = ((Book) o1).getCost();
                float cost2 = ((Book) o2).getCost();
                if (cost1 == cost2) {
                    return 0;
                } else {
                    return cost1 < cost2 ? 1 : -1;
                }
            }
        });
        return new Books(other);
    }

}




