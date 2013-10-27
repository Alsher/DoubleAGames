/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.engine.GameObject;
import com.doubleagamesdev.engine.Main;
import java.util.ArrayList;

/**
 *
 * @author Philipp Friese
 */
public class CookieMonster extends Enemy {
    
    public static final int SIZE = 32;
    public static final float DAMPING = 0.5f;
    
    public CookieMonster(float x, float y, int level)
    {
        super(level);
        init(x, y, 0.2f, 0.2f, 1f, SIZE, SIZE, 0);
    }
    
    @Override
    protected void Look()
    {
        ArrayList<GameObject> objects = Main.sphereCollide(x, y, 128);
        
        for(GameObject go : objects)
            if(go.getType() == PLAYER_ID)
                setTarget(go);
    }
    
    @Override
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
        
        x = x + speedX;
        y = y + speedY;
    }
    

}

