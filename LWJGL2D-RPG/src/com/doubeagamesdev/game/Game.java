/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubeagamesdev.game;

import com.doubleagamesdev.engine.GameObject;
import com.doubleagamesdev.game.gameobject.Player;
import java.util.ArrayList;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Philipp Friese
 */
public class Game 
{
    private ArrayList<GameObject> objects;
    private Player player; 
    
    public Game()
    {
        objects = new ArrayList<GameObject>();
        
        player = new Player(Display.getWidth() / 2 - Player.SIZE / 2, Display.getHeight() / 2 - Player.SIZE / 2);
        
        objects.add(player);
        
    }
    
    
    public void getInput()
    {
        player.getInput();
    }
    
    public void update()
    {
        for(GameObject go : objects)
            go.update();
    }
    
    public void render()
    {
        for(GameObject go : objects)
            go.render();
       
    }
}
