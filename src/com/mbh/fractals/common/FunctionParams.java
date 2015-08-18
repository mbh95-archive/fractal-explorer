package com.mbh.fractals.common;

import com.mbh.math.Complex;

public class FunctionParams {

    public final Complex arg;
    public final int maxIterations;

    public FunctionParams(Complex arg, int maxIterations) {
        this.arg = arg;
        this.maxIterations = maxIterations;
    }
}
