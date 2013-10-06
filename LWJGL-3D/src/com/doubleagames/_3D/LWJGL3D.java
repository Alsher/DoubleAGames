/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagames._3D;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Phil
 */
public class LWJGL3D {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        initDisplay();
        gameLoop();
        cleanUp();
    }
    
    
    public static void gameLoop()
    {
        Camera cam = new Camera(70, (float)Display.getWidth() / (float)Display.getHeight(), 0.3f, 1000);
        float x = 0;
        
        while(!Display.isCloseRequested())
        {
            glClear(GL_COLOR_BUFFER_BIT);
            glLoadIdentity();
            cam.useView();
            
            glPushMatrix(); //push in own matrix
            {
                glColor3f(1.0f, 0.5f, 0f);
                glTranslatef(0, 0, -10);
                glRotatef(x, 1, 0, 0);
                glBegin(GL_QUADS);
                {
                    //front Face
                    glVertex3f(-1, -1, 1);
                    glVertex3f(-1, 1, 1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(1, -1, 1);
                    
                    //back face
                    glVertex3f(-1, -1, -1);
                    glVertex3f(-1, 1, -1);
                    glVertex3f(1, 1, -1);
                    glVertex3f(1, -1, -1);
                    
                    // bottom
                    glVertex3f(-1, -1, -1);
                    glVertex3f(-1, -1, 1);
                    glVertex3f(-1, 1, 1);
                    glVertex3f(-1, 1, -1);
                    
                    // top
                    glVertex3f(1, -1, -1);
                    glVertex3f(1, -1, 1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(1, 1, -1);
                    
                    //left
                    glVertex3f(-1, -1, -1);
                    glVertex3f(1, -1, -1);
                    glVertex3f(1, -1, 1);
                    glVertex3f(-1, -1, 1);
                    
                    //right
                    glVertex3f(-1, 1, -1);
                    glVertex3f(1, 1, -1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(-1, 1, 1);
                }
                glEnd();
            }
            glPopMatrix();
            
            x += 5;
            Display.update();
        }
    }
    
    public static void cleanUp()
    {
        Display.destroy();
    }
    
    
    public static void initDisplay()
    {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException ex) {
            Logger.getLogger(LWJGL3D.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
