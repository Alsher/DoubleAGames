/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doubeagames._2D.RPG;


import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Philipp Friese
 */
public class Sprite {
    
    private float r, g, b; // red/green/blue color
    private float sx, sy;
    
    public void Sprite(float r, float g, float b, float sx, float sy)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.sx = sx;
        this.sy = sy;
    }
    
    public void render()
    {
        
        glColor3f(r, g, b);
        glBegin(GL_QUADS);
        {
            glVertex2f(0, 0);
            glVertex2f(0, sy);
            glVertex2f(sx, sy);
            glVertex2f(sx, 0);
        }
        glEnd();
    }
    
}
