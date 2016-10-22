package com.frame.component;

import com.cache.CacheLoader;
import com.cache.util.Type;

import javax.swing.*;
import java.awt.*;

/**
 * @author Daniel
 */
public abstract class CachePanel extends JPanel {

    protected CachePanel() {
        super(new GridBagLayout());
        getCacheLoader();
        initialize();
    }

    public abstract Type getType();

    protected abstract CacheLoader getCacheLoader();

    protected abstract void initialize();

}