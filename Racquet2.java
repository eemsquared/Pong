package mp;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by mmcalvarez on 10/26/2016.
 */
public class Racquet2 {
    private static final int Y = 20;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 8;
    int x = 100;
    int xa = 0;
    private Pong game;

    public Racquet2(Pong game) {
        this.game= game;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH)
            x = x + xa;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A)
            xa = -game.speed;
        if (e.getKeyCode() == KeyEvent.VK_D)
            xa = game.speed;


    }

    public Rectangle getBounds() {  //indicates the position of the racquet which will then bu used by the class mp.Ball to detect collision
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    public int getTopY() {      //opposite for geyTopY of class Racquet
        return Y + HEIGHT;
    }

    public int getPosition(){
        return Y;
    }

    public int getScore(){
        return game.p2score;
    }
}
