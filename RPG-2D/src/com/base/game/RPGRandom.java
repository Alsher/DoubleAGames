/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

import java.util.Random;

/**
 *
 * @author Malik
 */
public class RPGRandom {
    private static Random rand;
    
    
    public static void initRand(){
        rand = new Random();
    }
    public static int nextInt(){
        return rand.nextInt();
    }
    public static double nextDouble(){
        return rand.nextDouble();
    }
    public static int nextInt(int cap){
        return (int)((rand.nextDouble())*(double)cap);
    }
    public static double nextDouble(double cap){
        return (rand.nextDouble())*cap;
    }
}
