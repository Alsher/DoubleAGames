/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.uncategorized;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Phil
 */
public class Game {
    
    private ArrayList<GameObject> objects; 
    private GOPlayer player;
    private GOPlayer2 player2;
    private GOBall ball;
    private GOWall wall1;
    private GOWall wall2;
    private int playerScore;
    private int player2Score;
    
    
    public Game()
    {
        objects = new ArrayList();
        
        playerScore = 0;
        player2Score = 0;
        
        ball = new GOBall(Display.getWidth() / 2 - GOBall.SIZE / 2, Display.getHeight() / 2 - GOBall.SIZE / 2);
        wall1 = new GOWall(0, 0, Display.getWidth(), GOWall.STDSIZE, ball);
        wall2 = new GOWall(0, Display.getHeight() - GOWall.STDSIZE, Display.getWidth(), GOWall.STDSIZE, ball);
       
        player = new GOPlayer(0, Display.getHeight() / 2 - GOPlayer.SIZEY / 2, wall1, wall2, ball);
        player2 = new GOPlayer2((Display.getWidth() - GOPlayer2.SIZEX / 2) - 7, Display.getHeight() / 2 - GOPlayer2.SIZEY / 2, wall1, wall2, ball);
        
        objects.add(player);
        objects.add(ball);
        objects.add(player2);
        objects.add(wall1);
        objects.add(wall2);
    }
    
     public void getInput()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) 
            player.move( 1);
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            player.move(-1);
        if(Keyboard.isKeyDown(Keyboard.KEY_UP))
            player2.move( 1);
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
            player2.move(-1);
    }
    
    public void update()
    {
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
    
   
    
    public void render() 
    {
        for(GameObject go : objects) // for every object in GameObject: do render()
            go.render();
        
        Display.setTitle("Score: Player = " + playerScore + "   |   " +  "Player2 = " + player2Score);
    }
    
    
}
