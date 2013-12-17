/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject;

import com.base.engine.GameObject;
import com.base.game.Util;

/**
 *
 * @author Malik
 */
public class Enermy extends GameObject {
    
    
    private Stats stats;
    private GameObject target;
    
    public Enermy(int level){
        stats = new Stats(level, false);
        target = null;
    }
    @Override
    public void update(){
        if(target == null)
            Look();
        
        else
        {
            /** needs to be implemented correctly **/
            //if(Util.LineOfSight(this, target) && Util.Dist(x, y, getTarget().getX(), getTarget().getY()) <= 128)
            //  Attack();
            //else
            
            
            Chase();
            
        }  
        if(stats.getCurrentHealth() <= 0)
            Die();
    }
            
    protected void Attack(){
        
    }
    protected void Look(){
        
    }
    protected void Chase(){
        
    }
    protected void Die(){
        
    }
    public void setTarget(GameObject go){
        target = go;
    }
    public GameObject getTarget(){
        return target;
    }
    public Stats getStats(){
        return stats;
    }
}
