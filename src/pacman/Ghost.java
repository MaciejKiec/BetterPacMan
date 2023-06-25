package pacman;
/**
 * The Ghost interface represents a ghost entity in the Pacman game.
 * Ghosts are controlled by implementing classes through the control() method.
 */
public interface Ghost {
    /**
     * Controls the ghost's behavior in response to the Pacman's actions.
     *
     * @param pac The Pacman instance that the ghost will respond to.
     */
    public void control(Pacman pac);
}
