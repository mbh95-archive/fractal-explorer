package com.mbh.fractals.functions;

import com.mbh.fractals.common.FunctionParams;
import com.mbh.fractals.common.FunctionResult;
import com.mbh.math.Complex;

public class Julia implements IFunction {

    private final Complex c;

    public Julia(double cRe, double cIm) {
        this.c = new Complex(cRe, cIm);
    }
    @Override
    public FunctionResult f(FunctionParams params) {
        Complex z = params.arg.getCopyOf();
        int n = 1;
        while(z.getLengthSquared()<4.0 && n < params.maxIterations) {
            z.multiply(z);
            z.add(c);
            n++;
        }
        return new FunctionResult(z, n, params);
    }
}
