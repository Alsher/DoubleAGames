/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.engine;

/**
 *
 * @author Philipp Friese
 */

import static org.lwjgl.opengl.GL11.*;

public abstract class GameObject {
    
   protected float x, y, sx, sy;
   protected Sprite spr;
   
   public void update()
   {
       
   }
   
   public void render()
   {
       glPushMatrix();
       {
            glTranslatef(x, y, 0);
            spr.render(); 
       }
       glPopMatrix(); //return to primary matrix
   }

    public float getX() {
       return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getSX()
    {
        return spr.getSX();
    }
    
    public float getSY()
    {
        return spr.getSY();
    }

 
    protected void init(float x, float y, float r, float g, float b, float sx, float sy)
    {
        this.x = x;
        this.y = y;
        this.spr = new Sprite(r, g, b, sx, sy);
        
    }
}
