/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

import com.base.engine.GameObject;
import com.base.gameobject.CookieMonster;
import com.base.gameobject.Player;
import com.base.gameobject.items.Cube;
import java.util.ArrayList;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Malik
 */
public class Game {
    
    private ArrayList<GameObject> objects;
    private ArrayList<GameObject> remove;
    private Player player;
    
    public Game(){
        objects = new ArrayList<GameObject>();
        remove = new ArrayList<GameObject>();
        
        player = new Player(Display.getWidth() / 2 - Player.SIZE / 2,Display.getHeight() / 2 - Player.SIZE / 2);
        
        objects.add(player);
        objects.add(new Cube(32,32,player));
        objects.add(new CookieMonster(300,500,1));
    }
    public void getInput(){
        player.getInput();
    }
    public void update(){
        
        for(GameObject go : objects)
            if(!go.getRemove()) //yeah fuck the small things
                go.update();
            else{
                remove.add(go);
            }
        for(GameObject go :remove)
            objects.remove(go);
    }
    public void render(){
        for(GameObject go : objects)
            go.render();
    }
    public GameObject[] SphereCollide(float x, float y, float radius){
        ArrayList<GameObject> res = new ArrayList<GameObject>();
        
        for(GameObject go : objects){
            if(Util.Dist(go.getX(),go.getY(), x, y) < radius)
                res.add(go);
        }
        
        return (GameObject[])res.toArray();
    }
    
}