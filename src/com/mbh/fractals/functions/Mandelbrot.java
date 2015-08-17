package com.mbh.fractals.functions;

public class Mandelbrot implements IFunction {
    @Override
    public int f(double zr, double zi, int N) {
        double cr = zr;
        double ci = zi;
        int n = 0;
        while (n < N && zr * zr + zi * zi < 4.0) {
            double temp = 2.0 * zi * zr;
            zr = cr + zr * zr - zi * zi;
            zi = ci + temp;
            n++;
        }
        return n;
    }
}
