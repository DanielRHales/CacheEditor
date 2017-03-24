package com.resource;

import com.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;

public class FileOperations {

    public FileOperations() {
    }

    public static byte[] readFile(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            MappedByteBuffer buffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFile.length());
            if (!buffer.hasArray()) {
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                return bytes;
            }
            randomAccessFile.close();
            return buffer.array();
        } catch (FileNotFoundException ex) {
            Logger.log(FileOperations.class, Level.SEVERE, String.format("File not found -> %s", file.getAbsolutePath()), ex);
            return new byte[]{};
        } catch (Exception ex) {
            Logger.log(FileOperations.class, Level.SEVERE, String.format("Exception getting bytes from file -> %s", file.getAbsolutePath()), ex);
            return new byte[]{};
        }
    }

}