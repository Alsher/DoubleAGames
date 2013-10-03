/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagames.uncategorized;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Phil
 * test
 */
public class Main {
    
    private static Game game;
   
    public static void main(String[] args)
    {
        //Initialize program
        initDisplay();
        initGL();
        
        initGame();
        
        gameLoop();
        cleanUp();        
    } 
    
    private static void initGame()
    {
        game = new Game();
    }
    
    private static void getInput()
    {
        game.getInput();
    }
    
    private static void update()
    {
        game.update();
    }
    
    private static void render()
    {
        
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity(); //clear matrix again for glTranslatef(..);

        game.render();

        Display.update();
        Display.sync(60);
    }
    
    private static void gameLoop()
    {
       while(!Display.isCloseRequested()) 
        {
            getInput();
            update();
            render();

        }     
    }
    
    
    
   
    private static void initGL()
    {
       glMatrixMode(GL_PROJECTION);
       glLoadIdentity(); // clear matrix
       glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1); //display 2D with getWidth() / getHeight() for getting screen size
       glMatrixMode(GL_MODELVIEW);
       
       glClearColor(0, 0, 0, 1);
       
       glDisable(GL_DEPTH_TEST);
    }
    
    private static void cleanUp()
    {
        Display.destroy();
    }
    
    private static void initDisplay()
    {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
            Display.setVSyncEnabled(true);
            
        } catch (LWJGLException ex) {            
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    
}
