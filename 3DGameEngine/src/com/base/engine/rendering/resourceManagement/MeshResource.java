package com.base.engine.rendering.resourceManagement;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

public class MeshResource
{
    private int vao;

    private int vbo;
    private int ibo;
    private int size;

    private int refCount;


    public MeshResource(int size, int vao)
    {
        this.vao = vao;

        vbo = glGenBuffers();
        ibo = glGenBuffers();
        this.size = size;
        this.refCount = 1;
    }

    @Override
    protected void finalize()
    {
        glDeleteVertexArrays(vao);
        glDeleteBuffers(vbo);
        glDeleteBuffers(ibo);
    }

    public void addReference()
    {
        refCount++;
    }

    public boolean removeReference()
    {
        refCount--;
        return refCount == 0;
    }

    public int getVao() {
        return vao;
    }

    public int getVbo() {
        return vbo;
    }

    public int getIbo() {
        return ibo;
    }

    public int getSize() {
        return size;
    }

}
