package com.mbh.fractals.functions;

import com.mbh.fractals.common.FunctionParams;
import com.mbh.fractals.common.FunctionResult;
import com.mbh.math.Complex;

public class Mandelbrot implements IFunction {
    @Override
    public FunctionResult f(FunctionParams params) {
        double zr = params.arg.re;
        double zi = params.arg.im;
        double cr = params.arg.re;
        double ci = params.arg.im;
        int n = 0;
        while (n < params.maxIterations && zr * zr + zi * zi < 4.0) {
            double temp = 2.0 * zi * zr;
            zr = cr + zr * zr - zi * zi;
            zi = ci + temp;
            n++;
        }
        return new FunctionResult(new Complex(zr, zi), n, params);
    }
}
