/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject;

import com.base.engine.GameObject;
import com.base.game.Delay;
import com.base.game.Util;

/**
 *
 * @author Malik
 */
public class Enermy extends statObject {
    
    
    private statObject target;
    private float attackRange;
    private int attackDamage;
    private Delay attackDelay;
    
    public Enermy(int level){
        stats = new Stats(level, false);
        target = null;
        attackDelay = new Delay(0);
        attackRange = 48f;
        attackDamage = 1;
        attackDelay.end();
    }
    @Override
    public void update(){
        if(target == null)
            Look();
        else
        {   
            /** needs to be implemented correctly **/
            if(Util.LineOfSight(this, target) && Util.dist(x, y, getTarget().getX(),getTarget().getY()) <= attackRange){
                if(attackDelay.over())
                Attack();
            }
            else
                Chase();
        }  
        if(stats.getCurrentHealth() <= 0)
            Death();
    }
            
    protected void Attack(){
        
    }
    protected void Look(){
        
    }
    protected void Chase(){
        
    }
    protected void Death(){
        
    }
    public void setTarget(statObject go){
        target = go;
    }
    public GameObject getTarget(){
        return target;
    }
    public Stats getStats(){
        return stats;
    }
    public int getAttackDamage(){
        return attackDamage;
    }
    public void setAttackRange(int range){
        attackRange = range;
    }
    public void setAttackDelay(int time){
        attackDelay = new Delay(time);
        attackDelay.end();
    }
    public void setAttackDamage(int amt){
        attackDamage = amt;
    }
    public void restartAttackDelay(){
        attackDelay.start();
    }
    
}
