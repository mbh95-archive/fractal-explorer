package com.mbh.fractals.common;

import com.mbh.math.Complex;

public class FunctionResult {
    public final Complex finalZ;
    public final int escapeIterations;
    public final FunctionParams functionParams;

    public FunctionResult(Complex finalZ, int escapeIterations, FunctionParams functionParams) {
        this.finalZ = finalZ;
        this.escapeIterations = escapeIterations;
        this.functionParams = functionParams;
    }
}
