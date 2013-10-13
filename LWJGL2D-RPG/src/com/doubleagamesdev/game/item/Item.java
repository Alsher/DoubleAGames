/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.item;

import com.doubleagamesdev.engine.GameObject;
import com.doubleagamesdev.engine.Sprite;

/**
 *
 * @author Philipp Friese
 */
public class Item extends GameObject{
    
    protected String name;
    
    public void pickUp()
    {
        
    }
    
    protected void init(float x, float y, float r, float g, float b, float sx, float sy, String name)
    {
        this.x = x;
        this.y = y;
        this.type = 1;
        this.spr = new Sprite(r, g, b, sx, sy);    
        this.name = name;
    }
}

