/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.engine.GameObject;

/**
 *
 * @author Philipp Friese
 */
public class StatObject extends GameObject {
    
    protected Stats stats;
    
    public void damage(int amt)
    {
        stats.damage(amt);
    }
    
    
    public float getSpeed()
    {
        return stats.getSpeed();
    }
    
    public int getLevel()
    {
        return stats.getLevel();
    }
    
    public int getMaxHealth()
    {
        return stats.getMaxHealth();
    }
    
    public int getCurrentHealth()
    {
        return stats.getCurrentHealth();
    }
    
    public float getStrength()
    {
        return stats.getStrength();
    }
    
    public float getMagic()
    {
        return stats.getMagic();
    }
}

