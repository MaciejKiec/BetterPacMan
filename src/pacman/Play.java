package pacman;
import javax.swing.JFrame;
/**
 * The {@code Play} class represents the main entry point for the Pacman game.
 * It extends the {@link javax.swing.JFrame} class to provide a graphical user interface.
 */
public class Play extends JFrame {
    /**
     * Constructs a new instance of the {@code Play} class.
     * It initializes the game by adding a new {@link Game} instance to the frame.
     */
    public Play(){
        this.add(new Game());

    }
    /**
     * The main method that launches the Pacman game.
     * It creates an instance of the {@code Play} class, sets up the frame properties,
     * and makes the frame visible to start the game.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Play pac = new Play();
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(380 ,420);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
    }
}



