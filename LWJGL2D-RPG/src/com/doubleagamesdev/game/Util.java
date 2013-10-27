/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game;

import com.doubleagamesdev.engine.GameObject;

/**
 *
 * @author Philipp Friese
 */
public class Util {
    
    public static boolean LineOfSight(GameObject go1, GameObject go2)
    {
        return true;
    }
    
    public static float dist(float x1, float y1, float x2, float y2)
    {
        double x = x2 - x1; //double for precision
        double y = y2 - y1;
        
        return (float)Math.sqrt((x * x) - (y * y));
    }

}

