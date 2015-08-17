package com.mbh.fractals.renderer;

import com.mbh.fractals.RenderParams;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ActiveRenderer implements IRenderer {

    private int blockSize;
    private int indX, indY;
    private RenderParams lastParams;
    private BufferedImage backBuffer;
    private Graphics2D backBufferGraphics;

    @Override
    public void updateParams(RenderParams params) {

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

    @Override
    public void step() {
        if (isDone()) {
            return;
        }

        int cxScreen = indX * blockSize + blockSize / 2;
        int cyScreen = indY * blockSize + blockSize / 2;

        double cRe = lastParams.cx + RenderUtils.screenToDomainX(cxScreen, lastParams);
        double cIm = lastParams.cy + RenderUtils.screenToDomainY(cyScreen, lastParams);

        int fResult = lastParams.function.f(cRe, cIm, lastParams.maxIterations);
        Color currentColor = lastParams.colorScheme.getColor(fResult, lastParams.maxIterations);
        backBufferGraphics.setColor(currentColor);
        backBufferGraphics.fillRect(indX * blockSize, indY * blockSize, blockSize, blockSize);

        indX++;
        if (indX * blockSize >= lastParams.width) {
            indX = 0;
            indY++;
        }
        if (indY * blockSize >= lastParams.height) {
            indY = 0;
            blockSize /= 2;
        }
    }

    @Override
    public boolean isDone() {
        return blockSize < 1;
    }

    @Override
    public BufferedImage getResult() {
        return backBuffer;
    }

    private void resetProgress() {
        blockSize = (1 << 8);
        indX = 0;
        indY = 0;
    }
}
