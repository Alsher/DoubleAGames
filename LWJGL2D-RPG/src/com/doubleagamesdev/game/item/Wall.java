/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.item;

import com.doubleagamesdev.engine.GameObject;


/**
 *
 * @author Philipp Friese
 */
public class Wall extends GameObject {

    public Wall(float x, float y, float sizeX, float sizeY)
    {
        init(x, y, 1.0f, 0.5f, 0f, sizeX, sizeY, DEFAULT_ID);
        setSolid(true);
    }
    
}

