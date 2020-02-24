package com.epam.lesson6;

import java.io.*;

public class IOBooks {

    public static void writeBooksArray(Book[] booksWritten, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Book elem : booksWritten) {
                oos.writeObject(elem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Book[] readBooksArray(String fileName, int size) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Book[] booksRead = new Book[size];
            for (int i = 0; i < size; i++) {
                try {
                    booksRead[i] = (Book) ois.readObject();
                } catch (EOFException err) {
                    booksRead[i] = null;
                }
            }
            return booksRead;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
