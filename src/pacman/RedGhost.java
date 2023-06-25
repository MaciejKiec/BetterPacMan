package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class RedGhost extends Entity implements Drawing, Ghost{
    private Image ghost_up, ghost_down, ghost_left, ghost_right;
    boolean dodge = true;

    public RedGhost(int start_x, int start_y){
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
    public void IsDodging(){
        int xId = (int) x / 24;
        int yId = (int) y / 24;
        int ch = Map.pixels[yId][xId];
        dodge = !((direction != 1 || (ch & 1) == 0)
                && (direction != 3 || (ch & 4) == 0)
                && (direction != 2 || (ch & 2) == 0)
                && (direction != 4 || (ch & 8) == 0));
        if (dodge){
            System.out.println("dodge");
        }
    }
    public void control(Pacman pac){
        //chases pacman
        int xId = (int) x / 24;
        int yId = (int) y / 24;
        int difference_x = x - pac.x;
        int difference_y = y - pac.y;
        int ch = Map.pixels[yId][xId];


        if(dodge == true) {
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
        }else{
            Random random = new Random();
            direction = random.nextInt(4) + 1;
        }
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
