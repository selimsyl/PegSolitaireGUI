package PegGame;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ImageToIcon implements Serializable {
    private final String path;

    public ImageToIcon(String path) {this.path = path;}

    public ImageIcon getIcon() {
        var imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);  // transform it back
    }
}
