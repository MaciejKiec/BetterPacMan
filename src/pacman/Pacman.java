package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    public void draw(Graphics2D g2d, JPanel panel) {
        switch (direction) {
            case 1 -> g2d.drawImage(pacman_left, x, y, panel);
            case 2 -> g2d.drawImage(pacman_up, x, y, panel);
            case 3 -> g2d.drawImage(pacman_right, x, y, panel);
            case 4 -> g2d.drawImage(pacman_down, x, y, panel);
        }
    }
//    public static int getX(){
//    }
//    public static int getY(){
//    }
}
