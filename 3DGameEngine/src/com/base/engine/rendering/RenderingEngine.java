package com.base.engine.rendering;

import com.base.engine.components.BaseLight;
import com.base.engine.components.Camera;
import com.base.engine.core.GameObject;
import com.base.engine.core.Transform;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.resourceManagement.MappedValues;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine extends MappedValues
{
    private HashMap<String, Integer> samplerMap;
	private ArrayList<BaseLight> lights;
	private BaseLight activeLight;

    private Shader forwardAmbient;
    private Camera mainCamera;

	public RenderingEngine()
	{
        super();
        lights = new ArrayList<>();
        samplerMap = new HashMap<>();
        samplerMap.put("diffuse", 0);
        samplerMap.put("normalMap", 1);

        addVector3f("ambient", new Vector3f(0.1f, 0.1f, 0.1f));

        forwardAmbient = new Shader("forward-ambient");

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_DEPTH_CLAMP);
	}

    public void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType)
    {
        throw new IllegalArgumentException(uniformName + " is not a supported type in Rendering Engine");
    }

	public void render(GameObject object)
	{
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        object.renderAll(forwardAmbient, this);

        glEnable(GL_BLEND);

        /** Enable **/
        glBlendFunc(GL_ONE, GL_ONE); //existing * 1, new * 1
        glDepthMask(false); //disable writing to depth buffer
        glDepthFunc(GL_EQUAL); //only add new pixel if same depth

        for(BaseLight light : lights)
        {
            activeLight = light;
            object.renderAll(light.getShader(), this);
        }

        /** Disable **/
        glDepthFunc(GL_LESS); //only add new pixel if less depth
        glDepthMask(true); //enable writing to depth buffer
        glDisable(GL_BLEND);
	}

    public static String getOpenGLVersion()
	{
		return glGetString(GL_VERSION);
	}

	public void addLight(BaseLight light)
	{
		lights.add(light);
	}

	public void addCamera(Camera camera)
	{
		mainCamera = camera;
	}

    public int getSamplerSlot(String samplerName)
    {
        return samplerMap.get(samplerName);
    }

	public BaseLight getActiveLight()
	{
		return activeLight;
	}

	public Camera getMainCamera()
	{
		return mainCamera;
	}

	public void setMainCamera(Camera mainCamera)
	{
		this.mainCamera = mainCamera;
	}
}
