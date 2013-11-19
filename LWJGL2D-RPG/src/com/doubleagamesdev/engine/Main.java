/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.engine;

import com.doubleagamesdev.game.Game;
import com.doubleagamesdev.game.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Phil
 */
public class Main {
    
    public static void main(String[] args)
    {
        initDisplay();
        initGL();
        initGame();
        
        
        gameLoop();
        
        cleanUp();
    }

    private static void initGL()
    {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        glMatrixMode(GL_MODELVIEW);
        
        glDisable(GL_DEPTH_TEST);
        
        glClearColor(0, 0, 0, 0);
    }
    
    private static void initGame()
    {
        Game.game = new Game();
    }
    
    private static void getInput()
    {
        Game.game.getInput();
    }

    private static void update()
     {
         Game.game.update();
     }
    
    private static void render()
     {
         glClear(GL_COLOR_BUFFER_BIT);
         glLoadIdentity();
         
         Game.game.render();
         
         Display.update();
         Display.sync(60);        
     }
     
    private static void cleanUp()
     {
         Display.destroy();
         Keyboard.destroy();
     }
     
    private static void gameLoop()
    {
        Time.init();
        
        int frames = 0;
        long lastTime = System.nanoTime();
        long totalTime = 0; 
        
        while(!Display.isCloseRequested())
        {       
            long now = System.nanoTime();
            long passed = now - lastTime;
            lastTime = now;
            totalTime += passed;
            
            if(totalTime >= 1000000000)
            {
                System.out.println(frames);
                totalTime = 0;
                frames = 0;
            }
            
            Time.update();
            getInput();
            update();
            render();
            frames++;
        }
    }
    
   
    
    private static void initDisplay()
    {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
            Keyboard.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
