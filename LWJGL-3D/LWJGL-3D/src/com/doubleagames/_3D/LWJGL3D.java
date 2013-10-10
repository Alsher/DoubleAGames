/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagames._3D;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author Philipp Friese
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
    
    public static Texture loadTexture(String key)
    {
        try
            {
                return TextureLoader.getTexture("png", new FileInputStream(new File("res/" + key + ".png")));
            }
                catch (IOException ex)
            {
        Logger.getLogger(LWJGL3D.class.getName()).log(Level.SEVERE, null, ex);
    }

return null;
}
    
    public static void gameLoop()
    {
        Texture wood = loadTexture("wood");
        
        Camera cam = new Camera(70, (float)Display.getWidth() / (float)Display.getHeight(), 0.3f, 1000);
        float x = 0;
        
        boolean temp = false;
        
        while(!Display.isCloseRequested())
        {
            boolean forward = Keyboard.isKeyDown(Keyboard.KEY_W) | Keyboard.isKeyDown(Keyboard.KEY_UP);
            boolean backward = Keyboard.isKeyDown(Keyboard.KEY_S) | Keyboard.isKeyDown(Keyboard.KEY_DOWN);
            boolean left = Keyboard.isKeyDown(Keyboard.KEY_A);
            boolean right = Keyboard.isKeyDown(Keyboard.KEY_D);
            
            if(forward)
                cam.move(0.3f, 1);
            if(backward)
                cam.move(-0.3f,1);
            if(left) 
                cam.move(0.3f, 0);
            if(right)
                cam.move(-0.3f, 0);
            
            if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 
                cam.rotateY(-2f);
            if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
                cam.rotateY(2f);

            
            
            
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            cam.useView();
            
            glPushMatrix(); //push in own matrix
            {
                wood.bind(); //texture file
                glColor3f(1.0f, 0.5f, 0f);
                glTranslatef(0, 0, -10); // shift cube
                
                if((forward && left ) || (forward && right) || (backward && left) || (backward && right))
                    {
                        temp = true;
                    }
                
                if(temp)
                    glRotatef(45, 0, 1, 0);
                
                
                
                
                
                
                glBegin(GL_QUADS);
                {
                    //FrontFace
                    glColor3f(1f,0f,0f);
                    glTexCoord2f(0,0); glVertex3f(-1,-1,1);
                    glTexCoord2f(0,1); glVertex3f(1,-1,1);
                    glTexCoord2f(1,1); glVertex3f(1,1,1);
                    glTexCoord2f(1,0); glVertex3f(-1,1,1);

                    //BackFace
                    glColor3f(0f,1f,0f);
                    glTexCoord2f(0,0); glVertex3f(-1,-1,-1);
                    glTexCoord2f(0,1); glVertex3f(-1,1,-1);
                    glTexCoord2f(1,1); glVertex3f(1,1,-1);
                    glTexCoord2f(1,0); glVertex3f(1,-1,-1);

                    //BottomFace
                    glColor3f(0f,0f,1f);
                    glTexCoord2f(0,0); glVertex3f(-1,-1,-1);
                    glTexCoord2f(0,1); glVertex3f(-1,-1,1);
                    glTexCoord2f(1,1); glVertex3f(-1,1,1);
                    glTexCoord2f(1,0); glVertex3f(-1,1,-1);

                    //TopFace
                    glColor3f(1f,1f,0f);
                    glTexCoord2f(0,0); glVertex3f(1,-1,-1);
                    glTexCoord2f(0,1); glVertex3f(1,-1,1);
                    glTexCoord2f(1,1); glVertex3f(1,1,1);
                    glTexCoord2f(1,0); glVertex3f(1,1,-1);

                    //LeftFace
                    glColor3f(0f,1f,1f);
                    glTexCoord2f(0,0); glVertex3f(-1,-1,-1);
                    glTexCoord2f(0,1); glVertex3f(1,-1,-1);
                    glTexCoord2f(1,1); glVertex3f(1,-1,1);
                    glTexCoord2f(1,0); glVertex3f(-1,-1,1);

                    //Right Face
                    glColor3f(1f,0f,1f);
                    glTexCoord2f(0,0); glVertex3f(-1,1,-1);
                    glTexCoord2f(0,1); glVertex3f(1,1,-1);
                    glTexCoord2f(1,1); glVertex3f(1,1,1);
                    glTexCoord2f(1,0); glVertex3f(-1,1,1);
                    
                    
                }
                glEnd();
            }
            glPopMatrix();
            
            x += 1.5f;
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
