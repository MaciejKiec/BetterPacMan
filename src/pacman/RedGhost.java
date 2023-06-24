package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class RedGhost extends Entity implements Drawing, Ghost{
    private Image ghost_up, ghost_down, ghost_left, ghost_right;

    public RedGhost(int start_x, int start_y){
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
    public void control(){
        Random random = new Random();
        direction = random.nextInt(4) + 1;
        move();
    }
    public void loadImages() throws IOException {
        try {
            ghost_up = new ImageIcon("images/red_up.gif").getImage();
            ghost_down = new ImageIcon("images/red_down.gif").getImage();
            ghost_left = new ImageIcon("images/red_left.gif").getImage();
            ghost_right = new ImageIcon("images/red_right.gif").getImage();
        }
        catch (Exception e) {
            throw new IOException("Invalid number format in the file.");
        }
    }
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
