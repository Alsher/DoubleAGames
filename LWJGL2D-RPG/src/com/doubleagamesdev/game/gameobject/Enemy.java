/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.engine.GameObject;
import static com.doubleagamesdev.engine.GameObject.PLAYER_ID;
import com.doubleagamesdev.engine.Main;
import com.doubleagamesdev.game.Delay;
import com.doubleagamesdev.game.Time;
import com.doubleagamesdev.game.Util;
import java.util.ArrayList;

/**
 *
 * @author Philipp Friese
 */
public class Enemy extends StatObject {
    
    public static final float DAMPING = 0.5f;
    
    
    private StatObject target; 
    private float attackRange;
    private int attackDamage;
    private Delay attackDelay;
    private float sightRange;
    
    public Enemy(int level)
    {
        stats = new Stats(level, false); //starting experience, levelable
        target = null;
        attackDelay = new Delay(500);
        attackRange = 48f;
        attackDamage = 1;
        attackDelay.end();
        sightRange = 128;
        
    }
    
    @Override
    public void update()
    {
        if(target == null)
            Look();
        else 
        {
            if(Util.LineOfSight(this, target) && Util.dist(x, y, getTarget().getX(), getTarget().getY()) <= attackRange)
            {
                if(attackDelay.over())
                    Attack();
            }
                
            else
                Chase();
        }
        
        if(stats.getCurrentHealth() <= 0)
            Death();
    }
    
    
    protected void Attack()
    {
        getTarget().damage(getAttackDamage());
        //System.out.println("targets health: " + getTarget().getCurrentHealth() + "/" + getTarget().getMaxHealth());
        restartAttackDelay();
    }
    
    protected void Death()
    {
        remove();
    }
  
    
    protected void Look()
    {
        ArrayList<GameObject> objects = Main.sphereCollide(x, y, sightRange);
        
        for(GameObject go : objects)
            if(go.getType() == PLAYER_ID)
                setTarget((StatObject)go);
    }
    
    protected void Chase()
    {
        float speedX = (getTarget().getX() - x);
        float speedY = (getTarget().getY() - y);
        
        float maxSpeed = getStats().getSpeed() * DAMPING;
        
        if(speedX > maxSpeed)
            speedX = maxSpeed;
        if(speedX < -maxSpeed)
            speedX = -maxSpeed;
        
        if(speedY > maxSpeed)
            speedY = maxSpeed;
        if(speedY < -maxSpeed)
            speedY = -maxSpeed;
        
        x = x + speedX * Time.getDelta();
        y = y + speedY * Time.getDelta();
    }
    

    public void setTarget(StatObject go)
    {
        target = go;
    }
    
    public StatObject getTarget()
    {
        return target;
    }
    
    public Stats getStats()
    {
        return stats;
    }
    
    public int getAttackDamage()
    {
        return attackDamage;
    }
  
    public void setAttackRange(int range)
    {
        attackRange = range;
    }
    
    public void setAttackDelay(int time)
    {
        attackDelay = new Delay(time);
        attackDelay.end();
    }

    public void setAttackDamage(int amt)
    {
        attackDamage = amt;
    }
    
    public void restartAttackDelay()
    {
        attackDelay.start();
    }
    
    public void setSightRange(float dist)
    {
        sightRange = dist;
    }
}

