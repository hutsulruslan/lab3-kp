package com.example;

import java.io.*;
import java.util.List;

public class BookFileWriter {

    public static void writeTitlesBuffered(List<Book> books, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write(book.getTitle());
                writer.newLine();
            }
        }
    }

    public static void writeAuthorsUnbuffered(List<Book> books, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Book book : books) {
                if (book.getAuthor() != null) {
                    writer.write(book.getAuthor().getName());
                    writer.write("\n");
                }
            }
        }
    }
}
