package com.base.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

/**
 * Created by MadMoney
 */
public class Window
{
    public static void createWindow(int width, int height, String title)
    {
        try
        {
            /*
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            Display.create();
*/
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
}
