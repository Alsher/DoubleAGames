/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject;

import com.base.engine.GameObject;
import com.base.game.Delay;
import com.base.game.Game;
import com.base.game.Time;
import com.base.game.Util;
import com.base.gameobject.items.Item;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.glTranslatef;

/**
 *
 * @author Malik
 */
public class Player extends statObject{
    
    public static final float SIZE = 32;
    
    private Inventory inventory;
    private Equipment equipment;
    
    public static final int FORWARD = 0;
    public static final int BACKWARD = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
    private int attackRange;
    private int facingDirection; 
    private Delay attackDelay;
    private int attackDamage;
    
    private int moveAmountX;
    private int moveAmountY;
    
    public Player(float x, float y){
        init(x,y,0.1f,1f,0.3f,SIZE,SIZE,PLAYER_ID);
        stats = new Stats(0, true);
        inventory = new Inventory(20);
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
        //System.out.println("States: SPEED: " + getSpeed() + " Level: " + getLevel() + " MAXHP: " + getMaxHealth() + " HP: " + getCurrentHealth() + " STRENGTH: " + getStrength() + " MAGIC: " + getMagic());
        float newX = x + (float)moveAmountX;
        float newY = y + (float)moveAmountY;
        
        moveAmountX = 0;
        moveAmountY = 0;
        
        ArrayList<GameObject> objects = Game.rectangleCollide(newX, newY, newX + SIZE, newY + SIZE);
        ArrayList<GameObject> items = new ArrayList<GameObject>();
        
        boolean move = true;
        
        for(GameObject go : objects){
            if(go.getType() == GameObject.ITEM_ID)
                items.add(go);
            if(go.getSolid())
                move = false;
        }
        if(!move)
            return;
            
        x = newX;
        y = newY;
        
        for(GameObject go: items){
           System.out.println("You just picked up an " + ((Item)go).getName() + "!");
           go.remove();
           addItem((Item)go);
           
        }
        if(stats.getCurrentHealth() <= 0)
            Death();
    }
    protected void Death(){
        remove();
    }
    public void getInput(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            move(0,1);
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            move(0,-1);
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            move(-1,0);
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            move(1,0);
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && attackDelay.isOver())
            attack();
    }
    public void attack(){
        System.out.print("This is Sparta!");
        
        //find enemies in attackrange
        ArrayList<GameObject> objects = new ArrayList<GameObject>();
        
        if(facingDirection == FORWARD)
            objects = Game.rectangleCollide(x, y, x + SIZE, y + attackRange);
        else if(facingDirection == BACKWARD)
            objects = Game.rectangleCollide(x, y - attackRange + SIZE, x + SIZE, y);
        else if(facingDirection == LEFT)
            objects = Game.rectangleCollide(x - attackRange + SIZE, y, x, y + SIZE);
        else if(facingDirection == RIGHT)
            objects = Game.rectangleCollide(x, y, x + attackRange, y + SIZE);
        
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        
        for(GameObject go : objects){
            if(go.getType() == ENEMY_ID)
                enemies.add((Enemy)go);
        }
        //find closest enemy
        if(enemies.size() > 0){
            Enemy target;
            
            target = enemies.get(0);
            {
            if(enemies.size() > 1)
                for(Enemy e : enemies)
                    if(Util.dist(x, y, e.getX(), e.getY()) < Util.dist(x, y, target.getX(), target.getY()))
                        target = e;
            }
            target.damage(attackDamage);
            System.out.println(" : " + target.getCurrentHealth() + "/" + target.getMaxHealth()); 
        }
        else
            System.out.println(" no target");
        
        attackDelay.restart();
    }
    public void addItem(Item item){
        inventory.add(item);
    }
    @Override
    public void render(){
        glTranslatef(Display.getWidth() / 2 - Player.SIZE / 2,Display.getHeight() / 2 - Player.SIZE / 2,0);
        spr.render();
        glTranslatef(-x,-y,0);
    }
    public void addXP(float amt){
        stats.addXP(amt);
    }
    private void move(float magX,float magY){
        if(magX == 0 && magY == 1)
            facingDirection = FORWARD;
        if(magX == 0 && magY == -1)
            facingDirection = BACKWARD;
        if(magX == -1 && magY == 0)
            facingDirection = LEFT;
        if(magX == 1 && magY == 0)
            facingDirection = RIGHT;
        
        moveAmountX += 4f * magX * Time.getDelta();
        moveAmountY += 4f * magY * Time.getDelta();
    }
}
