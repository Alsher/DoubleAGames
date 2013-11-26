/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject.items;

import com.base.engine.GameObject;
import com.base.engine.Sprite;

/**
 *
 * @author Malik
 */
public class Item extends GameObject{
    protected String name;
    
    public void pickUp(){
        
    }
    protected void init(float x,float y,float r,float g,float b,float sx,float sy, String name){
        this.x = x;
        this.y = y;
        this.type = 1;
        this.spr = new Sprite(r,g,b,sx,sy);
        this.name = name;
    }
}
