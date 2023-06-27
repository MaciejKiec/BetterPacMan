package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Game class represents the main game panel for the Pacman game.
 * It extends JPanel and implements ActionListener.
 */
public class Game extends JPanel implements ActionListener {
    private Dimension dim;
    private final Font smallFont = new Font("Arial", Font.BOLD, 16);
    private boolean inGame;
    private boolean dying;
//    private boolean showScore;

    private int lives, score;
//    private boolean gameOver;
    private JButton restartButton;
//    private JButton scoresButton;
//    private JLabel scoresLabel;

    private Image heart;


    private Pacman pacman;
    private Map map;
    private RedGhost red;
    private PinkGhost pink;
    private YellowGhost yellow;

    Timer timer;

    /**
     * Constructs a new Game instance.
     * Initializes the game by loading images, setting up variables, adding key listener,
     * and starting the game timer.
     */
    public Game() {
        loadImages();
        dim = new Dimension(400, 400);
        timer = new Timer(100, this);

        init();
        restartGame();
        addKeyListener(new TAdapter());
    }

    private void init() {
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setLayout(null);

        initializeRestartButton();
//        initializeScoresButton();
//        initializeScoresLabel();

        this.add(restartButton);
//        this.add(scoresButton);
//        this.add(scoresLabel);
    }

    private void setButtonVisibility(boolean visable){

        restartButton.setVisible(visable);
//        scoresButton.setVisible(visable);
    }

    private void initializeRestartButton() {
        restartButton = new JButton("Restart");
        restartButton.setBounds(380 / 2 - 100, 380 / 2 + 200, 100, 50);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        restartButton.setVisible(false);
    }

//    private void initializeScoresButton() {
//        scoresButton = new JButton("Show Scores");
//        scoresButton.setBounds(380 / 2 + 10, 420 / 2 + 200, 120, 50);
//        scoresButton.addActionListener(e -> {
//            showScore = true;
//            repaint();
////            showScores();
//            restartButton.setVisible(false);
//            scoresButton.setVisible(false);
//        });
//        scoresButton.setVisible(false);
//    }
//
//    private void initializeScoresLabel() {
//        scoresLabel = new JLabel();
//        scoresLabel.setBounds(380 / 2 - 200, 100, 400, 400);
//        scoresLabel.setForeground(Color.white);
//        scoresLabel.setFont(new Font("Serif", Font.PLAIN, 18));
//        scoresLabel.setVerticalAlignment(SwingConstants.TOP);
//        scoresLabel.setHorizontalAlignment(SwingConstants.LEFT);
//        scoresLabel.setVisible(false);
//    }

    /**
     * Initializes the variables required for the game.
     * Creates the map, sets the dimensions, initializes the characters (pacman, ghosts),
     * and sets the initial score and lives.
     */

    private void restartLevel() {
        map = new Map("lvl/level1.txt");
        pacman = new Pacman(300, 300);
        red = new RedGhost(dim.width / 2, dim.height / 2);
        pink = new PinkGhost(dim.width / 2, dim.height / 2);
        yellow = new YellowGhost(dim.width / 2, dim.height / 2);
    }


    private void restartGame() {
            restartLevel();
            score = 0;
            lives = 3;
//            gameOver = false;
            inGame = true;
//            showScore = false;
            dying = false;

            timer.restart();
            timer.start();
    }

    /**
     * Loads the required images for the game.
     */
    private void loadImages() {
        heart = new ImageIcon("images/heart.png").getImage();
    }


    /**
     * Paints the game panel.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillRect(0, 0, dim.width + 5, dim.height + 40);

        if (inGame){
            g2d.setColor(Color.black);
            map.draw(g2d, map.BLOCK_SIZE);
            drawScore(g2d);
            playGame(g2d);
        }
        else {
                g2d.setColor(Color.yellow);
                g2d.setFont(new Font("Serif", Font.BOLD, 20));
                FontMetrics metrics1 = getFontMetrics(g2d.getFont());
                String score_string = "Score: " + score;
                g2d.drawString(score_string, (380 - metrics1.stringWidth(score_string))/2 , 380 / 2 + 30);

                g2d.setColor(Color.yellow);
                g2d.setFont(new Font("Serif", Font.BOLD, 30));
                FontMetrics metrics = getFontMetrics(g2d.getFont());
                String game_over = "Pac-Man is dead.";
                g2d.drawString(game_over, (380 - metrics.stringWidth(game_over)) / 2, 380 / 2);
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    /**
     * Plays the game by updating the game state, moving characters,
     * and checking for collisions.
     *
     * @param g2d the Graphics2D object used for painting
     */
    private void playGame(Graphics2D g2d) {
        if (dying) {
            death(g2d);
        } else {
            pacman.move();
            pacman.draw(g2d, this);
            moveGhosts(g2d);
            checkMaze();
        }

    }

    /**
     * Moves the ghosts and checks for collisions with the pacman.
     *
     * @param g2d the Graphics2D object used for painting
     */
    private void moveGhosts(Graphics2D g2d) {
        red.control(pacman);
        red.IsDodging();
        pink.control();
        yellow.control();
        red.draw(g2d, this);
        pink.draw(g2d, this);
        yellow.draw(g2d, this);
        if (pacman.x > (red.x - 12) && pacman.x < (red.x + 12)
                && pacman.y > (red.y - 12) && pacman.y < (red.y + 12)
                && inGame) {
            dying = true;
        }
        if (pacman.x > (pink.x - 12) && pacman.x < (pink.x + 12)
                && pacman.y > (pink.y - 12) && pacman.y < (pink.y + 12)
                && inGame) {
            dying = true;
        }
        if (pacman.x > (yellow.x - 12) && pacman.x < (yellow.x + 12)
                && pacman.y > (yellow.y - 12) && pacman.y < (yellow.y + 12)
                && inGame) {
            dying = true;
        }
    }

    /**
     * Checks if the pacman has collided with a maze square and updates the score.
     */
    private void checkMaze() {
        if (map.checkSquare(pacman))
            score += 1;
        if (map.completeCheck()) {
            map = new Map("lvl/level1.txt");
            dim = new Dimension(400, 400);
            pacman = new Pacman(300, 300);
            red = new RedGhost(dim.width / 2, dim.height / 2);
            pink = new PinkGhost(dim.width / 2, dim.height / 2);
            yellow = new YellowGhost(dim.width / 2, dim.height / 2);
        }
    }

    /**
     * Handles the death of the pacman.
     * Decreases the number of lives, checks if the game is over,
     * resets the game variables, and stops the dying animation.
     */
    private void death(Graphics2D g2d) {
        lives--;
        if (lives == 0) {
            inGame = false;
//            showScore = true;
//            gameOver = true;
            setButtonVisibility(true); // Add this line
        }
        else{
            restartLevel();
            dying = false;
        }
    }

    /**
     * Draws the score and remaining lives on the game panel.
     *
     * @param g the Graphics2D object used for painting
     */
    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        int SCREEN_SIZE = 15 * map.BLOCK_SIZE;
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    /**
     * The TAdapter class extends KeyAdapter and handles key events for the game.
     */
    class TAdapter extends KeyAdapter {
        /**
         * Handles the key pressed event.
         * Updates the direction of the pacman based on the key pressed.
         *
         * @param e the KeyEvent object
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            int temp = 0;
            if (inGame) {
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        pacman.pointLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        pacman.pointRight();
                        break;
                    case KeyEvent.VK_UP:
                        pacman.pointUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        pacman.pointDown();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Handles the action performed event for the game.
     * Repaints the game panel.
     *
     * @param e the ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
            repaint();
    }

}