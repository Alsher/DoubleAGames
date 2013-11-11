/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.uncategorized;

/**
 *
 * @author Phil
 */
public class GOEnemy extends GameObject{
    
    public static final int SIZEX = 16;
    public static final int SIZEY = SIZEX * 7;
    public static final float MAX_SPEEDY = 4f;
    public static final float DAMPING = 0.5f;
    
     GOBall ball;
     GOWall wall1;
     GOWall wall2;
             
    public GOEnemy(float x, float y, GOWall wall1, GOWall wall2, GOBall ball)
    {
        this.x = x;
        this.y = y;
        this.sx = SIZEX;
        this.sy = SIZEY;
        this.ball = ball;
        this.wall1 = wall1;
        this.wall2 = wall2;
    }
    
    @Override
    public void update()
    {
        if(Physics.checkCollisions(this, ball))
            ball.reverseX(getCenterY());
        
        float speed = (ball.getCenterY() - getCenterY()) * DAMPING ;
        
        if(speed > MAX_SPEEDY)
            speed = MAX_SPEEDY;
        if(speed < -MAX_SPEEDY)
            speed = -MAX_SPEEDY;
        
        
        y += speed;

        if(Physics.checkCollisions(this, wall1))
            this.y = 16;
        if(Physics.checkCollisions(this, wall2))
            this.y = 472; 
    }
}
