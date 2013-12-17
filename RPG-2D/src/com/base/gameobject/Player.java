/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject;

import com.base.gameobject.items.Item;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Malik
 */
public class Player extends statObject{
    
    public static final float SIZE = 32;
    
    private Inventory inventory;

    public Player(float x, float y){
        init(x,y,0.1f,1f,0.3f,SIZE,SIZE,PLAYER_ID);
        stats = new Stats(0, true);
        inventory = new Inventory(20);
    }
    @Override
    public void update()
    {
        //System.out.println("States: SPEED: " + getSpeed() + " Level: " + getLevel() + " MAXHP: " + getMaxHealth() + " HP: " + getCurrentHealth() + " STRENGTH: " + getStrength() + " MAGIC: " + getMagic());
    }    
    public void getInput(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            move(0,1);
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            move(0,-1);
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            move(-1,0);
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            move(1,0);
    }
    
    private void move(float magX,float magY){
        x += getSpeed() * magX;
        y += getSpeed() * magY;
    }
    public void addItem(Item item){
        inventory.add(item);
    }
    
    public void addXP(float amt){
        stats.addXP(amt);
    }
}
