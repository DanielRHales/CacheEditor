package com.cache.sprite;

import com.cache.sprite.util.BeanType;

import java.awt.image.BufferedImage;

/**
 * @author Daniel
 */
public abstract class Bean {

    private String hash = "";

    private byte[] bytes = null;

    private BufferedImage image = null;

    protected Bean() {
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public abstract BeanType getBeanType();

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
