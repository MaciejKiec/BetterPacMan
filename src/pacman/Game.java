package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * The Game class represents the main game panel for the Pacman game.
 * It extends JPanel and implements ActionListener.
 */
public class Game extends JPanel implements ActionListener {
    private Dimension dim;
    private final Font smallFont = new Font("Arial", Font.BOLD,16);
    private boolean inGame = false;
    private boolean dying = false;

    private int lives = 3, score;

    private ThreadPool threadPool;


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
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
        timer = new Timer(100, this);
        timer.start();
    }
    /**
     * Initializes the game by setting the inGame flag to true.
     */
    private void initGame() {
        inGame = true;
    }
    /**
     * Loads the required images for the game.
     */
    private void loadImages() {
        heart = new ImageIcon("images/heart.png").getImage();
    }
    /**
     * Initializes the variables required for the game.
     * Creates the map, sets the dimensions, initializes the characters (pacman, ghosts),
     * and sets the initial score and lives.
     */
    private void initVariables() {
        threadPool = new ThreadPool(4);
        map = new Map("lvl/level1.txt");
        dim = new Dimension(400, 400);
        pacman = new Pacman(300,300);
        red = new RedGhost(dim.width/2,dim.height/2);
        pink = new PinkGhost(dim.width/2,dim.height/2);
        yellow = new YellowGhost(dim.width/2,dim.height/2);
        threadPool.runTask(pacman);
        threadPool.runTask(yellow);
        score = 0;
        lives = 3;
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
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, dim.width+5, dim.height+40);

        map.draw(g2d, map.BLOCK_SIZE);
        drawScore(g2d);

        if (inGame) {
            playGame(g2d);
        }
//        else {
//            showIntroScreen(g2d);
//        }

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
            death();
        }
        else {
            pacman.move();
            pacman.draw(g2d,this);
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
    private void checkMaze(){
        if (map.checkSquare(pacman))
            score +=1;
    }
    /**
     * Handles the death of the pacman.
     * Decreases the number of lives, checks if the game is over,
     * resets the game variables, and stops the dying animation.
     */
    private void death() {
        lives--;
        if(lives == 0) {
            inGame = false;
        }
        map = new Map("lvl/level1.txt");
        dim = new Dimension(400, 400);
        pacman = new Pacman(300,300);
        red = new RedGhost(dim.width/2,dim.height/2);
        pink = new PinkGhost(dim.width/2,dim.height/2);
        yellow = new YellowGhost(dim.width/2,dim.height/2);
        dying = false;
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