package com.mbh.fractals;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * FractalExplorer component.
 * Contains the core, component-level logic of the application.
 */
public class FractalExplorer extends Canvas implements Runnable {

    private Renderer renderer;

    public FractalExplorer(Container parent) {
        this.setSize(parent.getSize());
        this.renderer = new Renderer();
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
            renderer.update(renderParams);
            do {
                this.renderer.step();
            } while (System.nanoTime() < frameEnd);

            Graphics g = bufferStrat.getDrawGraphics();
            g.drawImage(this.renderer.getResult(), 0, 0, null);
            g.dispose();
            bufferStrat.show();
        }
    }
}
