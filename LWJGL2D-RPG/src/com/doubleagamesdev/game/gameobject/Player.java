/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.engine.GameObject;
import com.doubleagamesdev.engine.Sprite;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Philipp Friese
 */
public class Player extends GameObject {
    
    public static final int SIZE = 32;
    
    private int health;
    private float xp;
        
    public Player(float x, float y)
    {
        init(x, y, 0.1f, 1f, 0.25f, SIZE, SIZE);
        health = 10;
        xp = 150;
    }
    
    @Override
    public void update()
    {
        System.out.println("Stats: Speed: " + getSpeed() + " Level: " + getLevel() + " MaxHP: " + getMaxHealth() + " HP: " + getCurrentHealth() + " Strength: " + getStrength() + " magic: " + getMagic());;
    }
    
    public void getInput()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            move(0, 1);
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            move(0, -1);
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            move(-1, 0);
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            move(1, 0);
    }
    
    private void move(float magX, float magY)
    {
        x += getSpeed() * magX;
        y += getSpeed() * magY;
    }
    
    public float getSpeed()
    {
        return 4f;
    }
    
    public int getLevel()
    {
        return (int)(xp / 50) + 1;
    }
    
    public int getMaxHealth()
    {
        return getLevel() * 10;
    }
    
    public int getCurrentHealth()
    {
        int max = getMaxHealth();
        if(health > max)
            health = max;
        
        return health;
    }
    
    public float getStrength()
    {
        return getLevel() * 4f;
    }
    
    public float getMagic()
    {
        return getLevel() * 4f;
    }
    
    public void addXp(float amt)
    {
        xp += amt;
    }
}

