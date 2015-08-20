package com.mbh.fractals.colorschemes;

import com.mbh.fractals.common.FunctionResult;

import java.awt.*;

public class OrbitGradient implements IColorScheme {
    double colorScale;
    double redShift, greenShift, blueShift;
    double redScale, greenScale, blueScale;

    public OrbitGradient() {

        colorScale = 32.0;

        redScale = 1;
        greenScale = 1;
        blueScale = 1;

        redShift = -1;
        greenShift = 0;
        blueShift = 0.37;
    }


    @Override
    public Color getColor(FunctionResult f) {
        if (f.escapeIterations == f.functionParams.maxIterations)
            return Color.BLACK;

        double N = 1.0 * f.escapeIterations - Math.log(Math.log(f.finalZ.getLength())) / Math.log(2.0);
        N/=(1.0*f.functionParams.maxIterations);
        N*=colorScale;
        int R = (int) Math.round(255.0 * (Math.sin((N + redShift) * redScale) + 1.0) / 2.0);
        int G = (int) Math.round(255.0 * (Math.sin((N + greenShift) * greenScale) + 1.0) / 2.0);
        int B = (int) Math.round(255.0 * (Math.sin((N + blueShift) * blueScale) + 1.0) / 2.0);
        return new Color(R, G, B);
    }
}
