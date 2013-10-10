/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagames.uncategorized;

/**
 *
 * @author Phil
 */
public abstract class GameObject 
{
    protected float x, y;   //x and y
    protected float sx, sy; //sizex and sizey
    
    abstract void update();
    public void render()
    {
        Draw.drawRect(x, y, sx, sy);
    }
    
    
    /** getter for floats **/
    public float getX()
    {
      return x;  
    }
    
    public float getY()
    {
      return y;  
    }
    
    public float getSX()
    {
      return sx;  
    }
    
    public float getSY()
    {
      return sy;  
    }
    
    public float getCenterY()
    {
        return y + (sy/2); //create center of GameObject
    }
}
