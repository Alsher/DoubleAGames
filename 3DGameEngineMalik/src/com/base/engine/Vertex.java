package com.base.engine;

/**
 * Created by Malik on 28.01.14.
 */
public class Vertex {
    public static final int SIZE = 8;

    private Vector3f pos;
    private Vector2f texCoord;
    private Vector3f normal;

    public Vertex(Vector3f pos){
        this(pos, new Vector2f(0,0));
    }

    public Vertex(Vector3f pos, Vector2f texCoord){
        this(pos, texCoord, new Vector3f(0,0,0));
    }

    public Vertex(Vector3f pos, Vector2f texCoord, Vector3f normal){
        this.pos = pos;
        this.texCoord = texCoord;
        this.normal = normal;
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

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }
}
