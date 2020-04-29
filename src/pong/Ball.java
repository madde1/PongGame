package pong;
import java.awt.*;
import java.util.Random;

public class Ball {
    /**----- Variables -----*/
    public int x, y, width = 25, height = 25;
    public int motionX, motionY;
    public Random random;
    private  Pong pong;
    public int amoutOfHits;

    /**Class constructor*/
    public Ball(Pong pong){
        this.pong = pong;
        random = new Random();
        span();

    }

    /**Updates if the ball hits, scores and sets the speed on the ball*/
    public void update(Paddle paddle1, Paddle paddle2){
        int speed = 3;
        this.x += motionX * speed;
        this.y += motionY * speed;

        if(this.y + height - motionY > pong.height || this.y + motionY < 0 ){
            if(this.motionY < 0) {
                this.y = 0;
                this.motionY = random.nextInt(4);
                if(motionY == 0 ){
                    motionY = 1;
                }
            }else {
                this.motionY = -random.nextInt(4);
                this.y = pong.height- height;
                if(motionY == 0 ){
                    motionY = -1;
                }
            }
        }
        //Checks if the ball hit the paddle one
        if(checkCollision(paddle1) == 1 ){
            this.motionX = 1 + (amoutOfHits / 5);
            this.motionY = -2 + random.nextInt(4);

            if(motionY == 0 ){
                motionY = 1;
            }
            amoutOfHits ++;
        }

        //Checks if the ball hit the paddle two
        if(checkCollision(paddle2) == 1 ){
            this.motionX = -1 - (amoutOfHits / 5);
            this.motionY = -2 + random.nextInt(4);
            if(motionY == 0 ){
                motionY =1;
            }
            amoutOfHits ++;
        }

        //Checks if paddle one missed and then gives paddle two score
        if(checkCollision(paddle1) == 2){
            paddle2.score++;
            span(); //re spans the ball after score
        }

        //Checks if paddle two missed and then gives paddle one score
        if(checkCollision(paddle2) == 2){
            paddle1.score++;
            span();  //re spans the ball after score
        }
    }

    /** Returns if its a hit, score or nothing. */
    public int checkCollision(Paddle paddle){
        if(this.x < paddle.x + paddle.width && this.x + width  > paddle.x && this.y < paddle.y + paddle.height && this.y + height > paddle.y){
            return 1; //Hit
        }else if((paddle.x > x  && paddle.paddleNumber == 1 ) || (paddle.x < x - width  && paddle.paddleNumber == 2)){
            return 2; //score
        }
        return 0; //nothing
    }

    /**Spans the ball in the center of the game field*/
    public void span(){
        this.amoutOfHits = 0;
        this.x = pong.width/2 - this.width/2;
        this. y = pong.height/2 - this.height/2;

        this.motionY = -2 + random.nextInt(4);
        if(motionY == 0 ){
            motionY =1;
        }
        if(random.nextBoolean()){
            motionX = 1;
        }else {
            motionX = -1;
        }
    }

    /**Sets the color and size of the ball*/
    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x,y,width,height);
    }

}

