package com.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створення об'єктів com.example.Author та com.example.Book
        Author author1 = new Author("Author A", "Ukrainian");
        Author author2 = new Author("Author B", "American");
        Author author3 = new Author("Author C", "American");

        List<Book> books = Arrays.asList(
                new Book("Book One", author1, 1975),
                new Book("Book Two", null, 2001),
                new Book("Book Three", author2, 2002),
                new Book("Book Four", author3, 1999)
        );

        // Масив книг
        Book[] booksArray = new Book[]{
                new Book("Book One", author1, 1975),
                new Book("Book Two", null, 2001),
                new Book("Book Three", author2, 2002),
                new Book("Book Four", author3, 1998)
        };

        try {
            BookFileWriter.writeTitlesBuffered(books, "titles.txt");

            BookFileWriter.writeAuthorsUnbuffered(books, "authors.txt");

            BinaryBookSerializer.serializeBooksToBinary(books, "books.dat");
            System.out.println("Серіалізовані дані (нативна серіалізація):");
            for (Book book : books) {
                System.out.println(book);
            }

            List<Book> deserializedBooksFromBinary = BinaryBookSerializer.deserializeBooksFromBinary("books.dat");
            System.out.println("\nДесеріалізовані дані (нативна серіалізація):");
            for (Book book : deserializedBooksFromBinary) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + (book.getAuthor() != null ? book.getAuthor().getName() : "No author"));
                System.out.println("Publication Year: " + book.getPublicationYear());
                System.out.println("----------");
            }

            JsonBookSerializer.serializeBooksToJson(books, "books.json");
            System.out.println("\nСеріалізовано список у JSON: " + books);

            List<Book> deserializedBooksFromJson = JsonBookSerializer.deserializeBooksFromJson("books.json");
            System.out.println("Десеріалізовано список з JSON:");

            for (Book book : deserializedBooksFromJson) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + (book.getAuthor() != null ? book.getAuthor().getName() : "No author"));
                System.out.println("Publication Year: " + book.getPublicationYear());
                System.out.println("----------");
            }

            YamlBookSerializer.serializeBooksToYaml(booksArray, "books.yaml");
            System.out.println("Серіалізовано у YAML.");

            Book[] deserializedBooksFromYaml = YamlBookSerializer.deserializeBooksFromYaml("books.yaml");
            System.out.println("Десеріалізовано масив книг із YAML:");

            for (Book book : deserializedBooksFromYaml) {
                System.out.println(book);
            }

            System.out.println("Усі операції виконано успішно.");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
