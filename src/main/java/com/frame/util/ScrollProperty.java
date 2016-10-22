package com.frame.util;

import javax.swing.*;

/**
 * @author Daniel
 */
public class ScrollProperty {

    public static void setScrollProperty(final JScrollPane scrollPane, final int verticalScroll, final int horizontalScroll) {
        scrollPane.getVerticalScrollBar().setUnitIncrement(verticalScroll);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(horizontalScroll);
        scrollPane.getViewport().putClientProperty("EnableWindowBlit", Boolean.TRUE);
        scrollPane.getViewport().setScrollMode(javax.swing.JViewport.BACKINGSTORE_SCROLL_MODE);
    }

}
