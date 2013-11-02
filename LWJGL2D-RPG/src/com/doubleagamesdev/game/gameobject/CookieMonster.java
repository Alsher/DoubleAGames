/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;


/**
 *
 * @author Philipp Friese
 */
public class CookieMonster extends Enemy {
    
    public static final int SIZE = 32;
    
    public CookieMonster(float x, float y, int level)
    {
        super(level);
        init(x, y, 0.2f, 0.2f, 1f, SIZE, SIZE, 0);
        setAttackDelay(200);
    }
}

