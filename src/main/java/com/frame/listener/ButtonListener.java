package com.frame.listener;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * @author Daniel
 */
public class ButtonListener implements 
        ActionListener, 
        ChangeListener, 
        PropertyChangeListener, 
        AncestorListener, 
        ItemListener, 
        VetoableChangeListener, 
        ComponentListener, 
        ContainerListener, 
        FocusListener,
        HierarchyBoundsListener,
        InputMethodListener,
        KeyListener,
        MouseListener, 
        MouseMotionListener, 
        MouseWheelListener {

    protected ButtonListener(JButton button) {
        button.addActionListener(this);
        button.addChangeListener(this);
        button.addPropertyChangeListener(this);
        button.addAncestorListener(this);
        button.addItemListener(this);
        button.addVetoableChangeListener(this);
        button.addComponentListener(this);
        button.addContainerListener(this);
        button.addFocusListener(this);
        button.addHierarchyBoundsListener(this);
        button.addInputMethodListener(this);
        button.addKeyListener(this);
        button.addMouseListener(this);
        button.addMouseMotionListener(this);
        button.addMouseWheelListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        actionPerformed();
    }

    protected void actionPerformed() {
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        stateChanged();
    }

    protected void stateChanged() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        propertyChange();
    }

    protected void propertyChange() {

    }

    @Override
    public void ancestorAdded(AncestorEvent event) {
        ancestorAdded();
    }

    protected void ancestorAdded() {

    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
        ancestorRemoved();
    }

    protected void ancestorRemoved() {

    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        ancestorMovedEvent();
    }

    protected void ancestorMovedEvent() {

    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        itemStageChanged();
    }

    protected void itemStageChanged() {

    }

    @Override
    public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
        vetoableChange();
    }

    protected void vetoableChange() {

    }

    @Override
    public void componentResized(ComponentEvent event) {
        componentResized();
    }

    protected void componentResized() {

    }

    @Override
    public void componentMoved(ComponentEvent event) {
        componentMoved();
    }

    protected void componentMoved() {

    }

    @Override
    public void componentShown(ComponentEvent event) {
        componentShown();
    }

    protected void componentShown() {

    }

    @Override
    public void componentHidden(ComponentEvent event) {
        componentHidden();
    }

    protected void componentHidden() {

    }

    @Override
    public void componentAdded(ContainerEvent event) {
        componentAdded();
    }

    protected void componentAdded() {

    }

    @Override
    public void componentRemoved(ContainerEvent event) {
        componentRemoved();
    }

    protected void componentRemoved() {

    }

    @Override
    public void focusGained(FocusEvent event) {
        focusGained();
    }

    protected void focusGained() {

    }

    @Override
    public void focusLost(FocusEvent event) {
        focusLost();
    }

    protected void focusLost() {

    }

    @Override
    public void ancestorMoved(HierarchyEvent event) {
        ancestorMovedHierarchy();
    }

    protected void ancestorMovedHierarchy() {

    }

    @Override
    public void ancestorResized(HierarchyEvent event) {
        ancestorResized();
    }

    protected void ancestorResized() {

    }

    @Override
    public void inputMethodTextChanged(InputMethodEvent event) {
        inputMethodTextChanged();
    }

    protected void inputMethodTextChanged() {

    }

    @Override
    public void caretPositionChanged(InputMethodEvent event) {
        caretPositionChanged();
    }

    protected void caretPositionChanged() {

    }

    @Override
    public void keyTyped(KeyEvent event) {
        keyTyped();
    }

    protected void keyTyped() {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        keyPressed();
    }

    protected void keyPressed() {

    }

    @Override
    public void keyReleased(KeyEvent event) {
        keyReleased();
    }

    protected void keyReleased() {

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        mouseClicked();
    }

    protected void mouseClicked() {

    }

    @Override
    public void mousePressed(MouseEvent event) {
        mousePressed();
    }

    protected void mousePressed() {

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        mouseReleased();
    }

    protected void mouseReleased() {

    }

    @Override
    public void mouseEntered(MouseEvent event) {
        mouseEntered();
    }

    protected void mouseEntered() {

    }

    @Override
    public void mouseExited(MouseEvent event) {
        mouseExited();
    }

    protected void mouseExited() {

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        mouseDragged();
    }

    protected void mouseDragged() {

    }

    @Override
    public void mouseMoved(MouseEvent event) {
        mouseMoved();
    }

    protected void mouseMoved() {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        mouseWheelMoved();
    }

    protected void mouseWheelMoved() {

    }
}
