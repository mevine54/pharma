package fr.mevine.util;

import java.time.LocalDateTime;

public class Logger {

    public static void log(String message) {
        System.out.println(LocalDateTime.now() + " - " + message);
    }

    public static void error(String message) {
        System.err.println(LocalDateTime.now() + " - ERROR: " + message);
    }
}
