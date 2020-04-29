package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Pong implements ActionListener, KeyListener {
  /**------- Variables --------- */
    public static Pong pong;
    public int width = 700, height= 700;

    public Renderer renderer;

    public Paddle player1;
    public Paddle player2;

    public Ball ball;

    public boolean bot = false;
    public boolean w,s,up,down;

    public int gameStatus = 0; // 0 = stopped, 1 = Paused, 2 = Playing

    /**---- Class constructor -----*/
    public Pong(){
        Timer timer = new Timer(20, this);
        JFrame jFrame = new JFrame("Pong"); //window title

        renderer = new Renderer();

        jFrame.setSize(width + 15, height + 40); //set window size
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.add(renderer);
        jFrame.addKeyListener(this);

        timer.start();
    }

    /** Start method, sets the gamesStatus to playing, creates new players and new ball*/
    public void start(){
        gameStatus = 2;
        player1 = new Paddle(this,1);
        player2 = new Paddle(this,2);
        ball = new Ball(this);
    }

    /**Update method that sets */
    public void update(){
        if(w){
            player1.move(true);
        }
        if(s){
            player1.move(false);
        }
        if(up){
            player2.move(true);
        }
        if(down){
            player2.move(false);
        }
        ball.update(player1, player2);
    }

    /**Sets the game field */
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (gameStatus == 0){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("Pong" , width/2 - 70,height / 2 - 120);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 20));
            g.drawString("Press Shit to play with bot" , width/2 - 120,height / 2 - 50);
            g.drawString("Press Space to play" , width/2 - 120,height / 2 - 80);
        }
        if (gameStatus == 2 || gameStatus == 1) {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(5f));
            g.drawLine(width / 2, 0, width / 2, height);


            g.setFont(new Font("Arial", 1, 20));
            g.drawString("Player 1: " + String.valueOf(player1.score), width/2 - 340,50);
            g.setFont(new Font("Arial", 1, 20));
            g.drawString("Player 2: " + String.valueOf(player2.score), width/2 + 230,50);

            player1.render(g);
            player2.render(g);
            ball.render(g);
        }
        if (gameStatus == 1){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("Paused" , width/2 - 80,50);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameStatus == 2) {
            update();
        }
        renderer.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**Checks if the key is pressed and changes gamesStatus if space is pressed*/
    @Override
    public void keyPressed(KeyEvent e) {
        int id= e.getKeyCode();

        if(id== KeyEvent.VK_W){
            w = true;
        }
        if(id == KeyEvent.VK_S){
            s = true;
        }
        if(id == KeyEvent.VK_UP){
            up = true;
        }
        if(id == KeyEvent.VK_DOWN){
            down = true;
        }
        if(id == KeyEvent.VK_SHIFT && gameStatus == 0){
            bot = true;
            start();
        }
        if(id == KeyEvent.VK_SPACE){
            if(gameStatus == 0){
                start();
                bot = false;
            }
            else if(gameStatus == 1){
                gameStatus = 2;
            }
            else if(gameStatus == 2){
                gameStatus = 1;
            }
        }
    }
    /**Checks if the key is released*/
    @Override
    public void keyReleased(KeyEvent e) {
        int id= e.getKeyCode();

        if(id== KeyEvent.VK_W){
            w = false;
        }
        if(id == KeyEvent.VK_S){
            s = false;
        }
        if(id == KeyEvent.VK_UP){
            up = false;
        }
        if(id == KeyEvent.VK_DOWN){
            down = false;
        }
    }
    /**Main method*/
    public static void main(String[] args){
        pong = new Pong();
    }
}
