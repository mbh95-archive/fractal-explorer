package com.mbh.fractals.colorschemes;


import java.awt.*;

public class LinearGradient implements IColorScheme {

    int aR, aG, aB;
    int bR, bG, bB;

    public LinearGradient(Color a, Color b) {
        this.aR = a.getRed();
        this.aG = a.getGreen();
        this.aB = a.getBlue();
        this.bR = b.getRed();
        this.bG = b.getGreen();
        this.bB = b.getBlue();
    }


    @Override
    public Color getColor(int f, int N) {
        double Z = (double) f / (double) N;
        int R = (int) Math.round(aR * Z + bR * (1.0 - Z));
        int G = (int) Math.round(aG * Z + bG * (1.0 - Z));
        int B = (int) Math.round(aB * Z + bB * (1.0 - Z));
        return new Color(R, G, B);
    }
}
