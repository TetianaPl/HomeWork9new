package com.epam.lesson6;

import com.sun.deploy.util.ArrayUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

public class Book implements Externalizable {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int yearOfPublication;
    private int numberOfPages;
    private float cost;

    public Book() {
    }

    public Book(int id, String title, String author, String publisher, int yearOfPublication, int numberOfPages, float cost) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
        this.numberOfPages = numberOfPages;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

        @Override
    public String toString() {
        return String.format("ID: %1$2d %3$s, %2$s. - %4$s, %5$d. - %6$d стр.; ціна %7$.2f грн.",
                id, title, author, publisher, yearOfPublication, numberOfPages, cost);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeObject(title);
        out.writeObject(author);
        out.writeObject(publisher);
        out.writeInt(yearOfPublication);
        out.writeInt(numberOfPages);
        out.writeFloat(cost);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        title = (String) in.readObject();
        author = (String) in.readObject();
        publisher = (String) in.readObject();
        yearOfPublication = in.readInt();
        numberOfPages = in.readInt();
        cost = in.readFloat();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId() == book.getId() &&
                getYearOfPublication() == book.getYearOfPublication() &&
                getNumberOfPages() == book.getNumberOfPages() &&
                Math.abs(book.getCost() - getCost()) < 0.001 &&
                getTitle().equals(book.getTitle()) &&
                getAuthor().equals(book.getAuthor()) &&
                getPublisher().equals(book.getPublisher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthor(), getPublisher(), getYearOfPublication(), getNumberOfPages(), getCost());
    }
}
