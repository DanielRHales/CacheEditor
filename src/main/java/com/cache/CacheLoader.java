package com.cache;

/**
 * @author Daniel
 */
public abstract class CacheLoader {

    protected CacheLoader() {
        load();
    }

    protected abstract void load();

}
