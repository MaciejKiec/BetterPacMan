package pacman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public interface Drawing {
    public void draw(Graphics2D g, JPanel panel);
    public void loadImages() throws IOException;
}
