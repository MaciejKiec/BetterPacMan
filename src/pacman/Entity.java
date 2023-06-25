package pacman;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Entity{
    protected int x, y;
    protected int direction;
    protected Boolean go = true;
    protected int SPEED;
    protected final int[] VALID_SPEED = {1, 2, 3, 6, 8};


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

    public void pointUp() {
        direction = 2;
    }

    public void pointRight() {
        direction = 3;
    }

    public void pointLeft() {
        direction = 1;
    }

    public void pointDown() {
        direction = 4;
    }

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
