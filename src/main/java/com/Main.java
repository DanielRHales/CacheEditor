package com;

import com.frame.UI;

import javax.swing.*;
import java.awt.*;

/**
 * @author Daniel
 */
public class Main {

    private Main() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        JPopupMenu.setDefaultLightWeightPopupEnabled(true);
        initialize();
    }

    public static void main(String... argv) {
        new Main();
    }

    private void initialize() {
        EventQueue.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        UI.getInstance().setVisible(true);
                    }
                }
        );
    }

}
