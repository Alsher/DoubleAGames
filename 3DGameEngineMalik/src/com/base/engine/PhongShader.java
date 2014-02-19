package com.base.engine;

/**
 * Created by Malik on 17.02.14.
 */
public class PhongShader extends Shader{
    private static final PhongShader intance = new PhongShader();

    public static PhongShader getIntance(){
        return  intance;
    }

    private static Vector3f ambientLight;

    public PhongShader(){
        super();

        addVertexShader(RecourceLoader.loadShader("phongVertex.vs"));
        addFragmentShader(RecourceLoader.loadShader("phongFragment.fs"));
        compileShader();

        addUniform("transform");
        addUniform("baseColor");
        addUniform("ambientLight");
    }
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material){
        if (material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();

        setUniform("transform", projectedMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", ambientLight);
    }

    public static Vector3f getAmbientLight() {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3f ambientLight) {
        PhongShader.ambientLight = ambientLight;
    }
}
