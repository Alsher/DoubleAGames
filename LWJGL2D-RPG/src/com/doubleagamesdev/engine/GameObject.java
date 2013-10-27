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
    
    public static final int PLAYER_ID = 2;
    public static final int ITEM_ID = 1;
    
   protected float x, y, sx, sy;
   protected Sprite spr;
   protected int type;
   
   protected boolean[] flags = new boolean[1];
   
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

    public int getType()
    {
        return type;
    }
    
    public boolean getRemove()
    {
        return flags[0];
    }
    
    public void remove()
    {
        flags[0] = true;
    }
 
    protected void init(float x, float y, float r, float g, float b, float sx, float sy, int type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.spr = new Sprite(r, g, b, sx, sy);
        
    }
}
