package com.base.engine;

/**
 * Created by Malik on 28.01.14.
 */
public class Vertex {
    public static final int SIZE = 5;

    private Vector3f pos;
    private Vector2f texCoord;

    public Vertex(Vector3f pos){
        this(pos, new Vector2f(0,0));
    }

    public Vertex(Vector3f pos, Vector2f textCoord){
        this.pos = pos;
        this.texCoord = textCoord;
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector2f getTextCoord() {
        return texCoord;
    }

    public void setTextCoord(Vector2f textCoord) {
        this.texCoord = textCoord;
    }

}
