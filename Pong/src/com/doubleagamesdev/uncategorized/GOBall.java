/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.uncategorized;

/**
 *
 * @author Phil
 */
public class GOBall extends GameObject{
    
    public static final int SIZE = 16;
    public static final float MAX_SPEEDX = 6f;
    public static final float MAX_SPEEDY = 10f;
    public static final float DAMPING = 0.05f;
    
    public float velX, velY;
    public float startX, startY;
    
    
    public GOBall(float x, float y)
    {
        this.x = x;
        this.y = y;
        this.sx = SIZE;
        this.sy = SIZE;
        
        startX = x;
        startY = y;
        
        velX = -MAX_SPEEDX;
        velY = 0;
    }
    
    @Override
    public void update()
    {
        x += velX;
        y += velY;
    }
    
    public void resetPosition()
    {
        x = startX;
        y = startY;
        
        velY = 0;
        velX *= -1; //let ball go to winning player
    }
    
    public void reverseX(float center)
    {
        velX *= -1;
        velY += (getCenterY() - center) * DAMPING;
        
        if(velY > MAX_SPEEDY)
            velY = MAX_SPEEDY;
        if(velY < -MAX_SPEEDY)
            velY = -MAX_SPEEDY;
    }
    
    public void reverseY()
    {
        velY *= -1;
    }
}
