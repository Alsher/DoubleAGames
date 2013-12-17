/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject;

import com.base.engine.GameObject;
import com.base.engine.Main;
import com.base.game.Util;
import java.util.ArrayList;

/**
 *
 * @author Malik
 */
public class CookieMonster extends Enermy{
    public static final int SIZE = 32;
    public static final float DAMPING = 0.5f;
    
    public static final float SIGHT_RANGE = 128;
    
    public CookieMonster(float x, float y, int level){
        super(level);
        this.init(x, y, 0.2f, 0.2f, 1.0f, SIZE, SIZE, 0);
        setAttackDelay(200); 
   
    }
    @Override
    protected void Look(){
        ArrayList<GameObject> objects = Main.sphereCollide(x,y,SIGHT_RANGE);
        
        for(GameObject go : objects)
            if(go.getType() == PLAYER_ID)
                setTarget((statObject)go);
    }
    @Override
    protected void Chase(){
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
        
        x = x + speedX;
        y = y + speedY;
    }
    @Override 
    protected void Attack(){
        
        
        //todo hurt im
        damage(getAttackDamage());
        System.out.println("enermy health " + getCurrentHealth() + "/" + getMaxHealth());
        restartAttackDelay();
    }
    
    @Override
    protected void Death(){
        remove();
    }
}
