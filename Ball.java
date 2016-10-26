package mp;

import java.awt.*;

/**
 * Created by mmcalvarez on 10/26/2016.
 */
public class Ball {
    private static final int DIAMETER = 25;
    int x = 0;
    int y = 150;
    int xa = 1;
    int ya = 1;
    boolean flag = true;
    private Pong game;

    public Ball(Pong game) {
        this.game= game;
    }

    void move() {
        boolean changeDirection = true;
        if (x + xa < 0)
            xa = game.speed;
        else if (x + xa > game.getWidth() - DIAMETER)
            xa = -game.speed;
        else if (y + ya + DIAMETER < 0)
            ya = game.speed;
        else if(y + ya < 0){
            game.p1score++;
            game.nextRound();
        }
        else if (y + ya + 10 > game.getHeight() - game.racquet2.getPosition()){
            game.p2score++;
            game.nextRound();  //restarts the speed of the ball. basically the game but not the scores
        }
        else if(game.p1score == 3 || game.p2score == 3){
            Sound.GAMEOVER.play();
            game.gameOver();
        }
        else if (collision()){
            if(flag == false){
                ya = -game.speed;
                y = game.racquet.getTopY() - DIAMETER;
                game.speed++;
            }else{
                ya = +game.speed;
                y = game.racquet2.getTopY();
                game.speed++;
            }
        } else
            changeDirection = false;

        if (changeDirection)
            Sound.BALL.play();

        x = x + xa;
        y = y + ya;
    }

    private boolean collision() {
        if(flag){
            flag = false;
            return game.racquet.getBounds().intersects(getBounds());
        }else{
            flag = true;
            return game.racquet2.getBounds().intersects(getBounds());
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);

    }
}
