package pacman;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {
    public int width, height;
    public final int BLOCK_SIZE = 24;
    public int points;
    public static int[][] pixels;

    public Map(String filePath) {
        try {
            pixels = loadMapFromFile(filePath);
            width = pixels.length;
            height = pixels[0].length;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean completeCheck() {

        for (var j : pixels)
            for (var i : j)
                if (i != 0)
                    return false;
        return true;
    }

    public boolean checkSquare(Entity entity) {
        boolean point = false;
        int xId, yId, ch, px;

            xId = (int) entity.x / BLOCK_SIZE;
            yId = (int) entity.y / BLOCK_SIZE;
            ch = pixels[yId][xId];
            if ((ch & 16) != 0) {
                px = (ch & 15);
                if (px == 0)
                    pixels[yId][xId] = 32;
                else
                    pixels[yId][xId] = px;
                point = true;
            }

           entity.go = (entity.direction != 1 || (ch & 1) == 0)
                   && (entity.direction != 3 || (ch & 4) == 0)
                   && (entity.direction != 2 || (ch & 2) == 0)
                   && (entity.direction != 4 || (ch & 8) == 0);

        return point;
    }

    public int[][] loadMapFromFile(String filePath) throws IOException {
        List<int[]> pixels = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] values = line.trim().split(", ");
                    int[] row = new int[values.length];

                    for (int i = 0; i < values.length; i++) {
                        row[i] = Integer.parseInt(values[i]);
                    }
                    pixels.add(row);
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid number format in the file.");
                }
            }
        }

        // Convert the list of integers to an array
        int[][] array = new int[pixels.size()][];
        for (int i = 0; i < array.length; i++) {
            array[i] = pixels.get(i);
        }

        return array;
    }

    public void draw(Graphics2D g2d, int BLOCK_SIZE) {
        for (int y = 0; y < height * BLOCK_SIZE; y += BLOCK_SIZE) {
            for (int x = 0; x < width * BLOCK_SIZE; x += BLOCK_SIZE) {
                int row = y / BLOCK_SIZE;
                int col = x / BLOCK_SIZE;


                g2d.setColor(new Color(0, 72, 251));
                g2d.setStroke(new BasicStroke(5));
//              FILL  WALLS
                if ((pixels[row][col] == 0)) {
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
//                LEFT WALLS
                if ((pixels[row][col] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }
//                UPPER WALLS
                if ((pixels[row][col] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }
//                RIGHT WALLS
                if ((pixels[row][col] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }
//                DOWN WALLS
                if ((pixels[row][col] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }
                if ((pixels[row][col] & 16) != 0) {
                    g2d.setColor(new Color(255, 255, 255));
                    g2d.fillOval(x + BLOCK_SIZE / 2, y + BLOCK_SIZE / 2, 6, 6);
                }
            }
        }
    }
}
