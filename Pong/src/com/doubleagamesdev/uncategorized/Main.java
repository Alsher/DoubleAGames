/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.uncategorized;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Malik / Philipp Friese
 * 
 */



public class Main {
    
    public enum GameState {
        MAIN_MENU, SINGLE, MULTI;
    }
    
    private static Game game;
    public static GameState state;
    private static MainMenu mainMenu;
    
    
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
        state = GameState.MAIN_MENU;
        mainMenu = new MainMenu();
    }
    
    private static void getInput()
    {
        game.getInput();
    }
    
    
    private static void updateSingle()
    {
        game.updateSingle();
    }
    
    private static void updateMulti()
    {
        game.updateMulti();
    }
    
    
    
    private static void renderSingle()
    {
        
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity(); //clear matrix again for glTranslatef(..);

        game.renderSingle();

        Display.update();
        Display.sync(60);
    }
    
    private static void renderMulti()
    {
        
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity(); //clear matrix again for glTranslatef(..);

        game.renderMulti();

        Display.update();
        Display.sync(60);
    }
    
    
    private static void updateMainMenu()
    {
        mainMenu.update();
        game.setI(0);
    }
    
    private static void renderMainMenu()
    {
        
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity(); //clear matrix again for glTranslatef(..);

        mainMenu.render();

        Display.update();
        Display.sync(60);
    }
    
    
    
    private static void gameLoop()
    {
       while(!Display.isCloseRequested())
            {
                switch(state)
                     {          
                        /** enum state == MAIN_MENU **/
                        case MAIN_MENU:
                            {  
                                getInput();
                                updateMainMenu();
                                renderMainMenu();
                                break;
                            }
                        
                        /** enum state == MULTI **/
                        case MULTI:
                           {
                               getInput();
                               updateMulti();
                               renderMulti();
                               break;
                           }
                        
                        /** enum state == SINGLE **/
                        case SINGLE:
                           {
                               getInput();
                               updateSingle();
                               renderSingle();
                               break;
                           }

                        default:
                        {
                            break;
                        }
                     }
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
       glEnable(GL_TEXTURE_2D);
    }
    
    private static void cleanUp()
    {
        Display.destroy();
        Keyboard.destroy();
    }
    
    private static void initDisplay()
    {
        try {
            Display.setDisplayMode(new DisplayMode(1280,720));
            Display.create();
            Display.setVSyncEnabled(true);
            Keyboard.create();

        } catch (LWJGLException ex) {            
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
     }          
    
}
