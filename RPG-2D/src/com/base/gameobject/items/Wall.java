/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject.items;

import com.base.engine.GameObject;

/**
 *
 * @author Malik
 */
public class Wall extends GameObject{
    public Wall(float x, float y, float sizeX, float sizeY){
        init(x,y,1f,0.5f,0.0f,sizeX,sizeY,DEFAULT_ID);
        setSolid(true);
    }
}
