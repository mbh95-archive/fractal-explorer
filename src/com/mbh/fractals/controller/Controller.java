package com.mbh.fractals.controller;

import com.mbh.fractals.FractalExplorer;
import com.mbh.fractals.colorschemes.OrbitGradient;
import com.mbh.fractals.common.RenderParams;
import com.mbh.fractals.colorschemes.IColorScheme;
import com.mbh.fractals.colorschemes.LinearGradient;
import com.mbh.fractals.colorschemes.SimpleLookup;
import com.mbh.fractals.functions.IFunction;
import com.mbh.fractals.functions.Julia;
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

    private int functionIndex;
    private IFunction[] functions = new IFunction[]{
            new Mandelbrot(),
            new Julia(0, 0)
    };

    private int colorIndex;
    private IColorScheme[] colorSchemes = new IColorScheme[]{
            new OrbitGradient(),
            new SimpleLookup("res/rainbows.png"),
            new LinearGradient(Color.BLACK, Color.WHITE),
            new LinearGradient(Color.WHITE, Color.BLACK),
            new SimpleLookup("res/blue-black.png"),
            new SimpleLookup("res/sine.png"),
            new SimpleLookup("res/square.png"),
            new SimpleLookup("res/purple and white.png"),
            new SimpleLookup("res/blue and brown.png")
    };

    private double movementSensitivity = 0.02;
    private int[] upKeys = new int[]{VK_W, VK_UP, VK_KP_UP};
    private int[] downKeys = new int[]{VK_S, VK_DOWN, VK_KP_DOWN};
    private int[] leftKeys = new int[]{VK_A, VK_LEFT, VK_KP_LEFT};
    private int[] rightKeys = new int[]{VK_D, VK_RIGHT, VK_KP_RIGHT};

    private DeltaD mouseXDelta = new DeltaD();
    private DeltaD mouseYDelta = new DeltaD();
    private Toggle rmbToggle = new Toggle();
    private DeltaD mouseWheelDelta = new DeltaD();

    private int[] zoomInKeys = new int[]{VK_I};
    private int[] zoomOutKeys = new int[]{VK_O};

    private int[] doubleDetailKeys = new int[]{VK_E};
    private int[] halfDetailKeys = new int[]{VK_Q};

    private Toggle doubleDetailToggle = new Toggle();
    private Toggle halfDetailToggle = new Toggle();

    private int[] colorUpKeys = new int[]{VK_PERIOD};
    private int[] colorDownKeys = new int[]{VK_COMMA};
    private Toggle colorUpToggle = new Toggle();
    private Toggle colorDownToggle = new Toggle();


    private int[] functionUpKeys = new int[]{VK_R};
    private int[] functionDownKeys = new int[]{VK_F};
    private Toggle functionUpToggle = new Toggle();
    private Toggle functionDownToggle = new Toggle();

    public Controller(FractalExplorer parent) {
        this.parent = parent;
        loadDefaultState();
        Keyboard.bindTo(parent);
        Mouse.bindTo(parent);
    }

    public void loadDefaultState() {
        this.cx = 0.0;
        this.cy = 0.0;
        this.realDomain = 2.0;
        this.maxIterations = 1 << 6;
        this.functionIndex = 0;
        this.colorIndex = 0;
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
            maxIterations = Math.min(1 << 16, maxIterations * 2);
        }
        if (halfDetailToggle.getDelta(Keyboard.isAtLeastOneKeyDown(halfDetailKeys))) {
            maxIterations = Math.max(1, maxIterations / 2);
        }

        if (rmbToggle.getDelta(Mouse.rightButtonDown()))
            moveToScreen(Mouse.getX(), Mouse.getY());

        if (colorUpToggle.getDelta(Keyboard.isAtLeastOneKeyDown(colorUpKeys))) {
            colorIndex++;
            colorIndex %= colorSchemes.length;
        }
        if (colorDownToggle.getDelta(Keyboard.isAtLeastOneKeyDown(colorDownKeys))) {
            colorIndex--;
            if (colorIndex < 0)
                colorIndex = colorSchemes.length - 1;
        }

        if (Mouse.leftButtonDown()) {
            double dx = mouseXDelta.getDelta(-(double) Mouse.getX() * realDomain / parent.getWidth());
            double dy = mouseYDelta.getDelta((double) Mouse.getY() * realDomain / parent.getWidth());
            translate(dx, dy);
        } else {
            mouseXDelta.reset();
            mouseYDelta.reset();
        }

        int dScroll = mouseWheelDelta.getDelta(Mouse.getScrollWheelValue()).intValue();
        if (dScroll != 0) {
            zoom(Math.pow(.9, -dScroll));
        }

        if (functionUpToggle.getDelta(Keyboard.isAtLeastOneKeyDown(functionUpKeys))) {
            functionIndex++;
            functionIndex %= functions.length;
        }
        if (functionDownToggle.getDelta(Keyboard.isAtLeastOneKeyDown(functionDownKeys))) {
            functionIndex--;
            if (functionIndex < 0)
                functionIndex = functions.length - 1;
        }

        IFunction function = functions[functionIndex];
        IColorScheme colorScheme = colorSchemes[colorIndex];

        if (function instanceof Julia && Keyboard.isKeyDown(VK_J)) {
            functions[functionIndex] = new Julia(screenToDomainX(Mouse.getX()) + cx, screenToDomainY(Mouse.getY()) + cy);
        }

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

    private class Flip extends Delta<Boolean> {

        @Override
        public Boolean getDelta(Boolean cur) {
            boolean temp = cur != old;
            old = cur;
            return temp;
        }
    }

    private class DeltaD extends Delta<Double> {
        public DeltaD() {
            this.old = Double.NaN;
        }

        @Override
        public Double getDelta(Double cur) {
            if (Double.isNaN(old)) {
                old = cur;
                return 0.0;
            }
            double temp = cur - old;
            old = cur;
            return temp;
        }

        public void reset() {
            this.old = Double.NaN;
        }
    }
}
