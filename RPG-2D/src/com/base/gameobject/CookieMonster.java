/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject;

import com.base.engine.GameObject;
import com.base.engine.Main;

/**
 *
 * @author Malik
 */
public class CookieMonster extends Enermy{
    private static int SIZE = 32;
    
    public CookieMonster(float x, float y, int level){
        super(level);
        this.init(x, y, 0.2f, 0.2f, 1.0f, SIZE, SIZE, 0);
    }
    protected void Look(){
        GameObject[] objects = Main.sphereCollide(x,y,radius);
    }
}
