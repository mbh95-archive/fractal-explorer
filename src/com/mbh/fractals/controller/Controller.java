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


    public Controller(FractalExplorer parent) {
        this.parent = parent;
        this.cx = 0.0;
        this.cy = 0.0;
        this.realDomain = 2.0;
        this.maxIterations = 128;
        this.function = new Mandelbrot();
        this.colorScheme = new LinearGradient(Color.BLACK, Color.BLUE);
        Keyboard.bindTo(parent);
        Mouse.bindTo(parent);
    }

    @Override
    public RenderParams getRenderParams() {
        if (Keyboard.isAtLeastOneKeyDown(upKeys))
            cy += movementSensitivity * realDomain;
        if (Keyboard.isAtLeastOneKeyDown(downKeys))
            cy -= movementSensitivity * realDomain;
        if (Keyboard.isAtLeastOneKeyDown(leftKeys))
            cx -= movementSensitivity * realDomain;
        if (Keyboard.isAtLeastOneKeyDown(rightKeys))
            cx += movementSensitivity * realDomain;

        return new RenderParams(parent.getWidth(), parent.getHeight(), cx, cy, realDomain, maxIterations, function, colorScheme);
    }
}
