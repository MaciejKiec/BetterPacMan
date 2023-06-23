package pacman;
import javax.swing.JFrame;

public class Pacman extends JFrame {
    public Pacman(){
        this.add(new Game());

    }

    public static void main(String[] args) {
        Pacman pac = new Pacman();
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(573 ,595);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
    }
}



