package com.mbh.fractals.renderer;

import com.mbh.fractals.common.RenderParams;

public class RenderUtils {
    private RenderUtils() {

    }

    public static double screenToDomainX(int screenX, RenderParams params) {
        return (screenX - params.width / 2.0) * params.realDomain / params.width;
    }

    public static double screenToDomainY(int screenY, RenderParams params) {
        return (-screenY + params.height / 2.0) * params.realDomain / params.width;
    }
}
