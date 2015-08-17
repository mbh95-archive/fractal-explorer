package com.mbh.fractals.renderer;

import com.mbh.fractals.RenderParams;

public final class RenderUtils {
    private RenderUtils() {

    }

    public static double screenToComplexX(int screenX, RenderParams params) {
        return (screenX - params.width / 2.0) * params.realDomain / params.width;
    }

    public static double screenToComplexY(int screenY, RenderParams params) {
        return (screenY - params.height / 2.0) * params.realDomain / params.width;
    }
}
