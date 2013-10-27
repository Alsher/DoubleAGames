/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.item;

import com.doubleagamesdev.engine.Physics;
import com.doubleagamesdev.game.Game;
import com.doubleagamesdev.game.gameobject.Player;

/**
 *
 * @author Philipp Friese
 */
public class Cube extends Item{
    
    public static final float SIZE = 24f;
    

    public Cube(float x, float y, Player play)
    {    
        super(play);
        init(x, y, 1.0f, 0.5f, 0f, SIZE, SIZE, "Cube");
    }
    

}


