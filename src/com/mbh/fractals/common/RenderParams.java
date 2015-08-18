package com.mbh.fractals.common;

import com.mbh.fractals.colorschemes.IColorScheme;
import com.mbh.fractals.functions.IFunction;

public class RenderParams {

    public final int width, height;
    public final double cx, cy;
    public final double realDomain;
    public final int maxIterations;
    public final IFunction function;
    public final IColorScheme colorScheme;

    public RenderParams(int width, int height, double cx, double cy, double realDomain, int maxIterations, IFunction function, IColorScheme colorScheme) {
        this.width = width;
        this.height = height;
        this.cx = cx;
        this.cy = cy;
        this.realDomain = realDomain;
        this.maxIterations = maxIterations;
        this.function = function;
        this.colorScheme = colorScheme;
    }

    @Override
    public boolean equals(Object o) {
        /* If this object is compared with itself */
        if (o == this) {
            return true;
        }
        /* If this object is compared with a different type of object */
        if (!(o instanceof RenderParams)) {
            return false;
        }

        RenderParams r = (RenderParams) o;
        if (this.width != r.width || this.height != r.height) {
            return false;
        }
        if (this.cx != r.cx || this.cy != r.cy) {
            return false;
        }
        if (this.realDomain != r.realDomain) {
            return false;
        }
        if (this.maxIterations != r.maxIterations) {
            return false;
        }
        if(this.function != r.function) {
            return false;
        }
        if(this.colorScheme != r.colorScheme) {
            return false;
        }
        return true;
    }

    public boolean dimensionsMatch(RenderParams r) {
        if (r == null) {
            return false;
        }
        return (this.width == r.width && this.height == r.height);
    }
}
