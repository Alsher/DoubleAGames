package com.base.engine;

import sun.net.www.content.audio.basic;

/**
 * Created by Malik on 16.02.14.
 */
public class Basicshader extends Shader{
    private static final Basicshader intance = new Basicshader();

    public static Basicshader getIntance(){
        return  intance;
    }
    public Basicshader(){
        super();

        addVertexShader(RecourceLoader.loadShader("basicVertex.vs"));
        addFragmentShader(RecourceLoader.loadShader("basicFragment.fs"));
        compileShader();

        addUniform("transform");
        addUniform("color");
    }
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material){
        if (material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();

        setUniform("transform", projectedMatrix);
        setUniform("color", material.getColor());
    }
}
