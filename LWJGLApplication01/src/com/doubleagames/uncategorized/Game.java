/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagames.uncategorized;

import java.util.ArrayList;

/**
 *
 * @author Phil
 */
public class Game {
    
    private ArrayList<GameObject> objects;
    
    public Game()
    {
        objects = new ArrayList<GameObject>();
    }
    
     public void getInput()
    {
        
    }
    
    public void update()
    {
        for(GameObject go : objects)
            go.update();
    }
    
   
    
    public void render() 
    {
        for(GameObject go : objects) // for every object in GameObject: do render()
            go.render();
    }
}
