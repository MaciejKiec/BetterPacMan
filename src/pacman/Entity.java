package pacman;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
/**
 * The Entity class represents a generic entity in the Pac-Man game.
 * It encapsulates the position, direction, and movement functionality of an entity.
 */
public class Entity{
    protected int x, y; // The x and y coordinates of the entity
    protected int direction; // The direction the entity is facing
    protected Boolean go = true; // Indicates whether the entity can move
    protected int SPEED; // The speed at which the entity moves
    protected final int[] VALID_SPEED = {1, 2, 3, 6, 8}; // The valid speeds for the entity

    /**
     * Changes the speed of the entity.
     *
     * @param speed The new speed value for the entity.
     * @throws IllegalArgumentException If the specified speed is not a valid speed.
     */
    protected void changeSpeed(int speed) {
        try {
            int index = Arrays.binarySearch(VALID_SPEED, speed);
            if (index < 0) {
                throw new IllegalArgumentException("Value not found in array");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    /**
     * Points the entity upwards.
     */
    public void pointUp() {
        direction = 2;
    }
    /**
     * Points the entity to the right.
     */
    public void pointRight() {
        direction = 3;
    }
    /**
     * Points the entity to the left.
     */
    public void pointLeft() {
        direction = 1;
    }
    /**
     * Points the entity downwards.
     */
    public void pointDown() {
        direction = 4;
    }
    /**
     * Moves the entity based on its current direction and speed.
     */
    protected void move() {
        if (go) {
            switch (direction) {
                case 1:
                    x -= SPEED;
                    break;
                case 2:
                    y -= SPEED;
                    break;
                case 3:
                    x += SPEED;
                    break;
                case 4:
                    y += SPEED;
                    break;
            }
        }
    }
}
