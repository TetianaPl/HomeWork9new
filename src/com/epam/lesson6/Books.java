package com.epam.lesson6;

import java.util.Arrays;

public class Books {

    private int length;
    private Book[] books = new Book[length];

    public Books(int length, Book[] books) {
        this.length = length;
        this.books = Arrays.copyOf(books, length);
    }

    public Books() {
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public void addBook(Book book) {
        Book[] newBooks = Arrays.copyOf(this.getBooks(), this.getLength() + 1);
        newBooks[this.getLength()] = book;
        this.setBooks(newBooks);
        this.setLength(this.getLength() + 1);
    }

    public Book[] searchAuthor(String author) {
        Book[] newBooks = new Book[books.length];
        int count = 0;
        for (Book elem : books) {
            if (elem.getAuthor().equals(author)) {
                newBooks[count] = elem;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("\nNo books found");
        }
        return Arrays.copyOf(newBooks, count);
    }

    public Book[] searchYear(int year) {
        Book[] newBooks = new Book[books.length];
        int count = 0;
        for (Book elem : books) {
            if (elem.getYearOfPublication() > year) {
                newBooks[count] = elem;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("\nNo books found");
        }
        return Arrays.copyOf(newBooks, count);
    }

    public void revaluation(float percent) {
        for (Book elem : books) {
            elem.setCost(elem.getCost() * (1 + percent / 100));
        }
    }

    public void printBooks() {
        for (Book elem : books) {
            elem.viewBook();
        }

    }
}

