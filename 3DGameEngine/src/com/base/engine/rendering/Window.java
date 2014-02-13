package com.base.engine.rendering;

import com.base.engine.core.Vector2f;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Window 
{
    public static void createWindow(int width, int height, String title)
    {
        try {
            PixelFormat pixelFormat = new PixelFormat();
            ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
                    .withForwardCompatible(true)
                    .withProfileCore(true);

            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            Display.create(pixelFormat, contextAtrributes);
            Display.setVSyncEnabled(true);

            Keyboard.create();
            Mouse.create();
        }

        catch (LWJGLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public static void render()
	{
		Display.update();
	}
	
	public static void dispose()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	public static boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}

    public static int getWidth()
    {
        return Display.getDisplayMode().getWidth();
    }

    public static int getHeight()
    {
        return Display.getDisplayMode().getHeight();
    }
	
	public static String getTitle()
	{
		return Display.getTitle();
	}

    public Vector2f getCenter()
    {
        return new Vector2f(getWidth()/2, getHeight()/2);
    }
}
