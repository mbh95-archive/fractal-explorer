package com.mbh.fractals.controller;

import com.mbh.fractals.FractalExplorer;
import com.mbh.fractals.RenderParams;
import com.mbh.fractals.colorschemes.IColorScheme;
import com.mbh.fractals.colorschemes.LinearGradient;
import com.mbh.fractals.functions.IFunction;
import com.mbh.fractals.functions.Mandelbrot;
import com.mbh.input.Keyboard;
import com.mbh.input.Mouse;

import java.awt.*;

import static java.awt.event.KeyEvent.*;

public class Controller implements IController {

    private FractalExplorer parent;
    private double cx, cy;
    private double realDomain;
    private int maxIterations;
    private IFunction function;
    private IColorScheme colorScheme;

    private double movementSensitivity = 0.02;
    private int[] upKeys = new int[]{VK_W, VK_UP, VK_KP_UP};
    private int[] downKeys = new int[]{VK_S, VK_DOWN, VK_KP_DOWN};
    private int[] leftKeys = new int[]{VK_A, VK_LEFT, VK_KP_LEFT};
    private int[] rightKeys = new int[]{VK_D, VK_RIGHT, VK_KP_RIGHT};

    private int[] zoomInKeys = new int[]{VK_I};
    private int[] zoomOutKeys = new int[]{VK_O};

    private int[] doubleDetailKeys = new int[] {VK_E};
    private int[] halfDetailKeys = new int[] {VK_Q};

    private Toggle doubleDetailToggle = new Toggle();
    private Toggle halfDetailToggle = new Toggle();

    private Toggle rmbToggle = new Toggle();

    public Controller(FractalExplorer parent) {
        this.parent = parent;
        this.cx = 0.0;
        this.cy = 0.0;
        this.realDomain = 2.0;
        this.maxIterations = (1 << 6);
        this.function = new Mandelbrot();
        this.colorScheme = new LinearGradient(Color.BLACK, Color.BLUE);
        Keyboard.bindTo(parent);
        Mouse.bindTo(parent);
    }

    @Override
    public RenderParams getRenderParams() {

        if (Keyboard.isAtLeastOneKeyDown(upKeys))
            translate(0, movementSensitivity * realDomain);
        if (Keyboard.isAtLeastOneKeyDown(downKeys))
            translate(0, -movementSensitivity * realDomain);
        if (Keyboard.isAtLeastOneKeyDown(leftKeys))
            translate(-movementSensitivity * realDomain, 0);
        if (Keyboard.isAtLeastOneKeyDown(rightKeys))
            translate(movementSensitivity * realDomain, 0);

        if (Keyboard.isAtLeastOneKeyDown(zoomInKeys))
            zoom(0.9);
        if (Keyboard.isAtLeastOneKeyDown(zoomOutKeys))
            zoom(1.0 / 0.9);


        if (doubleDetailToggle.getDelta(Keyboard.isAtLeastOneKeyDown(doubleDetailKeys))) {
            maxIterations = Math.min(1<<16, maxIterations*2);
        }
        if(halfDetailToggle.getDelta(Keyboard.isAtLeastOneKeyDown(halfDetailKeys))) {
            maxIterations = Math.max(1, maxIterations / 2);
        }

        if (rmbToggle.getDelta(Mouse.rightButtonDown()))
            moveToScreen(Mouse.getX(), Mouse.getY());

        return new RenderParams(parent.getWidth(), parent.getHeight(), cx, cy, realDomain, maxIterations, function, colorScheme);
    }

    private void moveTo(double cx, double cy) {
        cx = cx;
        cy = cy;
    }

    private void moveToScreen(int screenX, int screenY) {
        cx = screenToDomainX(screenX);
        cy = screenToDomainY(screenY);
    }

    private void translate(double dx, double dy) {
        cx += dx;
        cy += dy;
    }

    private void zoom(double factor) {
        realDomain *= factor;
    }

    private double screenToDomainX(int screenX) {
        return cx + (screenX - parent.getWidth() / 2.0) * realDomain / parent.getWidth();
    }

    private double screenToDomainY(int screenY) {
        return cy + (-screenY + parent.getHeight() / 2.0) * realDomain / parent.getWidth();
    }

    private abstract class Delta<T> {
        T old;
        public abstract T getDelta(T cur);
    }

    private class Toggle extends Delta<Boolean> {
        @Override
        public Boolean getDelta(Boolean cur) {
            boolean temp = cur && (cur != old);
            old = cur;
            return temp;
        }
    }

    private class DeltaD extends Delta<Double> {

        @Override
        public Double getDelta(Double cur) {
            double temp = cur - old;
            old = cur;
            return temp;
        }
    }
}
