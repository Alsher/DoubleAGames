/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

/**
 *
 * @author Malik
 */
public class Frame {
    private int lenght;
    private Sprite spr;
    private int numDisplayed;
    
    public Frame(Sprite spr, int lenght){
        this.spr = spr;
        this.lenght = lenght;
        numDisplayed = 0;
        
        
    }
    
    public boolean render(){
        
        spr.render();
        numDisplayed++;
        
        if(numDisplayed >= lenght){
            numDisplayed = 0;
            return true;
        }
        
        return false;
    }
}
