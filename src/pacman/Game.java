package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {
    private Dimension dim;
    //    private final Font smallFont = new Font("Arial", Font.BOLD,14);
    private boolean inGame;

    private final int PACMAN_SPEED = 6;
    private Image pac_up, pac_down, pac_left, pac_right;
    private int pacmanX, pacmanY;
    private int direction;


    public Game() {
        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
        Timer timer = new Timer(100, this);
        timer.start();
    }

    private void initGame() {

        inGame = true;
        pacmanX = dim.width/2;
        pacmanY = dim.height/2;
    }


    private void loadImages() {
        pac_up = new ImageIcon("C:\\Users\\Michał\\IdeaProjects\\test\\img\\pac-up.png").getImage();
        pac_down = new ImageIcon("C:\\Users\\Michał\\IdeaProjects\\test\\img\\pac-down.png").getImage();
        pac_left = new ImageIcon("C:\\Users\\Michał\\IdeaProjects\\test\\img\\pac-left.png").getImage();
        pac_right = new ImageIcon("C:\\Users\\Michał\\IdeaProjects\\test\\img\\pac-right.png").getImage();
//        cherry = new ImageIcon("C:\\Users\\Michał\\IdeaProjects\\test\\img\\cherry.png").getImage();


    }

    private void initVariables() {
        dim = new Dimension(400, 400);
        direction = 1;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, dim.width, dim.height);


        if (inGame) {
        playGame(g2d);
        }
//        else {
//            showIntroScreen(g2d);
//        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    private void playGame(Graphics2D g2d) {
        movePacman();
        drawPacman(g2d);
//        moveGhosts(g2d);
//        checkMaze();
    }
    private void movePacman() {
        // Logic to update Pac-Man's position based on the current direction
            if (direction == 1) { // Left
                pacmanX -= PACMAN_SPEED;
            } else if (direction == 2) { // Up
                pacmanY -= PACMAN_SPEED;
            } else if (direction == 3) { // Right
                pacmanX += PACMAN_SPEED;
            } else if (direction == 4) { // Down
                pacmanY += PACMAN_SPEED;
            }
    }
    private void drawPacman(Graphics2D g2d) {
        if (direction == 1) {
            g2d.drawImage(pac_left, pacmanX, pacmanY, this);
        } else if (direction == 2) {
            g2d.drawImage(pac_up, pacmanX, pacmanY, this);
        } else if (direction == 3){
            g2d.drawImage(pac_right, pacmanX, pacmanY, this);
        } else if (direction == 4) {
            g2d.drawImage(pac_down, pacmanX, pacmanY, this);
        }
    }


        class TAdapter extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (inGame) {
                    if (key == KeyEvent.VK_LEFT) {
//                        System.out.println("left");
                        direction = 1;
                    } else if (key == KeyEvent.VK_RIGHT) {
//                        System.out.println("right");
                        direction = 3;

                    } else if (key == KeyEvent.VK_UP) {
//                        System.out.println("up");
                        direction = 2;

                    } else if (key == KeyEvent.VK_DOWN) {
//                        System.out.println("down");
                        direction = 4;

                    }
                }
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
