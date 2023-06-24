package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {
    private Dimension dim;
    private final Font smallFont = new Font("Arial", Font.BOLD,16);
    private boolean inGame = false;
    private boolean dying = false;

    private int lives, score;


    private Image heart;




    private Pacman pacman;
    private Map map;
    private RedGhost red;

    private BlueGhost blue;
    private PinkGhost pink;
    private YellowGhost yellow;

    Timer timer;


    public Game() {
        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
        timer = new Timer(100, this);
        timer.start();
    }

    private void initGame() {
        inGame = true;
    }

    private void loadImages() {
        heart = new ImageIcon("images/live.gif").getImage();
    }

    private void initVariables() {
        map = new Map("lvl/level1.txt");
        dim = new Dimension(map.width*map.BLOCK_SIZE, map.height*map.BLOCK_SIZE);
        pacman = new Pacman(dim.width/2,dim.height/2);
        red = new RedGhost(dim.width/2,dim.height/2);
        blue = new BlueGhost(dim.width/2,dim.height/2);
        pink = new PinkGhost(dim.width/2,dim.height/2);
        yellow = new YellowGhost(dim.width/2,dim.height/2);
        score = 0;
        lives = 3;
    }

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

    private void moveGhosts(Graphics2D g2d) {
        red.control();
        blue.control();
        pink.control();
        yellow.control();
        red.draw(g2d, this);
        blue.draw(g2d, this);
        pink.draw(g2d, this);
        yellow.draw(g2d, this);
    }
    private void checkMaze(){
        if (map.checkSquare(pacman))
            score +=1;
    }
    private void death() {
    }

    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        int SCREEN_SIZE = map.height * map.BLOCK_SIZE;
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 27);

        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, i * 40 + 10, SCREEN_SIZE + 1, this);
        }
    }


    class TAdapter extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
