package com.mbh.fractals;

import com.mbh.fractals.renderer.ActiveRenderer;
import com.mbh.fractals.renderer.IRenderer;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * FractalExplorer component.
 * Contains the core, component-level logic of the application.
 */
public class FractalExplorer extends Canvas implements Runnable {

    private IRenderer renderer;

    public FractalExplorer(Container parent) {
        this.setSize(parent.getSize());
        this.renderer = new ActiveRenderer();
    }

    @Override
    public void run() {
        this.createBufferStrategy(2);
        BufferStrategy bufferStrat = this.getBufferStrategy();
        final long frameLength = (long) (1e9 / 60);
        while (this.isDisplayable()) {
            long frameEnd = System.nanoTime() + frameLength;

            /* Update all subsystems */
            RenderParams renderParams = new RenderParams(this.getWidth(), this.getHeight(), 0.0, 0.0, 2.0, 128);
            renderer.updateParams(renderParams);
            do {
                if (!renderer.isDone()) {
                    renderer.step();
                } else {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } while (System.nanoTime() < frameEnd);

            Graphics g = bufferStrat.getDrawGraphics();
            g.drawImage(renderer.getResult(), 0, 0, null);
            g.dispose();
            bufferStrat.show();
        }
    }
}
