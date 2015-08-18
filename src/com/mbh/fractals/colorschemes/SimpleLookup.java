package com.mbh.fractals.colorschemes;

import com.mbh.fractals.common.FunctionResult;
import com.mbh.util.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SimpleLookup implements IColorScheme {

    private Color[] colorTable;

    public SimpleLookup(String srcPath) {
        this(ImageUtils.loadImage(srcPath));
    }

    public SimpleLookup(BufferedImage src) {
        this.colorTable = new Color[src.getWidth()];
        for(int i = 0; i< src.getWidth(); i++) {
            int rgb  = src.getRGB(i, 0);
            this.colorTable[i] = new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, (rgb & 0xFF));
        }
    }

    @Override
    public Color getColor(FunctionResult f) {
        if (f.escapeIterations == f.functionParams.maxIterations)
            return Color.BLACK;
        return colorTable[f.escapeIterations % colorTable.length];
    }
}
