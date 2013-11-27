/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject.items;

import com.base.engine.Physiks;
import com.base.gameobject.Player;

/**
 *
 * @author Malik
 */
public class Cube extends Item{
    public static final float SIZE = 32;
    
    public Cube(float x, float y, Player play){
        super(play);
        init(x,y,1f,0.5f,0.0f,SIZE,SIZE,"The Cube");
    }
}
