package com.epam.lesson6;

import java.util.Arrays;

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

    public void printBooks() {
        for (int i = 0; i < quantity; i++) {
            System.out.println(books[i]);
        }
    }

    public Books sorteByAuthor() {
        Book[] other = Arrays.copyOf(books, quantity);
        Arrays.sort(other, new ComparatorByAuthor());
        return new Books(other);
    }

    public Books sorteByPublisher() {
        Book[] other = Arrays.copyOf(books, quantity);
        Arrays.sort(other, new ComparatorByPublisher());
        return new Books(other);
    }

    public Books sorteByCost() {
        Book[] other = Arrays.copyOf(books, quantity);
        Arrays.sort(other, new ComparatorByCost());
        return new Books(other);
    }

}




