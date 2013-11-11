/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game;

/**
 *
 * @author Philipp Friese
 */
public class Delay {

    private int length;
    private long endTime;
    boolean started;
    
    public Delay(int length)
    {
        this.length = length;
        started = false;
    }
    
    public boolean isOver()
    {
        if(!started) return false;
        
        return Time.getTime() >= endTime;
    }
    
    public boolean isActive()
    {
        return started;
    }
    
    public void restart()
    {
        started = true;
        endTime = length * 1000000 + Time.getTime();
    }
    
    public void stop()
    {
        started = false;
    }
    
    
    
    public void terminate()
    {
        started = true;
        endTime = 0;
    }
}

