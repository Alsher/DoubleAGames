/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.engine.GameObject;
import com.doubleagamesdev.game.item.Cube;
import com.doubleagamesdev.game.item.Item;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Philipp Friese
 */
public class Player extends GameObject {
    
    public static final int SIZE = 32;
    public static final double LEVEL_CONST = 25 * Math.pow(3, (3.0/2.0));
    
    private int health;
    private float xp;
        
    public Player(float x, float y)
    {
        init(x, y, 0.1f, 1f, 0.25f, SIZE, SIZE, 0);
        health = 10;
        xp = 0;
    }
    
    @Override
    public void update()
    {
        //System.out.println("Stats: Speed: " + getSpeed() + " Level: " + getLevel() + " MaxHP: " + getMaxHealth() + " HP: " + getCurrentHealth() + " Strength: " + getStrength() + " magic: " + getMagic());;
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
        double x = xp + 105;
        
        
        double a = Math.sqrt(243 * (x * x) + 4050 * x + 17500);
        double c = (3 * x + 25)/25;
        double d = Math.cbrt(a / LEVEL_CONST + c);
        
        return (int)(d - 1.0/d * 3) - 1;
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
    
    public void addItem(Item item)
    {
        System.out.println("You picked up an item");
    
    }
}

