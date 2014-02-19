package com.base.engine;

import org.lwjgl.input.Keyboard;
import sun.net.www.content.audio.basic;

/**
 * Created by Malik on 19.01.14.
 */
public class Game {

    private Mesh mesh;
    private Shader shader;
    private Material material;
    private Transform transform;
    private Camera camera;

    public Game(){
        mesh = new Mesh();
        material = new Material(RecourceLoader.loadTexture("test.png"), new Vector3f(0,1,1));
        shader = new PhongShader().getIntance();
        camera = new Camera();

        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1, 0), new Vector2f(0,0)),
                                      new Vertex(new Vector3f(-1, 1, 0), new Vector2f(0.5f,0)),
                                      new Vertex(new Vector3f( 0, 1, 0), new Vector2f(1.0f,0)),
                                      new Vertex(new Vector3f( 0,-1, 1), new Vector2f(0.0f,0.5f))};

        int[] indices = new int[]{3,1,0,
                                  2,1,3,
                                  0,1,2,
                                  0,2,3};

        mesh.addVertices(vertices, indices);


        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
        Transform.setCamera(camera);
        transform = new Transform();

        PhongShader.setAmbientLight(new Vector3f(1,1,1));
    }

    public void input(){
        camera.input();

     //   if(Input.getKeyDown(Keyboard.KEY_UP))
     //       System.out.println("up");
     //  if(Input.getKeyUp(Keyboard.KEY_UP))
     //       System.out.println("released up");
     //
     //   if(Input.getMouseDown(1))
     //       System.out.println("right click");
     //   if(Input.getMouseUp(1))
     //       System.out.println("released right click");
    }

    float temp = 0.0f;

    public void update(){
        temp += Time.getDelta();

        float sinTemp = (float)Math.sin(temp);

        transform.setTranslation(sinTemp, 0, 5);
        transform.setRotation(0 ,sinTemp * 180, 0);
        //transform.setScale(sinTemp, sinTemp, sinTemp);
    }

    public void render(){
        RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectionTransformation(), material);
        mesh.draw();
    }
}
