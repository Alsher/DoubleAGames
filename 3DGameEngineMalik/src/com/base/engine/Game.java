package com.base.engine;

import org.lwjgl.input.Keyboard;

/**
 * Created by Malik on 19.01.14.
 */
public class Game {

    private Mesh mesh;

    public Game(){
        mesh = new Mesh();

        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1,-1, 0)),
                                      new Vertex(new Vector3f(-1, 1, 0)),
                                      new Vertex(new Vector3f( 0, 1, 0)),};

        mesh.addVertices(data);
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
        mesh.draw();
    }
}
