package com.base.engine.rendering;

import com.base.engine.components.BaseLight;
import com.base.engine.core.GameObject;
import com.base.engine.core.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine {

    private Camera mainCamera;
    private Vector3f ambientLight;

    //"More Permanent" Structure
    private ArrayList<BaseLight> lights;
    private BaseLight activeLight;

    public RenderingEngine()
    {
        lights = new ArrayList<>();
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

        lights.clear();
        object.addToRenderingEngine(this);

        Shader forwardAmbient = ForwardAmbient.getInstance();
        forwardAmbient.setRenderingEngine(this);

        object.render(forwardAmbient);

        glEnable(GL_BLEND);
        /** Enable **/
        glBlendFunc(GL_ONE, GL_ONE); //existing * 1, new * 1
        glDepthMask(false); //disable writing to depth buffer
        glDepthFunc(GL_EQUAL); //only add new pixel if same depth

        for(BaseLight light : lights)
        {
            light.getShader().setRenderingEngine(this);

            activeLight = light;

            object.render(light.getShader());
        }

        /** Disable **/
        glDepthFunc(GL_LESS); //only add new pixel if less depth
        glDepthMask(true); //enable writing to depth buffer
        glDisable(GL_BLEND);
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

    public void addLight(BaseLight light)
    {
        lights.add(light);
    }

    public BaseLight getActiveLight()
    {
        return activeLight;
    }

    public Camera getMainCamera()
    {
        return mainCamera;
    }
}
