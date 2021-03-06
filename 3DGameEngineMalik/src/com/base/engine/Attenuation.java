package com.base.engine;

/**
 * Created by Malik on 23.02.14.
 */
public class Attenuation {
    private float constant;
    private float linear;
    private float exponent;

    public Attenuation(float constant, float linear, float exponent){
        this.constant = constant;
        this.linear = linear;
        this.exponent = exponent;
    }

    public float getExponent() {
        return exponent;
    }

    public void setExponent(float exponent) {
        this.exponent = exponent;
    }

    public float getLinear() {
        return linear;
    }

    public void setLinear(float linear) {
        this.linear = linear;
    }

    public float getConstant() {
        return constant;
    }

    public void setConstant(float constant) {
        this.constant = constant;
    }
}
