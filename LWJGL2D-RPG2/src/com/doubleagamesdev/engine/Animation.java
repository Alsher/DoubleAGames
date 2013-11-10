/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.engine;

import java.util.ArrayList;

/**
 *
 * @author Philipp Friese
 */
public class Animation {
    
    private ArrayList<Frame> frames;
    private int curFrame;
    
    public Animation()
    {
        frames = new ArrayList<>();
        
    }
    
    public void render()
    {
        Frame temp  = frames.get(curFrame);
        if(temp.render())
        {
            curFrame++;
            curFrame %= frames.size();
        }
    }
}
