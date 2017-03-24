package com.configuration;

import com.configuration.util.Environment;

import java.io.File;

/**
 * @author Daniel
 */
public class Constants {

    public static final String SERVER_NAME = "";

    private static final String HOME = System.getProperty("user.home");

    private static final File DESKTOP = new File(HOME, "Desktop");

    private static final File MAIN_DIRECTORY = new File(DESKTOP, String.format("%sCache Editor", SERVER_NAME));

    private static final File CACHE_DIRECTORY = new File(MAIN_DIRECTORY, "Cache");

    private static final File SPRITES_DIRECTORY = new File(CACHE_DIRECTORY, "Sprites");

    public static final File RAW_SPRITES_DIRECTORY = new File(SPRITES_DIRECTORY, "Raw");

    private static final File CACHED_SPRITES_DIRECTORY = new File(SPRITES_DIRECTORY, "Cached");

    public static final File DUMPED_SPRITES_DIRECTORY = new File(SPRITES_DIRECTORY, "Dumped");

    private static final File REMOVED_SPRITES_DIRECTORY = new File(SPRITES_DIRECTORY, "Removed");

    public static final File REMOVED_CACHED_SPRITES_DIRECTORY = new File(REMOVED_SPRITES_DIRECTORY, "Cached");

    public static final File REMOVED_RAW_SPRITES_DIRECTORY = new File(REMOVED_SPRITES_DIRECTORY, "Raw");

    public static final File CACHED_SPRITES_INDEX_FILE = new File(CACHED_SPRITES_DIRECTORY, "Sprites.idx");

    public static final File CACHED_SPRITES_DATA_FILE = new File(CACHED_SPRITES_DIRECTORY, "Sprites.dat");

    public static final String ALGORITHM = new String[]{
            "MD5",
            "SHA-1",
            "SHA-256"
    }[2];

    static {
        Environment.createDirectories(
                MAIN_DIRECTORY,
                CACHE_DIRECTORY,
                SPRITES_DIRECTORY,
                DUMPED_SPRITES_DIRECTORY,
                RAW_SPRITES_DIRECTORY,
                REMOVED_SPRITES_DIRECTORY,
                REMOVED_CACHED_SPRITES_DIRECTORY,
                REMOVED_RAW_SPRITES_DIRECTORY,
                CACHED_SPRITES_DIRECTORY
        );
        Environment.createFiles(
                CACHED_SPRITES_INDEX_FILE,
                CACHED_SPRITES_DATA_FILE
        );
    }

}
