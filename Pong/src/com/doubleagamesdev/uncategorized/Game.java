/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.uncategorized;

import com.doubleagamesdev.uncategorized.Main.*;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Phil
 */
public class Game {
    
    private ArrayList<GameObject> objects; 
    public GOPlayer player;
    public GOPlayer2 player2;
    public GOEnemy enemy;
    public GOBall ball;
    public GOWall wall1;
    public GOWall wall2;
    private MainMenu mainMenu;
    
    
    private int playerScore;
    private int player2Score;
    private int enemyScore;
    private int i;
    
    private double mouseX;
    private double mouseY;
    
    
    public Game()
    {
        mainMenu = new MainMenu();

        objects = new ArrayList<>();
        playerScore = 0;
        player2Score = 0;
        enemyScore = 0;
        int i = 0;
        
        mouseX = Mouse.getX();
        mouseY = Mouse.getY();
        
        ball = new GOBall(Display.getWidth() / 2 - GOBall.SIZE / 2, Display.getHeight() / 2 - GOBall.SIZE / 2);
        wall1 = new GOWall(0, 0, Display.getWidth(), GOWall.STDSIZE, ball);
        wall2 = new GOWall(0, Display.getHeight() - GOWall.STDSIZE, Display.getWidth(), GOWall.STDSIZE, ball);
       
        player = new GOPlayer(0, Display.getHeight() / 2 - GOPlayer.SIZEY / 2, wall1, wall2, ball);
        player2 = new GOPlayer2((Display.getWidth() - GOPlayer2.SIZEX / 2) - 7, Display.getHeight() / 2 - GOPlayer2.SIZEY / 2, wall1, wall2, ball);        
        enemy = new GOEnemy((Display.getWidth() - GOEnemy.SIZEX / 2) - 7, Display.getHeight() / 2 - GOEnemy.SIZEY / 2, wall1, wall2, ball);
        
        
        objects.add(player);
        objects.add(ball);    
        objects.add(wall1);
        objects.add(wall2);
    }
    
    private void addOpponent()
    {
        if(i <= 2)
        {
            if(Main.state == GameState.SINGLE)
                {
                    objects.add(enemy);
                }
        else if(Main.state == GameState.MULTI)
                {
                    objects.add(player2);
                }
        else
        i++;
        }
    }
    
    public void getInput()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            player.move( 1);
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            player.move(-1);
        if(Main.state == GameState.MULTI)
        {
            if(Keyboard.isKeyDown(Keyboard.KEY_UP))
                player2.move( 1);
            if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
                player2.move(-1);
        }
        
        
        /** debug code for manual switch **/
        if(Keyboard.isKeyDown(Keyboard.KEY_M))
            Main.state = GameState.MAIN_MENU;
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            Main.state = GameState.MULTI;
        /* Disabled due to buggy removal of GameObjects
         
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        {
            Main.state = GameState.MAIN_MENU;
        }
        */
        
        if(Mouse.isButtonDown(0))
        {
            mouseX = Mouse.getX();
            mouseY = Mouse.getY();
            mainMenu.checkMousePosition(mouseX, mouseY);

        }
            
        if(Mouse.isButtonDown(1))
        {
            mouseX = Mouse.getX();
            mouseY = Mouse.getY();
            mainMenu.checkMousePosition(mouseX, mouseY);
    
        }
            
                
        
    }
    
     
    public void updateSingle()
    {
        addOpponent();
        for(GameObject go : objects)
                go.update();

        if(ball.getX() > Display.getWidth())
        {
            playerScore++;
            ball.resetPosition();
        }
        
        if(ball.getX() < 0)
        {
            enemyScore++;
        ball.resetPosition();
        }
    }
    
    public void updateMulti()
    {
        addOpponent();
        for(GameObject go : objects)
                go.update();

        if(ball.getX() > Display.getWidth())
        {
            playerScore++;
            ball.resetPosition();
        }
        
        if(ball.getX() < 0)
        {
            player2Score++;
        ball.resetPosition();
        }
    }
   
    public void renderSingle() 
    {
        for(GameObject go : objects) // for every object in GameObject: do render()
            go.render();
        
        Display.setTitle("Score: Player = " + playerScore + "   |   " +  "Enemy = " + player2Score);
    }
    
    public void renderMulti() 
    {
        for(GameObject go : objects) // for every object in GameObject: do render()
            go.render();
        
        Display.setTitle("Score: Player = " + playerScore + "   |   " +  "Player2 = " + player2Score);
    }

    public void setI(int i)
    {
        this.i = i;
    }
    
    
}
