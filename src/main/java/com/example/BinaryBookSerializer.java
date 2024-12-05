package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryBookSerializer {

    public static void serializeBooksToBinary(List<Book> books, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            for (Book book : books) {
                if (book.getAuthor() != null) {
                    oos.writeObject(book);
                }
            }
        }
    }

    public static List<Book> deserializeBooksFromBinary(String filePath) throws IOException, ClassNotFoundException {
        List<Book> books = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            while (true) {
                try {
                    Book book = (Book) ois.readObject();
                    books.add(book);
                } catch (EOFException e) {
                    break;
                }
            }
        }
        return books;
    }
}
