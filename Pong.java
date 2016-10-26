package mp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by mmcalvarez on 10/26/2016.
 */
public class Pong extends JPanel {
    Ball ball = new Ball(this);
    Racquet racquet = new Racquet(this);
    Racquet2 racquet2 = new Racquet2(this);
    int speed = 1;
    boolean gameStarted = false;

    protected int p1score = 0;
    protected int p2score = 0;

    public Pong() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racquet.keyReleased(e);

            }

            @Override
            public void keyPressed(KeyEvent e) {
                racquet.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){        //press ENTER key to start the game
                    gameStarted = true;
                }
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent m) {
            }

            @Override
            public void keyReleased(KeyEvent m) {
                racquet2.keyReleased(m);
            }

            @Override
            public void keyPressed(KeyEvent m) {
                racquet2.keyPressed(m);
                if(m.getKeyCode() == KeyEvent.VK_ENTER){        //not sure if I should also do the same here
                    gameStarted = true;
                }
            }
        });

        setFocusable(true);
    }

    private void move() {
        if(gameStarted){
            ball.move();
            racquet.move();  //I tried to make racquets into one class but I'm confused on how to manage the different keys used for the racquets
            racquet2.move();
        }
    }

    @Override
    public void paint(Graphics g) {


        super.paint(g);  //this cleans the screen, making us see a "ball" moving on the screen. If we comment this line, we are gonna see a line drawn onto the screen.
        setBackground(Color.black);


        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); //makes the borders of the ball smoother.
        ball.paint(g2d);
        racquet.paint(g2d);
        racquet2.paint(g2d);


        g2d.setColor(Color.LIGHT_GRAY);    //score board
        g2d.setFont(new Font("Verdana", Font.PLAIN, 20));
        g2d.drawString(String.valueOf(racquet.getScore()), 10, 300);
        g2d.drawString(String.valueOf(racquet2.getScore()), 10, 70);

        if(!gameStarted){       //prompts a dialogue that requests the user to press ENTER key firrst to start the game
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawString("Pong", 110, 160);
            g2d.drawString("Press Enter...", 70, 200);
        }
    }

    public void nextRound(){        //restarts the game(sets the ball back to its original position) without changing the scores back to zero
        Sound.NEXTROUND.play();
        JOptionPane.showMessageDialog(this, "Next Round!");
        ball.x = 0;
        ball.y = 150;
        ball.xa = 1;
        ball.ya = 1;
        speed = 1;
    }

    public void gameOver(){     //gives option to users to start a new game or exit from the program
        int choice;
        if(p1score > p2score){
            choice = JOptionPane.showConfirmDialog(null, "Player 1 won!" + "\nRestart?", "Game Over", JOptionPane.YES_NO_OPTION);
        }else{
            choice = JOptionPane.showConfirmDialog(null, "Player 2 won!" + "\nRestart?", "Game Over", JOptionPane.YES_NO_OPTION);
        }
        if(choice == JOptionPane.YES_OPTION){
            try {
                main(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.exit(ABORT);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Pong");
        Pong game = new Pong();
        frame.add(game);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //allows us to close the created window.

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);   //allows the processor to sleep for how many milliseconds allowing other threads to execute
        }
    }
}
