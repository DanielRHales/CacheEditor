package com.cache.sprite.impl;

import com.cache.sprite.Bean;
import com.cache.sprite.util.BeanType;
import com.configuration.data.StreamHash;
import com.logging.Logger;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
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

    public void remove() {
        setName("");
        setX(0);
        setY(0);
        setBytes(new byte[]{-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 1, 0, 0, 0, 1, 8, 2, 0, 0, 0, -112, 119, 83, -34, 0, 0, 0, 1, 115, 82, 71, 66, 0, -82, -50, 28, -23, 0, 0, 0, 4, 103, 65, 77, 65, 0, 0, -79, -113, 11, -4, 97, 5, 0, 0, 0, 9, 112, 72, 89, 115, 0, 0, 14, -61, 0, 0, 14, -61, 1, -57, 111, -88, 100, 0, 0, 0, 12, 73, 68, 65, 84, 24, 87, 99, -8, -49, -16, 31, 0, 4, 0, 1, -1, -120, -48, -3, -52, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126});
        try {
            setImage(ImageIO.read(new ByteArrayInputStream(getBytes())));
        } catch (IOException e) {
            setImage(null);
        }
        setHash(StreamHash.getStreamHash(new ByteArrayInputStream(getBytes())));
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
