package com.mbh.fractals.colorschemes;

import com.mbh.util.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lookup implements IColorScheme {

    private int[] pixels;
    private boolean absolute = true;
    private boolean blackOverride = true;

    public Lookup(BufferedImage src, boolean absolute) {
        this.pixels = ImageUtils.getRaster(src);
        this.absolute = absolute;
    }

    public Lookup(String path, boolean absolute) {
        this(ImageUtils.loadImage(path), absolute);
    }

    public Lookup(String path, int x, int y, int w, int h, boolean absolute) {
        BufferedImage fin = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        fin.getGraphics().drawImage(ImageUtils.loadImage(path), 0, 0, null);
        this.pixels = ImageUtils.getRaster(fin);
        this.absolute = absolute;
    }

    @Override
    public Color getColor(int f, int N) {
        if (blackOverride && f == N)
            return Color.BLACK;
        int index = 0;
        if (absolute) {
            index = f % this.pixels.length;
        } else {
            double Z = ((double) f / (double) N);
            index = (int) Math.round(Z * (this.pixels.length - 1));
        }
        int rgb = this.pixels[index % this.pixels.length];
        return new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, (rgb & 0xFF));
    }
}
