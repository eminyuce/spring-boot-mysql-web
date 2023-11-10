package com.emin.yuce.learning.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class FlexibleDateTimeParser {

    public static LocalDateTime parse(String input) {
        // Define an array of possible patterns
        String[] patterns = {
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd HH:mm:ss",
                "yyyyMMdd'T'HHmmss",
                // Add more patterns as needed
        };

        // Create a DateTimeFormatterBuilder
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

        // Loop through the patterns and append optional patterns to the builder
        for (String pattern : patterns) {
            builder.append(DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH));
        }

        // Build the formatter
        DateTimeFormatter formatter = builder.toFormatter();

        // Try parsing the input with the formatter
        try {
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            // Handle the exception or return null if parsing fails
            System.out.println("Failed to parse the input: " + input);
            return null;
        }
    }
}
