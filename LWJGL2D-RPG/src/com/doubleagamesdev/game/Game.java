/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.game;

import com.doubleagamesdev.engine.GameObject;
import com.doubleagamesdev.engine.Physics;
import com.doubleagamesdev.game.gameobject.CookieMonster;
import com.doubleagamesdev.game.gameobject.Player;
import com.doubleagamesdev.game.item.Cube;
import java.awt.Rectangle;
import java.util.ArrayList;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Philipp Friese
 */
public class Game 
{
    private ArrayList<GameObject> objects;
    private ArrayList<GameObject> remove;
    private Player player; 
    
    public Game()
    {
        objects = new ArrayList<>();
        remove = new ArrayList<>();
        
        player = new Player(Display.getWidth() / 2 - Player.SIZE / 2, Display.getHeight() / 2 - Player.SIZE / 2);
        
        objects.add(player);
        objects.add(new Cube(32, 32, player));
        objects.add(new CookieMonster(300, 500, 1));
        
    }
    
    
    public void getInput()
    {
        player.getInput();
    }
    
    public void update()
    {
        for(GameObject go : objects)
        {
            if(!go.getRemove())
                go.update();
            else
            {
                remove.add(go);
            }
        }
        
        for(GameObject go : remove)
                objects.remove(go);
    }
    
    public void render()
    {
        for(GameObject go : objects)
            go.render();
    }
    
    public ArrayList<GameObject> sphereCollide(float x, float y, float radius)
    {
        ArrayList<GameObject> res = new ArrayList<>();
        
        for(GameObject go : objects)
        {
            if(Util.dist(go.getX(), go.getY(), x, y) < radius)
                res.add(go);
        }
        
        return res;
    }
    
    public ArrayList<GameObject> rectangleCollide(float x1, float y1, float x2, float y2)
    {
        ArrayList<GameObject> res = new ArrayList<>();
        
        float sx = x2 - x1;
        float sy = y2 - y1;
        
        Rectangle collider = new Rectangle((int)x1, (int)y1, (int)sx, (int)sy);
        
        for(GameObject go : objects)
        {
            if(Physics.checkCollision(collider, go) != null)
            {
                res.add(go);
            }
        }
        
        return res;
    }

}
