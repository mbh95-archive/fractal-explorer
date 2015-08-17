package com.mbh.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

public class ImageUtils {

    public static final BufferedImage loadImage(String path) {
        return loadImage(path, false);
    }

    public static final BufferedImage loadImage(String path, boolean transparency) {
        char beg = path.charAt(0);
        if (beg == '/' || beg == '\\' || beg == '.')
            path = path.substring(1);
        int format = transparency ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage img = new BufferedImage(1, 1, format);
        try {
            BufferedImage res = ImageIO.read(ImageUtils.class.getResourceAsStream("/" + path));
            img = new BufferedImage(res.getWidth(), res.getHeight(), format);
            img.getGraphics().drawImage(res, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static final int[] getRaster(BufferedImage src) {
        return ((DataBufferInt) src.getRaster().getDataBuffer()).getData();
    }
}
