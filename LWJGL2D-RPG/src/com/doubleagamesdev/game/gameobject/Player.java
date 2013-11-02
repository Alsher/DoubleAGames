/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.game.Time;
import com.doubleagamesdev.game.item.Item;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Philipp Friese
 */
public class Player extends StatObject {
    
    public static final int SIZE = 32;
    private Inventory inventory;
        
    public Player(float x, float y)
    {
        init(x, y, 0.1f, 1f, 0.25f, SIZE, SIZE, PLAYER_ID);
        stats = new Stats(0, true);  //starting experience, levelable
        inventory = new Inventory(20);
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
        x += getSpeed() * magX * Time.getDelta();
        y += getSpeed() * magY * Time.getDelta();
    }
    
    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public void addXp(float amt)
    {
        stats.addXp(amt);
    }
    

}

