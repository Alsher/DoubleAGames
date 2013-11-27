/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject;

import com.base.engine.GameObject;
import com.base.gameobject.items.Item;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Malik
 */
public class Player extends GameObject{
    
    public static final float SIZE = 32;
    private Stats stats;

    public Player(float x, float y){
        init(x,y,0.1f,1f,0.3f,SIZE,SIZE,0);
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
        x += getSpeed() + magX;
        y += getSpeed() + magY;
    }
    public void addItem(Item item){
        System.out.println("we just picked up an item");
    }
    public float getSpeed(){
        return stats.getSpeed();
    }
    
    public int getLevel(){
        return stats.getLevel();
    }
    
    public int getMaxHealth(){
        return stats.getMaxHealth();
    }
    
    public int getCurrentHealth(){
        return stats.getCurrentHealth();
    }
    
    public float getStrength(){
        return stats.getStrength();
    }
    
    public float getMagic(){
        return stats.getMagic();
    }
    public void addXP(float amt){
        stats.addXP(amt);
    }
}
