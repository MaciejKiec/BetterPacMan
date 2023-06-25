package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class YellowGhost  extends Entity implements Drawing{
    private Image ghost_up, ghost_down, ghost_left, ghost_right;

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
    public void control(){
        //walks randomly on the map
        int xId = (int) x / 24;
        int yId = (int) y / 24;
        int ch = Map.pixels[yId][xId];
        while(!((direction != 1 || (ch & 1) == 0)
                && (direction != 3 || (ch & 4) == 0)
                && (direction != 2 || (ch & 2) == 0)
                && (direction != 4 || (ch & 8) == 0))){
            Random random = new Random();
            direction = random.nextInt(4) + 1;
        }
        move();
    }
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
