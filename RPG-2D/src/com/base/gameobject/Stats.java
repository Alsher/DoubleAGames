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
    
    public static final int MAX_LEVEL = 99;
    public static final int MAX_XP = 999999;
    public static final double LEVEL_CONST = (double)MAX_XP/((double)MAX_LEVEL * (double)MAX_LEVEL);
    //public static final double LEVEL_KONST = 25.0 * Math.pow(3.0,3.0/2.0);
    
    private StatScale scale;
    private float xp;
    private int level;
    private boolean levelable;
    private int health;
    
    public Stats(float xp, boolean levelable){
        this.levelable = levelable;
        scale = new StatScale();
        //Warning
        scale.generateStatScale();
        //leave
        
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
    public int getLevel(){
        if(!levelable)
            return level;
        //calculat lvl from XP  lvl = sqrt(XP/a)
        
        return (int)Math.sqrt((double)xp/LEVEL_CONST) + 1;
        
        /* OLD LEVELING
        double  x = xp + 105.0;
        
        double a = Math.sqrt(243.0 * xp * xp + 4050.0 * xp + 17500.0);
        double c = (3.0 * xp +25.0)/25.0;
        double d = Math.sqrt(a / LEVEL_KONST + c);
        
        return (int)(d - 3/d) - 1;*/
    }
    
    
    public int getCurrentHealth(){
        int max = getMaxHealth();
        if(health > max) /** always the small ones **/
            health = max;
        
        return health;
    }
    public int getMaxHealth(){
        return (int)(getLevel() * scale.getScale(StatScale.VITALITY) * 10);
    }
    public float getSpeed(){
        return (float)(getLevel() * scale.getScale(StatScale.SPEED) * 10);
    }
    public float getStrength(){
        return (float)(getLevel() * scale.getScale(StatScale.STRENGHT) * 10);
    }
    public float getMagic(){
        return (float)(getLevel() * scale.getScale(StatScale.MAGIC) * 10);
    }
    public float getPhysicalDevence(){
        return (float)(getLevel() * scale.getScale(StatScale.PHYSICALDEVENCE) * 10);
    }
    public float getMagicDevence(){
        return (float)(getLevel() * scale.getScale(StatScale.MAGICDEVENCE) * 10);
    }
    public void addXP(float amt){
        xp += amt;
        if(xp > MAX_XP)
            xp = MAX_XP;
    }
    public void damage(int amt){
        health -= amt;
    }
}
