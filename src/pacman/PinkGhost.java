package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
/**
 * The PinkGhost class represents a pink ghost entity in the Pacman game.
 * It extends the Entity class and implements the Drawing interface.
 * Pink ghosts are unpredictable and move randomly on the game board.
 */
public class PinkGhost extends Entity implements Drawing, Runnable {
    private Image ghost_up, ghost_down, ghost_left, ghost_right;
    int counter = 0;
    /**
     * Constructs a PinkGhost object with the specified starting coordinates.
     *
     * @param start_x the starting x-coordinate of the pink ghost
     * @param start_y the starting y-coordinate of the pink ghost
     */
    public PinkGhost(int start_x, int start_y){
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
     * Controls the movement of the pink ghost.
     * Pink ghosts move randomly and change direction unpredictably.
     */
    public void control() {
        // Increase the counter
        counter++;

        // Calculate the current cell coordinates
        int xId = (int) x / 24;
        int yId = (int) y / 24;
        int ch = Map.pixels[yId][xId];

        // Create a random number generator
        Random random = new Random();

        // Change direction every 60 frames (1 second)
        if (counter % 60 == 0) {
            direction = random.nextInt(4) + 1;
        }

        // Randomly choose a new direction until a valid one is found
        while (!((direction != 1 || (ch & 1) == 0)
                && (direction != 3 || (ch & 4) == 0)
                && (direction != 2 || (ch & 2) == 0)
                && (direction != 4 || (ch & 8) == 0))) {
            direction = random.nextInt(4) + 1;
        }

        // Move the pink ghost
        move();
    }
    /**
     * Loads the images of the pink ghost from the specified file paths.
     *
     * @throws IOException if an error occurs while loading the images
     */
    public void loadImages() throws IOException {
        try {
            // Load the images for different directions
            ghost_up = new ImageIcon("images/pink_up.gif").getImage();
            ghost_down = new ImageIcon("images/pink_down.gif").getImage();
            ghost_left = new ImageIcon("images/pink_left.gif").getImage();
            ghost_right = new ImageIcon("images/pink_right.gif").getImage();
        }
        catch (Exception e) {
            throw new IOException("Invalid number format in the file.");
        }
    }
    /**
     * Draws the pink ghost on the game panel based on its current direction.
     *
     * @param g2d   the graphics context used for drawing
     * @param panel the panel on which the pink ghost is drawn
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
