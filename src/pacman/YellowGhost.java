package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
/**
 * The YellowGhost class represents a yellow ghost entity in the Pacman game.
 * It extends the Entity class and implements the Drawing interface.
 * This ghost walks randomly on the map and has four different directions: up, down, left, and right.
 * It loads images for each direction and can be drawn on a panel using the draw method.
 */
public class YellowGhost  extends Entity implements Drawing, Runnable{
    private Image ghost_up, ghost_down, ghost_left, ghost_right;
    /**
     * Constructs a YellowGhost object with the specified starting position.
     *
     * @param start_x The x-coordinate of the starting position.
     * @param start_y The y-coordinate of the starting position.
     */
    public YellowGhost(int start_x, int start_y){
        try {
            x = start_x;
            y = start_y;
            direction = 1;
            SPEED = 2;
            loadImages();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run(){

    }
    /**
     * Controls the movement of the YellowGhost.
     * The ghost walks randomly on the map by selecting a random direction
     * as long as the selected direction is not blocked by walls.
     */
    public void control() {
        // Determine the grid position of the YellowGhost
        int xId = (int) x / 24;
        int yId = (int) y / 24;
        int ch = Map.pixels[yId][xId];

        // Select a random direction that is not blocked by walls
        while (!((direction != 1 || (ch & 1) == 0)
                && (direction != 3 || (ch & 4) == 0)
                && (direction != 2 || (ch & 2) == 0)
                && (direction != 4 || (ch & 8) == 0))) {
            Random random = new Random();
            direction = random.nextInt(4) + 1;
        }

        move();
    }
    /**
     * Loads the images for each direction of the YellowGhost.
     *
     * @throws IOException If there is an error loading the images.
     */
    public void loadImages() throws IOException {
        try {
            ghost_up = new ImageIcon("images/yellow_up.gif").getImage();
            ghost_down = new ImageIcon("images/yellow_down.gif").getImage();
            ghost_left = new ImageIcon("images/yellow_left.gif").getImage();
            ghost_right = new ImageIcon("images/yellow_right.gif").getImage();
        }
        catch (Exception e) {
            throw new IOException("Invalid number format in the file.");
        }
    }
    /**
     * Draws the YellowGhost on the panel using the specified graphics context.
     *
     * @param g2d   The Graphics2D context to draw on.
     * @param panel The JPanel on which to draw the ghost.
     */
    public void draw(Graphics2D g2d, JPanel panel) {
        switch (direction){
            case 1:
                g2d.drawImage(ghost_left, x, y, panel);
                break;
            case 2:
                g2d.drawImage(ghost_up, x, y,  panel);
                break;
            case 3:
                g2d.drawImage(ghost_right, x, y, panel);
                break;
            case 4:
                g2d.drawImage(ghost_down, x, y, panel);

        }
    }
}
