/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.engine.GameObject;
import com.doubleagamesdev.game.Delay;
import com.doubleagamesdev.game.Game;
import com.doubleagamesdev.game.Time;
import com.doubleagamesdev.game.Util;
import com.doubleagamesdev.game.item.Item;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;

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
    private Equipment equipment;
    
    private int attackRange;
    private int facingDirection;
    private Delay attackDelay;
    private int attackDamage;
    
    private int moveAmountX;
    private int moveAmountY;
    
    public Player(float x, float y)
    {
        init(x, y, 0.1f, 1f, 0.25f, SIZE, SIZE, PLAYER_ID);
        stats = new Stats(0, true);    //starting experience, levelable
        inventory = new Inventory(20); //inventory with 20 "slots"
        equipment = new Equipment(inventory);
        attackDelay = new Delay(500);
        attackRange = 49;
        attackDamage = 1;
        facingDirection = 0;
        moveAmountX = 0;
        moveAmountY = 0;
        attackDelay.terminate();
    }
    
    @Override
    public void update()
    {
        //System.out.println("Stats: Speed: " + getSpeed() + " Level: " + getLevel() + " MaxHP: " + getMaxHealth() + " HP: " + getCurrentHealth() + " Strength: " + getStrength() + " magic: " + getMagic());;
        float newX = x + (float)moveAmountX;
        float newY = y + (float)moveAmountY;
        
        moveAmountX = 0;
        moveAmountY = 0;
        
        ArrayList<GameObject> objects = Game.rectangleCollide(newX, newY, newX + SIZE, newY + SIZE);
        ArrayList<GameObject> items = new ArrayList<>();
        
        boolean move = true;
        
        for(GameObject go : objects)
        {
            if(go.getType() == GameObject.ITEM_ID)
                items.add(go);
            
            if(go.getSolid())
                 move = false;
                                    
            
        }
        
        if(!move)
            return;
        
        x = newX;
        y = newY;
        
        for(GameObject go : items)
        {
            System.out.println("You just picked up an item  " + ((Item)go).getName());
            go.remove();
            addItem((Item)go);
        }
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
        
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && attackDelay.isOver())
            attack();
    }
    
    public void attack()
    {
        
        
        ArrayList<GameObject> objects = new ArrayList<>();
        
        /** find objects in attack range **/
        if(facingDirection == FORWARD)
            objects = Game.rectangleCollide(x, y, x + SIZE, y + attackRange);
        else if(facingDirection == BACKWARD)
            objects = Game.rectangleCollide(x, y - attackRange + SIZE, x + SIZE, y);
        else if(facingDirection == LEFT)
            objects = Game.rectangleCollide(x - attackRange + SIZE, y, x, y + SIZE);
        else if(facingDirection == RIGHT)
            objects = Game.rectangleCollide(x, y, x + attackRange, y + SIZE);
        
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

        
        attackDelay.restart();
    }
    
    @Override
    public void render()
    {
         glTranslatef(Display.getWidth() / 2 - Player.SIZE / 2, Display.getHeight() / 2 - Player.SIZE / 2, 0);
         spr.render(); 
         glTranslatef(-x, -y, 0);
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
        
        moveAmountX += 4f * magX * Time.getDelta(); //TODO: Add speed based scaling
        moveAmountY += 4f * magY * Time.getDelta();
        
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

