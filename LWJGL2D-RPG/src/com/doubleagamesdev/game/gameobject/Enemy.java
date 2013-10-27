/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.engine.GameObject;
import com.doubleagamesdev.game.Util;

/**
 *
 * @author Philipp Friese
 */
public class Enemy extends GameObject {
    

    private Stats stats;
    private GameObject target;
    
    public Enemy(int level)
    {
        stats = new Stats(level, false); //starting experience, levelable
        target = null;
    }
    
    @Override
    public void update()
    {
        if(target == null)
            Look();
        else 
        {
            Chase();
        
            if(Util.LineOfSight(this, target))
                Attack();
        }
        if(stats.getCurrentHealth() <= 0)
            Die();
    }
    
    
    protected void Attack()
    {
        
    }
    
    protected void Look()
    {
        
    }
    
    protected void Chase()
    {
        
    }
    
    protected void Die()
    {
        
    }
    
    
    
    
    public void setTarget(GameObject go)
    {
        target = go;
    }
    
    public GameObject getTarget()
    {
        return target;
    }
    
    public Stats getStats()
    {
        return stats;
    }
  

}

