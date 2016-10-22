package com.cache.sprite.impl;

import com.cache.sprite.Bean;
import com.cache.sprite.util.BeanType;
import com.logging.Logger;

import javax.imageio.ImageIO;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Cached extends Bean {

    private String name = "";

    private int id = -1;

    private int x = 0;

    private int y = 0;

    @Override
    public BeanType getBeanType() {
        return BeanType.CACHED;
    }

    public void readValues(DataInputStream indexStream, DataInputStream dataStream) throws IOException {
        while(true) {
            byte opCode = dataStream.readByte();
            if(opCode == 0) {
                return;
            }
            if(opCode == 1) {
                id = dataStream.readShort();
            } else if(opCode == 2) {
                name = dataStream.readUTF();
            } else if(opCode == 3) {
                x = dataStream.readShort();
            } else if(opCode == 4) {
                y = dataStream.readShort();
            } else if(opCode == 5) {
                int indexLength = indexStream.readInt();
                byte[] bytes = new byte[indexLength];
                dataStream.readFully(bytes);
                setBytes(bytes);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void saveImage(File file) {
        try {
            ImageIO.write(getImage(), "png", file);
        } catch (IOException ex) {
            Logger.log(Cached.class, Level.WARNING, String.format("Error saving Cached Image '%s:%d'", name, id), ex);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
