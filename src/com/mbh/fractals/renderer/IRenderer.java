package com.mbh.fractals.renderer;

import com.mbh.fractals.common.RenderParams;

import java.awt.image.BufferedImage;

public interface IRenderer {
    void updateParams(RenderParams params);
    void step();
    boolean isDone();
    BufferedImage getResult();
}
