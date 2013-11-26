/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject;

import com.base.engine.GameObject;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Malik
 */
public class Player extends GameObject{
    
    public static final float SIZE = 32;
    public static final double LEVEL_KONST = 25 * Math.pow(3,3/2);

    private int health;
    private float xp;
    
    public Player(float x, float y){
        init(x,y,0.1f,1f,0.3f,SIZE,SIZE,0);
        health = 10;
        xp = 0;
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
    
    public float getSpeed(){
        return 4f;
    }
    
    public int getLevel(){
        
        double a = Math.sqrt(243 * xp * xp + 4050 * xp + 17500);
        double c = (3 * xp +25)/25;
        double d = Math.sqrt(a / LEVEL_KONST + c);
        
        return (int)(d * 2 / 3);
    }
    public void addXP(float amt){
        xp += amt;
    }
    public int getMaxHealth(){
        return (getLevel() * 10);
    }
    
    public int getCurrentHealth(){
        int max = getMaxHealth();
        if(health < max)
            health = max;
        
        return health;
    }
    
    public float getStrength(){
        return getLevel() * 4f;
    }
    
    public float getMagic(){
        return getLevel() * 4f;
    }
}
