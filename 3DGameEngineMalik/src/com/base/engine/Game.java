package com.base.engine;

import org.lwjgl.input.Keyboard;

/**
 * Created by Malik on 19.01.14.
 */
public class Game {
    public Game(){
    }

    public void input(){
        if(Input.getKeyDown(Keyboard.KEY_UP))
            System.out.println("up");
        if(Input.getKeyUp(Keyboard.KEY_UP))
            System.out.println("released up");

        if(Input.getMouseDown(1))
            System.out.println("right click");
        if(Input.getMouseUp(1))
            System.out.println("released right click");
    }

    public void update(){

    }

    public void render(){

    }
}
