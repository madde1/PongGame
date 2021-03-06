package pong;

import java.awt.*;

public class Paddle {
    /**---- Variables -----*/
    public int paddleNumber;

    public int x, y, width = 20, height = 200;

    public int score;

    /** Paddle class constructor */
    public Paddle(Pong pong, int paddleNumber){
        this.paddleNumber = paddleNumber;

        if(paddleNumber == 1){
            this.x = 0;
        }
        if(paddleNumber == 2){
            this.x = pong.width - width;

        }
        this.y = pong.height/2 - this.height/2;
    }

    /**Sets the color and size of the paddles*/
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x,y,width,height);
    }

    /**Makes the paddle move up and down and sets the speed*/
    public void move(boolean up) {
        int speed = 15;
        if(up){
            if(y + height - speed > 0 ){
                y -= speed;
            }else {
                y += speed;
            }
        }else {
            if(y + speed < Pong.pong.height ){
                y += speed;
            }else {
                y = Pong.pong.height - height ;
            }

        }
    }
}
