/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.uncategorized;

import static com.doubleagamesdev.uncategorized.GOPlayer.SIZEX;
import static com.doubleagamesdev.uncategorized.GOPlayer.SIZEY;
import static com.doubleagamesdev.uncategorized.GOPlayer.SPEED;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Malik
 */
public class GOPlayer2 extends GameObject
{
    public static final int SIZEX = 16;
    public static final int SIZEY = SIZEX * 7;
    public static final float SPEED = 4f;
    
    private GOBall ball;
    private GOWall wall1;
    private GOWall wall2;
    public GOPlayer2(float x, float y, GOWall wall, GOWall wall2, GOBall ball)
    {
        this.x = x;
        this.y = y;
        this.sx = SIZEX;
        this.sy = SIZEY;
        this.ball = ball;
        this.wall1 = wall;
        this.wall2 = wall2;
    }
    
    @Override
    public void update()
    {
        if(Physics.checkCollisions(this, ball))
            ball.reverseX(getCenterY());
        if(Physics.checkCollisions(this, wall1))
            this.y = 16;
        if(Physics.checkCollisions(this, wall2))
            this.y = 472;   
    }
    
    public void move(float mag)
    {
        y += SPEED * mag;
        
    }
}