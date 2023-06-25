package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
/**

 * The Pacman class represents the Pacman entity in the Pacman game.

 * It extends the Entity class and implements the Drawing interface.
 */
public class Pacman extends Entity implements Drawing{
    private Image pacman_up, pacman_down, pacman_left, pacman_right;
    /**
     * Constructs a Pacman object with the specified starting coordinates.
     *
     * @param start_x The x-coordinate of the starting position.
     * @param start_y The y-coordinate of the starting position.
     */
    public Pacman(int start_x, int start_y){

        try {
            x = start_x;
            y = start_y;
            direction = 1;
            SPEED = 3;
            loadImages();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Loads the images for different directions of Pacman.
     *
     * @throws IOException if there is an error loading the images.
     */
    public void loadImages() throws IOException {
        try {
            pacman_up = new ImageIcon("images/pacman_up.gif").getImage();
            pacman_down = new ImageIcon("images/pacman_down.gif").getImage();
            pacman_left = new ImageIcon("images/pacman_left.gif").getImage();
            pacman_right = new ImageIcon("images/pacman_right.gif").getImage();
        }
        catch (Exception e) {
            throw new IOException("Invalid number format in the file.");
        }
    }
    /**
     * Draws Pacman on the screen based on its current direction.
     *
     * @param g2d   The Graphics2D object to draw on.
     * @param panel The JPanel where Pacman is drawn.
     */
    public void draw(Graphics2D g2d, JPanel panel) {
        switch (direction){
            case 1:
                g2d.drawImage(pacman_left, x, y, panel);
                break;
            case 2:
                g2d.drawImage(pacman_up, x, y,  panel);
                break;
            case 3:
                g2d.drawImage(pacman_right, x, y, panel);
                break;
            case 4:
                g2d.drawImage(pacman_down, x, y, panel);

        }
    }
}
