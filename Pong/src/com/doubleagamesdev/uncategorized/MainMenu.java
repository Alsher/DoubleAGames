/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.uncategorized;

import com.doubleagamesdev.uncategorized.Main.GameState;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Philipp Friese
 */
public class MainMenu {
    
    public static final float SIZEX = 400f;
    public static final float SIZEY = 150f;
    private double mouseX;
    private double mouseY;

    
    private static final float drawPosX = (Display.getWidth()/2)+ 350;
    private static final float drawNegX = (Display.getWidth()/2)- 350;
    private static final float drawY = ((Display.getHeight() / 2) + 100);
    
    
    public void drawMenu()
    {               
        Texture text = Textures.loadTexture("textures");
        Draw.drawCenteredRectWithTexture(drawNegX, drawY, -SIZEX, SIZEY, text, 1, 1, 1, true, 0.75f, 0.875f, 0.75f, 1, 1, 1, 1, 0.875f, false);
        Draw.drawCenteredRectWithTexture(drawPosX, drawY, SIZEX, SIZEY, text, 1, 1, 1, false, 0, 0.75f, 0, 0.875f, 0.25f, 0.875f, 0.25f, 0.75f,  true);
    }
    
    public void render()
    {
        drawMenu();
        Display.setTitle("Main Menu");
    }
    
    public void update()
    {
        
    }
    
    public void checkMousePosition(double x, double y)
    {
        this.mouseX = x;
        this.mouseY = y;
        System.out.println(mouseX + " | " +  mouseY);
        
        if(((double)mouseX > (drawNegX - (SIZEX/2))) && ((double)mouseY > (drawY - (SIZEY/2))) && /** <- first part | second part ->**/ ((double)mouseX < (drawNegX + (SIZEX/2))) && ((double)mouseY < (drawY + (SIZEY/2))))
        {
            System.out.println("button one");
            Main.state = GameState.SINGLE;
        }
        
        if(((double)mouseX > (drawPosX - (SIZEX/2))) && ((double)mouseY > (drawY - (SIZEY/2))) && /** <- first part | second part ->**/ ((double)mouseX < (drawPosX + (SIZEX/2))) && ((double)mouseY < (drawY + (SIZEY/2))))
        {
            System.out.println("button two");
            Main.state = GameState.MULTI;
        }
       
    }

}

