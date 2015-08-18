package com.mbh.fractals.colorschemes;

import com.mbh.fractals.common.FunctionResult;

import java.awt.*;

public class OrbitGradient implements IColorScheme {
    double redShift,  greenShift, blueShift;
    double redScale, greenScale, blueScale;

    public OrbitGradient(Color a, Color b) {
        redScale = 1;
        greenScale = 1;
        blueScale = 1;

        redShift = -1;
        greenShift = 0;
        blueShift = 0.37;
    }

    @Override
    public Color getColor(FunctionResult f) {
        if(f.escapeIterations == f.functionParams.maxIterations)
            return Color.BLACK;
        double Z = (double) f.escapeIterations / (double) f.functionParams.maxIterations;
        double modZ = f.finalZ.re * f.finalZ.re + f.finalZ.im * f.finalZ.im;
        double N = 1.0*f.escapeIterations - Math.log(Math.log(Math.sqrt(modZ))/Math.log(2))/Math.log(2);

        int R = (int)Math.round(127.5 * Math.sin((N+redShift)*redScale) + 127.5);
        int G = (int)Math.round(127.5 * Math.sin((N+greenShift)*greenScale)+ 127.5);
        int B = (int)Math.round(127.5 * Math.sin((N + blueShift) * blueScale)+ 127.5);
        return new Color(R, G, B);
    }
}
