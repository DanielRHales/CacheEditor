package com.resource;

import com.logging.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public class Resource {

    private static final Image ICON = getResourceImage("Icon", "png", new Dimension(32, 32));

    public static final Image PACKAGE = getResourceImage("Package", "png", new Dimension(16, 16));

    public static final Image RELOAD = getResourceImage("Reload", "png", new Dimension(16, 16));

    public static final Image DELETE = getResourceImage("Delete", "png", new Dimension(16, 16));

    public static final Image SINGLE_PACK = getResourceImage("SinglePack", "png", new Dimension(16, 16));

    public static final Image MULTIPLE_PACK = getResourceImage("MultiplePack", "png", new Dimension(16, 16));

    public static final Image DUMP_DATA = getResourceImage("DumpData", "png", new Dimension(16, 16));

    public static final List<Image> ICONS_LIST = Arrays.asList(
            getResizedImage(ICON, 16, 16),
            getResizedImage(ICON, 32, 32)
    );

    public static Image getResourceImage(String name) {
        try {
            return ImageIO.read(Resource.class.getResource(String.format("/%s", name)));
        } catch (Exception ex) {
            Logger.log(Resource.class, Level.WARNING, String.format("Error getting Resource Image \'%s\'.", name), ex);
            return null;
        }
    }

    public static Image getResourceImage(String name, String extension) {
        return getResourceImage(String.format("%s.%s", name, extension));
    }

    public static Image getResourceImage(String name, String extension, Dimension dimension) {
        return getResourceImage(String.format("%s_%dx%d", name, dimension.width, dimension.height), extension);
    }

    public static Image getResizedImage(Image image, int width, int height) {
        BufferedImage value = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = value.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
        return value;
    }

    public static Image getResizedImage(Image image, int width, int height, int originalWidth, int originalHeight, float alpha) {
        final BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = resized.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graphics.drawImage(image, 0, 0, originalWidth, originalHeight, null);
        graphics.dispose();
        return resized;
    }

    public static Image getFilteredImage(Image image, final Color color) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(image.getSource(), new RGBImageFilter() {
                    public final int filterRGB(int x, int y, int rgb) {
                        return ((rgb | 0xFF000000) != (color.getRGB() | 0xFF000000)) ? rgb : 0x00FFFFFF & rgb;
                    }
                })
        );
    }
}
