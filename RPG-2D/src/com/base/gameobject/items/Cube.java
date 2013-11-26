/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject.items;

import com.base.engine.Physiks;
import com.base.gameobject.Player;

/**
 *
 * @author Malik
 */
public class Cube extends Item{
    public static final float SIZE = 32;
    
    private Player player;

    public Cube(float x, float y, Player play){
        init(x,y,1f,0.5f,0.0f,SIZE,SIZE,"The Cube");
        this.player = play;
    }
    
    @Override
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
}
