package com.frame.component;

import com.frame.UI;

import javax.swing.*;

/**
 * @author Daniel
 */
public class Dialog {

    public static boolean acceptedChoice(JFrame instance, final String title, final String message) {
        return JOptionPane.showConfirmDialog(instance, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;
    }

    public static void displayMessage() {
        JOptionPane.showMessageDialog(UI.getInstance(), "The Process will now Exit.");
    }

}
