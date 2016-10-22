package com.cache.sprite.impl;

import com.cache.sprite.Bean;
import com.cache.sprite.util.BeanType;
import com.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Raw extends Bean {

    private final File file;

    public Raw(File file) {
        this.file = file;
        setBytes(fileToByteArray());
    }

    private byte[] fileToByteArray() {
        try {
            byte[] bytes = new byte[(int)file.length()];
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(bytes);
            inputStream.close();
            return bytes;
        } catch (Exception ex) {
            Logger.log(Raw.class, Level.WARNING, String.format("Error getting Bytes from file '%s'", file.getName()), ex);
            return null;
        }
    }

    public File getFile() {
        return file;
    }

    @Override
    public BeanType getBeanType() {
        return BeanType.RAW;
    }

    @Override
    public String toString() {
        return file.getName();
    }

}
