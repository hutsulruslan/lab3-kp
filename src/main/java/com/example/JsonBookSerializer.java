package com.example;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonBookSerializer {

    // Кастомний ExclusionStrategy для виключення поля "publicationYear"
    private static class SkipPublicationYearStrategy implements ExclusionStrategy {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getName().equals("publicationYear"); // Виключаємо "publicationYear"
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    }

    // Gson з кастомною стратегією виключення
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .addSerializationExclusionStrategy(new SkipPublicationYearStrategy())
            .create();

    // Серіалізація книг у JSON, пропускаючи поле "publicationYear"
    public static void serializeBooksToJson(List<Book> books, String filePath) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(books, writer);
        }
    }

    // Десеріалізація книг із JSON
    public static List<Book> deserializeBooksFromJson(String filePath) throws IOException {
        try (Reader reader = new FileReader(filePath)) {
            Book[] booksArray = gson.fromJson(reader, Book[].class);
            return new ArrayList<>(List.of(booksArray));
        }
    }
}
