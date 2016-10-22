package com.configuration.util;

import com.frame.UI;
import com.frame.component.Dialog;
import com.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Environment {

    public static void createFiles(File... files) {
        for (File file : files) {
            create(file, false);
        }
    }

    public static void createDirectories(File... files) {
        for (File file : files) {
            create(file, true);
        }
    }

    public static void create(final File file, final boolean directory) {
        if (directory ? !createDirectory(file) : !createFile(file)) {
            if (Dialog.acceptedChoice(UI.getInstance(), String.format("Try to create \'%s\' again?", file.getName()), String.format("Error creating %s '%s'.", directory ? "directory" : "file", file.getName()))) {
                create(file, directory);
            } else {
                exit();
            }
        }
    }

    private static void exit() {
        exit(true);
    }

    public static void exit(final boolean notify) {
        if (notify) {
            Dialog.displayMessage();
        }
        System.exit(0);
    }

    private static boolean createDirectory(final File directory) {
        return directory.isDirectory() || directory.mkdirs();
    }

    private static boolean createFile(final File file) {
        if (file.exists()) {
            return true;
        }
        try {
            return file.createNewFile();
        } catch (IOException ex) {
            Logger.log(Environment.class, Level.WARNING, String.format("Error creating file \'%s\'", file.getAbsolutePath()), ex);
            return false;
        }
    }

}
