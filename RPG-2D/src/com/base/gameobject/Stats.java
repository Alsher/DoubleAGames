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
    public static final double LEVEL_KONST = 25.0 * Math.pow(3.0,3.0/2.0);
    
    private float xp;
    private int level;
    private boolean levelable;
    private int health;
    
    public Stats(float xp, boolean levelable){
        this.levelable = levelable;
        
        if(levelable){
            this.xp = xp;
            this.level = 1;
            }
        else{
            this.xp = -1;
            this.level = (int)xp;
        }
        health = getMaxHealth();
    }
    public float getSpeed(){
        return 4f;
    }
    
    public int getCurrentHealth(){
        int max = getMaxHealth();
        if(health > max) /** always the small ones **/
            health = max;
        
        return health;
    }
    public int getLevel(){
        if(!levelable)
            return level;
        
        double  x = xp + 105.0;
        
        double a = Math.sqrt(243.0 * xp * xp + 4050.0 * xp + 17500.0);
        double c = (3.0 * xp +25.0)/25.0;
        double d = Math.sqrt(a / LEVEL_KONST + c);
        
        return (int)(d-1 / d/3) - 1;
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
    public void addXP(float amt){
        xp += amt;
    }
    public void damage(int amt){
        health -= amt;
    }
}
