/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.awt.Rectangle;

/**
 *
 * @author Malik
 */
public class Physiks {
    public static GameObject checkCollision(GameObject go1, GameObject go2){
        
        return checkCollision(new Rectangle((int)go1.getX(), (int)go1.getY(), (int)go1.getSX(), (int)go1.getSY()),go2); 
    }
    public static GameObject checkCollision(Rectangle r1, GameObject go2){
        
        Rectangle r2 = new Rectangle((int)go2.getX(), (int)go2.getY(), (int)go2.getSX(), (int)go2.getSY());
        
        boolean rea = r1.intersects(r2);
        
        if(rea)
            return go2;
        else
            return null;
    }
}
