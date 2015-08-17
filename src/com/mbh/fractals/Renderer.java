package com.mbh.fractals;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    private RenderParams lastParams;
    private BufferedImage backBuffer;
    private Graphics2D backBufferGraphics;

    public void update(RenderParams params) {
        if (params.equals(lastParams)) {
            return;
        }
        if (!params.dimensionsMatch(lastParams)) {
            backBuffer = new BufferedImage(params.width, params.height, BufferedImage.TYPE_INT_RGB);
            backBufferGraphics = backBuffer.createGraphics();
        }
        resetProgress();
        lastParams = params;
    }

    public void step() {
        backBufferGraphics.setColor(Color.RED);
        backBufferGraphics.fillRect(0,0,50,50);
    }

    public BufferedImage getResult() {
        return backBuffer;
    }

    private void resetProgress() {

    }
}
