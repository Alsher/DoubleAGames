package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game {

	public Game()
	{
	
	}
	
	public void input()
	{
		
		if(Input.getKeyDown(Keyboard.KEY_UP))
			System.out.println("up pressed");
		if(Input.getKeyUp(Keyboard.KEY_UP))
			System.out.println("up released");
		
		if(Input.getMouseDown(1))
			System.out.println("mouse right pressed at " + Input.getMousePosition().toString());
		if(Input.getMouseUp(1))
			System.out.println("mouse right released");
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		
	}
	
}
