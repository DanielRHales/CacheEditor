package com.frame;

import com.configuration.Constants;
import com.frame.component.CachePanel;
import com.frame.impl.SpritePanel;
import com.resource.Resource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel
 */
public class UI extends JFrame {

    private UI() {
        initialize();
    }

    public static UI getInstance() {
        return InstanceHolder.instance != null ? InstanceHolder.instance : (InstanceHolder.instance = new UI());
    }

    private void initialize() {
        final JTabbedPane tabbedPane = new JTabbedPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(String.format("%s Cache Editor", Constants.SERVER_NAME));
        setIconImages(Resource.ICONS_LIST);
        final ArrayList<CachePanel> panels = new ArrayList<CachePanel>(
                Collections.singletonList(
                        new SpritePanel()
                )
        );
        for (CachePanel cachePanel : panels) {
            tabbedPane.addTab(cachePanel.getType().toString(), cachePanel);
        }
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    private static class InstanceHolder {
        private static UI instance;
    }
}
