package com.mbh.input;

import java.awt.*;
import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
    private static final boolean[] buttons = new boolean[3];
    private static Point currentPosition = new Point();
    private static double scrollWheelValue = 0.0d;
    private static boolean isInside = false;
    private static final Mouse instance = new Mouse();

    public static void bindTo(Component component) {
        component.addMouseListener(instance);
        component.addMouseMotionListener(instance);
        component.addMouseWheelListener(instance);
    }

    public static boolean leftButtonDown() {
        return buttons[0];
    }

    public static boolean middleButtonDown() {
        return buttons[1];
    }

    public static boolean rightButtonDown() {
        return buttons[2];
    }

    public static Point getPosition() {
        return currentPosition;
    }

    public static int getX() {
        return currentPosition.x;
    }

    public static int getY() {
        return currentPosition.y;
    }

    public static double getScrollWheelValue() {
        return scrollWheelValue;
    }

    public static boolean isInside() {
        return isInside;
    }

    public static final Mouse getInstance() {
        return instance;
    }

    private Mouse() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isInside = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isInside = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton() - 1;
        if (button >= 0 && button < buttons.length)
            buttons[button] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton() - 1;
        if (button >= 0 && button < buttons.length)
            buttons[button] = false;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scrollWheelValue += e.getPreciseWheelRotation();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentPosition = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentPosition = e.getPoint();
    }
}