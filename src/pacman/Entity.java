package pacman;

public class Entity {
    protected int x, y;
    protected int direction;
    protected int SPEED;
    protected final int[] VALID_SPEED = {1,2,3,6,8};



    protected void pointUp() {}
    protected void pointRight() {}
    protected void pointLeft() {}
    protected void pointDown() {}
    protected void move() {}
}
