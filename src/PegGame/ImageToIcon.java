package PegGame;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Used for reading images and converting them to icons
 * for board JButton cells
 */
public class ImageToIcon implements Serializable {
    /**
     * Path where cell image is
     */
    private final String path;

    /**
     * @param path Path for image read
     */
    public ImageToIcon(String path) {this.path = path;}

    /**
     * @return ImageIcon
     */
    public ImageIcon getIcon() {
        var imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}
