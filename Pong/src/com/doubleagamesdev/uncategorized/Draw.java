/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.uncategorized;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Phil
 */

public class Draw {
    
    
    public static void drawRect(float x, float y, float width, float height)
    {
        drawRect(x, y, width, height, 0, 1, 1, 1);
    }
    public static void drawRect(float x, float y, float width, float height, float rot, float r, float g, float b)
    {
        glDisable(GL_TEXTURE_2D);
        glPushMatrix();
        {
            glTranslatef(x, y, 0);
            glRotatef(rot, 0, 0, 1);
            glColor3f(r, g, b);
            
            glBegin(GL_QUADS);
            {
                glVertex2f(0, 0);
                glVertex2f(0, height);
                glVertex2f(width, height);
                glVertex2f(width, 0);
            }
            glEnd();
        }
        glPopMatrix();
    }
    
    public static void drawCenteredRect(float x, float y, float sizeX, float sizeY, float r, float g, float b)
    {
        glPushMatrix();
        {
            //glColor3f(r, g, b);
            
            glBegin(GL_QUADS);
            {
                glVertex2f(x - (sizeX/2), y - (sizeY/2));
                glVertex2f(x - (sizeX/2), y + (sizeY/2));
                glVertex2f(x + (sizeX/2), y + (sizeY/2));
                glVertex2f(x + (sizeX/2), y - (sizeY/2));
            }
            glEnd();
        }
        glPopMatrix();
    }
    
    public static void drawCenteredRectWithTexture(float x, float y, float sizeX, float sizeY, Texture text, float r, float g, float b, boolean rot, float pos1, float pos2, float pos3, float pos4, float pos5, float pos6, float pos7, float pos8, boolean rot2)
    {
        glEnable(GL_TEXTURE_2D);
        glPushMatrix();
        {
            glColor3f(r, g, b);
            
            text.bind();
            if(rot == true)
            {
                glMatrixMode( GL_TEXTURE );
                glLoadIdentity();
                glRotatef( 180, 0, 0, 1);
                glMatrixMode( GL_MODELVIEW );
            }
            if(rot2 == true)
            {
                glMatrixMode( GL_TEXTURE );
                glLoadIdentity();
                glRotatef( 180, 1, 0, 0);
                glMatrixMode( GL_MODELVIEW );
            }
            glColor3f(r, g, b);
            
            glBegin(GL_QUADS);
            {
                glTexCoord2f(pos1, pos2);       glVertex2f(x - (sizeX/2), y - (sizeY/2));
                glTexCoord2f(pos3, pos4);       glVertex2f(x - (sizeX/2), y + (sizeY/2));
                glTexCoord2f(pos5, pos6);       glVertex2f(x + (sizeX/2), y + (sizeY/2));
                glTexCoord2f(pos7, pos8);       glVertex2f(x + (sizeX/2), y - (sizeY/2));

            }
            glEnd();
            
        }
        glPopMatrix();
        
    }
    
}
