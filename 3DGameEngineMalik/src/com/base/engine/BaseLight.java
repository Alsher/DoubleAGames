package com.base.engine;

/**
 * Created by Malik on 22.02.14.
 */
public class BaseLight {
    private Vector3f color;
    private float intensity;



    public BaseLight(Vector3f color, float intensity){
        this.color = color;
        this.intensity = intensity;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }
}
