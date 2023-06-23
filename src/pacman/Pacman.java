package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Pacman extends Entity implements Drawing{
    private Image pacman_up, pacman_down, pacman_left, pacman_right;

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

    private void loadImages() throws IOException {
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
    public void changeSpeed(int speed){
        try {
            int index = Arrays.binarySearch(VALID_SPEED, speed);
            if (index < 0) {
                throw new IllegalArgumentException("Value not found in array");
            }
        }  catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void pointUp() {
        direction=2;
    }
    @Override
    protected void pointRight() {
        direction=3;
    }
    @Override
    protected void pointLeft() {
        direction=1;
    }
    @Override
    protected void pointDown() {
        direction=4;
    }
    @Override
    protected void move(){
        switch (direction){
            case 1:
                x-=SPEED;
                break;
            case 2:
                y-=SPEED;
                break;
            case 3:
                x+=SPEED;
                break;
            case 4:
                y+=SPEED;
                break;
        }
    }
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
