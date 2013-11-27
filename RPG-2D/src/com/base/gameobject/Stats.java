/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject;


/**
 *
 * @author Malik
 */
public class Stats {
    private float xp;
    public static final double LEVEL_KONST = 25 * Math.pow(3,3/2);
    private int health;
    
    public Stats(float xp){
        this.xp = xp;
        health = getMaxHealth();
    }
    public float getSpeed(){
        return 4f;
    }
    
    public int getCurrentHealth(){
        int max = getMaxHealth();
        if(health < max)
            health = max;
        
        return health;
    }
    public int getLevel(){
        double  x = xp + 105;
        
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
    
    public float getStrength(){
        return getLevel() * 4f;
    }
    
    public float getMagic(){
        return getLevel() * 4f;
    }
}
