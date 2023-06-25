package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * The Drawing interface represents an object that can be drawn on a JPanel using the Graphics2D class.
 * It provides methods for drawing and loading images.
 */
public interface Drawing {
    /**
     * Draws the object using the specified Graphics2D object on the provided JPanel.
     *
     * @param g      the Graphics2D object used for drawing
     * @param panel  the JPanel on which the object will be drawn
     */
    public void draw(Graphics2D g, JPanel panel);
    /**
     * Loads the images required for drawing the object.
     *
     * @throws IOException if there is an error while loading the images
     */
    public void loadImages() throws IOException;
}
