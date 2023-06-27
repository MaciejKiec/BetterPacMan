package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.*;
/**
 * The RedGhost class represents a red ghost entity in the Pacman game.
 * It implements the Drawing and Ghost interfaces.
 * The red ghost chases and tries to capture the Pacman.
 */
public class RedGhost extends Entity implements Drawing, Ghost, Runnable {
    private Image ghost_up;     // Image representing the ghost facing up
    private Image ghost_down;   // Image representing the ghost facing down
    private Image ghost_left;   // Image representing the ghost facing left
    private Image ghost_right;  // Image representing the ghost facing right
    private boolean dodge = true;  // Flag indicating if the ghost is dodging obstacles

    /**
     * Constructs a RedGhost object with the specified starting coordinates.
     *
     * @param start_x The starting x-coordinate of the ghost.
     * @param start_y The starting y-coordinate of the ghost.
     */
    public RedGhost(int start_x, int start_y) {
        try {
            x = start_x;
            y = start_y;
            direction = 1;
            SPEED = 1;
            loadImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        move();
    }

    /**
     * Checks if the ghost is dodging any obstacles in its current direction.
     * Updates the dodge flag accordingly.
     * Prints "dodge" to the console if the ghost is dodging.
     */
    public void IsDodging() {
        int xId = (int) x / 24;
        int yId = (int) y / 24;
        int ch = Map.pixels[yId][xId];
        dodge = !((direction != 1 || (ch & 1) == 0)
                && (direction != 3 || (ch & 4) == 0)
                && (direction != 2 || (ch & 2) == 0)
                && (direction != 4 || (ch & 8) == 0));
        if (dodge) {
            System.out.println("dodge");
        }
    }
    /**
     * Controls the movement of the ghost.
     * The ghost chases the Pacman by adjusting its direction based on the relative positions.
     * If dodge flag is false, the ghost directly chases the Pacman.
     * If dodge flag is true, the ghost tries to avoid obstacles while chasing.
     *
     * @param pac The Pacman object to chase.
     */
    public void control(Pacman pac) {
        //chases pacman
        int xId = (int) x / 24;
        int yId = (int) y / 24;
        int difference_x = x - pac.x;
        int difference_y = y - pac.y;
        int ch = Map.pixels[yId][xId];
//            if (!dodge) {
                if (difference_x > 0) {
                    direction = 1;
                } else {
                    direction = 3;
                }
                if (difference_x > -1 && difference_x < 1) {
                    if (difference_y > 0) {
                        direction = 2;
                    } else {
                        direction = 4;
                    }
                }
//            }
//            else {
//                if ((ch & 1) != 0) {
//                    direction = 2;
//
//                } else if ((ch & 2) != 0) {
//                    direction = 3;
//
//                } else if ((ch & 4) != 0) {
//                    direction = 4;
//
//                } else if ((ch & 8) != 0) {
//                    direction = 1;
//                }
//        }
        move();
    }

//        int difference_x = Math.abs(x - pac.x);
//        int difference_y = Math.abs(y - pac.y);
////        System.out.println(difference_x);
////        System.out.println(difference_y);
//
//        int manhattanDistance = difference_x + difference_y;
//        if (dodge) {
//            if (difference_x >= difference_y) {
//                if (pac.x > x) {
//                    direction = 3;
//                } else {
//                    direction = 1;
//                }
//            } else {
//                if (pac.y > y)
//                    direction = 2;
//                else
//                    direction = 4;
//            }
//        } else {
//            Random random = new Random();
//            direction = random.nextInt(4) + 1;
//        }
//        move();
    /**
     * Loads the ghost images from the specified files.
     *
     * @throws IOException if there is an error loading the images.
     */
    public void loadImages() throws IOException {
        try {
            ghost_up = new ImageIcon("images/red_up.gif").getImage();
            ghost_down = new ImageIcon("images/red_down.gif").getImage();
            ghost_left = new ImageIcon("images/red_left.gif").getImage();
            ghost_right = new ImageIcon("images/red_right.gif").getImage();
        } catch (Exception e) {
            throw new IOException("Invalid number format in the file.");
        }
    }
    /**
     * Draws the ghost on the game panel based on its current direction.
     *
     * @param g2d The Graphics2D object used for drawing.
     * @param panel The JPanel to draw the ghost on.
     */
    public void draw(Graphics2D g2d, JPanel panel) {
        switch (direction) {
            case 1:
                g2d.drawImage(ghost_left, x, y, panel);
                break;
            case 2:
                g2d.drawImage(ghost_up, x, y, panel);
                break;
            case 3:
                g2d.drawImage(ghost_right, x, y, panel);
                break;
            case 4:
                g2d.drawImage(ghost_down, x, y, panel);

        }
    }

}
