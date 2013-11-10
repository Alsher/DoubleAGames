/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.engine.GameObject;
import com.doubleagamesdev.engine.Main;
import com.doubleagamesdev.game.Delay;
import com.doubleagamesdev.game.Time;
import com.doubleagamesdev.game.Util;
import com.doubleagamesdev.game.item.Item;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Philipp Friese
 */
public class Player extends StatObject {
    
    public static final int SIZE = 32;
    
    public static final int FORWARD = 0;
    public static final int BACKWARD = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
    private Inventory inventory;
    
    private int attackRange;
    private int facingDirection;
    private Delay attackDelay;
    private int attackDamage;
    
    public Player(float x, float y)
    {
        init(x, y, 0.1f, 1f, 0.25f, SIZE, SIZE, PLAYER_ID);
        stats = new Stats(0, true);  //starting experience, levelable
        inventory = new Inventory(20); //inventory with 20 "slots"
        attackDelay = new Delay(500);
        attackRange = 48;
        attackDamage = 1;
        facingDirection = 0;
        attackDelay.end();
    }
    
    @Override
    public void update()
    {
        //System.out.println("Stats: Speed: " + getSpeed() + " Level: " + getLevel() + " MaxHP: " + getMaxHealth() + " HP: " + getCurrentHealth() + " Strength: " + getStrength() + " magic: " + getMagic());;
    }
    
    public void getInput()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            move(0, 1);
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            move(0, -1);
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            move(-1, 0);
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            move(1, 0);
        
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && attackDelay.over())
            attack();
    }
    
    public void attack()
    {
        
        
        ArrayList<GameObject> objects = new ArrayList<>();
        
        /** find objects in attack range **/
        if(facingDirection == FORWARD)
            objects = Main.rectangleCollide(x, y, x + SIZE, y + attackRange);
        else if(facingDirection == BACKWARD)
            objects = Main.rectangleCollide(x, y, x + SIZE, y - attackRange);
        else if(facingDirection == LEFT)
            objects = Main.rectangleCollide(x, y, x - attackRange, y + SIZE);
        else if(facingDirection == RIGHT)
            objects = Main.rectangleCollide(x, y, x + attackRange, y + SIZE);
        
        /** Find which objects are enemies **/
        ArrayList<Enemy> enemies = new ArrayList<>();
        
        for(GameObject go : objects)
        {
            if(go.getType() == ENEMY_ID)
                enemies.add((Enemy)go);
        }
        /** Find closest enemy, if one exists **/
        if(enemies.size() > 0)
        {
            Enemy target = enemies.get(0);
            
            if(enemies.size() > 1)
            {
                for(Enemy e : enemies)
                    if(Util.dist(x, y, e.getX(), e.getY()) < Util.dist(x, y, target.getX(), target.getY()))
                        target = e;
            }
            
            
            /** attack **/
            target.damage(attackDamage);
            System.out.println(" : " + target.getCurrentHealth() + " / " + target.getMaxHealth());
        }
        else
            System.out.println(" : No target");
            
           
        
        attackDelay.start();
    }
    
    private void move(float magX, float magY)
    {
        if(magX == 0 && magY == 1)
            facingDirection = FORWARD;
        if(magX == 0 && magY == -1)
            facingDirection = 1;
        if(magX == -1 && magY == BACKWARD)
            facingDirection = 2;
        if(magX == 1 && magY == LEFT)
            facingDirection = RIGHT;
        
        x += getSpeed() * magX * Time.getDelta();
        y += getSpeed() * magY * Time.getDelta();
    }
    
    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public void addXp(float amt)
    {
        stats.addXp(amt);
    }
    

}

