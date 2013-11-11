/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.item;

/**
 *
 * @author Philipp Friese
 */
public class Cube extends Item{
    
    public static final float SIZE = 24f;
    

    public Cube(float x, float y)
    {
        init(x, y, 1.0f, 0.5f, 0f, SIZE, SIZE, "Cube");
    }
    

}


