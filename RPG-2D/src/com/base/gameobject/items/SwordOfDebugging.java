/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject.items;

import static com.base.gameobject.items.Cube.SIZE;

/**
 *
 * @author Malik
 */
public class SwordOfDebugging extends EquippableItem{
    
    public static final float SIZE = 32;
    
    private int damage;
    
    public SwordOfDebugging(float x, float y){
        init(x,y,1f,0.5f,0.0f,SIZE,SIZE,"The Ledenday Sword of Debuggery", WEAPON_SLOT);
        damage = 3;
    }
}
