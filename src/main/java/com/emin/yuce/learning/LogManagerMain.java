package com.emin.yuce.learning;

import com.emin.yuce.learning.utils.FlexibleDateTimeParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class LogManagerMain {

    public static void main(String[] args) {

        //SpringApplication.run(LogManagerMain.class, args);
        String input = "2023-11-10T15:30:45";
        LocalDateTime result = FlexibleDateTimeParser.parse(input);

        if (result != null) {
            System.out.println("Parsed LocalDateTime: " + result);
        }
    }
}
