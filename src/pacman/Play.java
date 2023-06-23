package pacman;
import javax.swing.JFrame;

public class Play extends JFrame {
    public Play(){
        this.add(new Game());

    }

    public static void main(String[] args) {
        Play pac = new Play();
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(573 ,595+16);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
    }
}



