/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.item;

import com.doubleagamesdev.engine.GameObject;
import com.doubleagamesdev.engine.Physics;
import com.doubleagamesdev.engine.Sprite;
import com.doubleagamesdev.game.gameobject.Player;
/**
 *
 * @author Philipp Friese
 */
public class Item extends GameObject{
    
    protected String name;
    protected Player player;
    
    public Item(Player play)
    {
        this.player = play;
    }
    
    public void pickUp()
    {
        System.out.println("You just picked up an item  " + name);
        player.addItem(this);
        remove();
    }
    
    
    @Override
    public void update()
    {
        if(Physics.checkCollision(this, player) != null)
        {
            System.out.println("Now");
            pickUp();
        }
    }
    
    public String getName()
    {
        return name;
    }
    
    protected void init(float x, float y, float r, float g, float b, float sx, float sy, String name)
    {
        this.x = x;
        this.y = y;
        this.type = ITEM_ID;
        this.spr = new Sprite(r, g, b, sx, sy);    
        this.name = name;
    }
    
 
}

