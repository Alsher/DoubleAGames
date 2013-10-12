/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doubleagamesdev.uncategorized;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Phil
 */

public class Draw {
    
    public static void drawRect(float x, float y, float width, float height)
    {
        drawRect(x, y, width, height, 0);
    }
    public static void drawRect(float x, float y, float width, float height, float rot)
    {
        glPushMatrix();
        {
            
            glTranslatef(x, y, 0);
            glRotatef(rot, 0, 0, 1);
            
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
    
}
