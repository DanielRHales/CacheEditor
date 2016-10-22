package com.logging;

import com.configuration.Constants;
import com.configuration.util.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Daniel
 */
public class Logger {
    public static void log(final Class clazz, final java.util.logging.Level level, final String message, final Exception exception, boolean write) {
        java.util.logging.Logger.getLogger(clazz.getName()).log(level, message, exception);
        if (write) {
            write(exception);
        }
    }

    public static void log(final Class clazz, final java.util.logging.Level level, final String message, final Exception exception) {
        log(clazz, level, message, exception, true);
    }

    private static void write(Exception exception) {
        final File file = new File(Constants.ERROR_DIRECTORY, String.format("%d.txt", System.currentTimeMillis()));
        Environment.create(file, false);
        try {
            FileWriter writer = new FileWriter(file);
            exception.printStackTrace(new PrintWriter(writer));
            writer.flush();
            writer.close();
        } catch (IOException ignore) {
        }
    }
}
