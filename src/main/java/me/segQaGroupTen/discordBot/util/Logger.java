package me.segQaGroupTen.discordBot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static final String LOG_FORMAT = "%s|(Level|%s) %s: %s";
    private static final String DATE_FORMAT = "HH:mm:ss";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public static String getDateString(Date time) {
        return dateFormat.format(time);
    }

    public static void logInfoMessage(String label, String message) {
        System.out.println(String.format(LOG_FORMAT, getDateString(new Date()), "INFO", label, message));
    }

    public static void logErrorMessage(String label, String message) {
        System.out.println(String.format(LOG_FORMAT, getDateString(new Date()), "ERROR", label, message));
    }
}
