package com.base.engine.core;

import com.base.engine.rendering.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine {

    private Camera mainCamera;
    private Vector3f ambientLight;
    private DirectionalLight directionalLight;
    private DirectionalLight directionalLight2;
    private PointLight pointLight;
    private SpotLight spotLight;

    private PointLight[] pointLightList;
    private SpotLight[] spotLightList;

    public RenderingEngine()
    {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_DEPTH_CLAMP);

        mainCamera = new Camera((float)Math.toRadians(70.0f), (float) Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f);

        ambientLight = new Vector3f(0.2f, 0.2f, 0.2f);
        directionalLight = new DirectionalLight(new BaseLight(new Vector3f(0, 0, 1), 0.4f), new Vector3f(1, 1, 1));
        directionalLight2 = new DirectionalLight(new BaseLight(new Vector3f(1, 0, 0), 0.4f), new Vector3f(-1, 1, -1));

        int lightFieldWidth = 5;
        int lightFieldDepth = 5;

        float lightFieldStartX = 0;
        float lightFieldStartY = 0;
        float lightFieldStepX = 7;
        float lightFieldStepY = 7;

        pointLightList = new PointLight[lightFieldWidth * lightFieldDepth];
        spotLightList = new SpotLight[lightFieldWidth * lightFieldDepth];

        for(int i = 0; i < lightFieldWidth; i++)
        {
            for(int j = 0; j < lightFieldDepth; j++)
            {
                pointLightList[i * lightFieldWidth + j] = new PointLight(new BaseLight(new Vector3f(0,1,0), 0.4f),
                                                          new Attenuation(0,0,0.5f),
                        						          new Vector3f(lightFieldStartX + lightFieldStepX * i,0,lightFieldStartY + lightFieldStepY * j), 100);
            }
        }
        pointLight = pointLightList[0];

        for(int i = 0; i < lightFieldWidth; i++)
        {
            for(int j = 0; j < lightFieldDepth; j++)
            {
                spotLightList[i * lightFieldWidth + j] = new SpotLight( new PointLight(new BaseLight(new Vector3f(0,1,0), 0.4f),
                                                                                       new Attenuation(0,0,0.1f),
                                                                                       new Vector3f(lightFieldStartX + lightFieldStepX * i,0,lightFieldStartY + lightFieldStepY * j), 100),
                                                                        new Vector3f(1,0,0), 0.4f);
            }
        }
        spotLight = spotLightList[0];
    }


    public void input(float delta)
    {
        mainCamera.input(delta);
    }

    public void render(GameObject object)
    {
        clearScreen();

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

        //object.render(forwardDirectional);

        DirectionalLight temp = directionalLight;
        directionalLight = directionalLight2;
        directionalLight2 = temp;

        //object.render(forwardDirectional);

        temp = directionalLight;
        directionalLight = directionalLight2;
        directionalLight2 = temp;

        for(int i = 0; i < pointLightList.length; i++)
        {
            pointLight = pointLightList[i];
            object.render(forwardPoint);
        }

        for(int i = 0; i < spotLightList.length; i++)
        {
            spotLight = spotLightList[i];
            object.render(forwardSpot);
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
    public DirectionalLight getDirectionalLight()
    {
        return directionalLight;
    }
    public PointLight getPointLight()
    {
        return pointLight;
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

    public Camera getMainCamera()
    {
        return mainCamera;
    }
}
