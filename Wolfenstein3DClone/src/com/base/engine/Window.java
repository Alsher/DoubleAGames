package com.base.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;

public class Window {
	
	
	public static void createWindow(int width, int height, String title)
	{
		try {
				PixelFormat pixelFormat = new PixelFormat();
	            ContextAttribs contextAtrributes = new ContextAttribs(4, 1)
	                .withForwardCompatible(true)
	                .withProfileCore(true);
	
	            Display.setDisplayMode(new DisplayMode(width, height));
	            Display.setTitle(title);
	            Display.create(pixelFormat, contextAtrributes);
	            //Display.setVSyncEnabled(true);
	            
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

	public static void exitOnGLError(String errorMessage) 
	{
        int errorValue = GL11.glGetError();
        if (errorValue != GL11.GL_NO_ERROR) 
        {
	        	String errorString = GLU.gluErrorString(errorValue);
	            System.err.println("ERROR - " + errorMessage + ": " + errorString);
	
	            if (Display.isCreated()) Display.destroy();
	            System.exit(-1);
        }
    }

}
