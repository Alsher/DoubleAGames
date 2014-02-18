package com.base.engine.core;

import com.base.engine.components.DirectionalLight;
import com.base.engine.components.PointLight;
import com.base.engine.rendering.*;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine {

    private Camera mainCamera;
    private Vector3f ambientLight;
    private DirectionalLight activeDirectionalLight;
    private PointLight activePointLight;
    private SpotLight spotLight;

    //"Permanent Structures"
    private ArrayList<DirectionalLight> directionalLights;
    private ArrayList<PointLight> pointLights;

    public RenderingEngine()
    {
        directionalLights = new ArrayList<>();
        pointLights = new ArrayList<>();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_DEPTH_CLAMP);

        mainCamera = new Camera((float)Math.toRadians(70.0f), (float) Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f);

        ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
    }


    public void input(float delta)
    {
        mainCamera.input(delta);
    }

    public void render(GameObject object)
    {
        clearScreen();

        clearLightList();
        object.addToRenderingEngine(this);

        Shader forwardAmbient = ForwardAmbient.getInstance();
        Shader forwardDirectional = ForwardDirectional.getInstance();
        Shader forwardPoint = ForwardPoint.getInstance();
        Shader forwardSpot = ForwardSpot.getInstance();
        forwardAmbient.setRenderingEngine(this);
        forwardDirectional.setRenderingEngine(this);
        forwardPoint.setRenderingEngine(this);
        forwardSpot.setRenderingEngine(this);

        object.render(forwardAmbient);

        glEnable(GL_BLEND);
        /** Enable **/
        glBlendFunc(GL_ONE, GL_ONE); //existing * 1, new * 1
        glDepthMask(false); //disable writing to depth buffer
        glDepthFunc(GL_EQUAL); //only add new pixel if same depth

        for(DirectionalLight light : directionalLights)
        {
            activeDirectionalLight = light;
            object.render(forwardDirectional);
        }

        for(PointLight light : pointLights)
        {
            activePointLight = light;
            object.render(forwardPoint);
        }

        /** Disable **/
        glDepthFunc(GL_LESS); //only add new pixel if less depth
        glDepthMask(true); //enable writing to depth buffer
        glDisable(GL_BLEND);
    }

    private void clearLightList()
    {
        directionalLights.clear();
        pointLights.clear();
    }

    private static void clearScreen()
    {
        //TODO: Stencil Buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public Vector3f getAmbientLight()
    {
        return ambientLight;
    }
    public DirectionalLight getActiveDirectionalLight()
    {
        return activeDirectionalLight;
    }
    public PointLight getActivePointLight()
    {
        return activePointLight;
    }
    public SpotLight getSpotLight()
    {
        return spotLight;
    }

    private static void setTextures(boolean enabled)
    {
        if(enabled)
            glEnable(GL_TEXTURE_2D);
        else
            glDisable(GL_TEXTURE_2D);
    }

    private static void unbindTextures()
    {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    private static void setClearColor(Vector3f color)
    {
        glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
    }

    public static String getOpenGLVersion()
    {
        return glGetString(GL_VERSION);
    }

    public void addDirectionalLight(DirectionalLight directionalLight)
    {
        directionalLights.add(directionalLight);
    }

    public void addPointLight(PointLight pointLight)
    {
        pointLights.add(pointLight);
    }

    public Camera getMainCamera()
    {
        return mainCamera;
    }
}
