package com.mbh.fractals;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * FractalExplorer component.
 * Contains the core, component-level logic of the application.
 */
public class FractalExplorer extends Canvas implements Runnable {

    public FractalExplorer(Container parent) {
        this.setSize(parent.getSize());
    }

    @Override
    public void run() {
        this.createBufferStrategy(2);
        BufferStrategy bufferStrat = this.getBufferStrategy();
        final long frameLength = (long) (1e9 / 60);
        while (this.isDisplayable()) {
            long frameEnd = System.nanoTime() + frameLength;

            /* Update all subsystems */

            do {
                System.out.println("Stepping renderer");
            } while (System.nanoTime() < frameEnd);

            Graphics g = bufferStrat.getDrawGraphics();
            //g.drawImage(backBuffer, 0, 0, null);
            g.dispose();
            bufferStrat.show();
        }
    }
}
