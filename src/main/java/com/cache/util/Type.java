package com.cache.util;

/**
 * @author Daniel
 */
public enum Type {
    SPRITES;

    public String toString() {
        return String.format("%s%s", Character.toString(super.toString().charAt(0)).toUpperCase(), super.toString().substring(1).toLowerCase());
    }

}
