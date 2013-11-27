/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject.items;

import com.base.engine.GameObject;
import com.base.engine.Physiks;
import com.base.engine.Sprite;
import com.base.gameobject.Player;

/**
 *
 * @author Malik
 */
public class Item extends GameObject{
    protected String name;
    
    protected Player player;
    
    public Item(Player play){
        this.player = play;
    }
    
    public void pickUp(){
        System.out.println("You just picked up an " + name + "!");
        player.addItem(this);
        remove = true;
    }
    
    @Override
    public void update(){
        if(Physiks.checkCollision(this, player))
            pickUp();
    }
    protected void init(float x,float y,float r,float g,float b,float sx,float sy, String name){
        this.x = x;
        this.y = y;
        this.type = 1;
        this.spr = new Sprite(r,g,b,sx,sy);
        this.name = name;
    }
}
