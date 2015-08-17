package com.mbh.input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private static final boolean[] keys = new boolean[0x10000];
    private static final Keyboard instance = new Keyboard();

    public static void bindTo(Component component) {
        component.addKeyListener(instance);
    }

    public static boolean isKeyDown(int key) {
        if (key >= 0 && key < keys.length)
            return keys[key];
        else
            return false;
    }

    public static boolean[] areKeysDown(int... keyList) {
        boolean[] ret = new boolean[keyList.length];
        for (int i = 0; i < keyList.length; i++) {
            ret[i] = isKeyDown(keyList[i]);
        }
        return ret;
    }

    public static boolean isAtLeastOneKeyDown(int... keyList) {
        for (int k : keyList) {
            if (isKeyDown(k))
                return true;
        }
        return false;
    }

    public static final Keyboard getInstance() {
        return instance;
    }

    private Keyboard() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code >= 0 && code < keys.length) {
            keys[code] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code >= 0 && code < keys.length) {
            keys[code] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}