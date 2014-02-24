package com.base.engine;


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

        shader = PhongShader.getInstance();
        camera = new Camera();
        transform = new Transform();

        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1, 0), new Vector2f(0,0)),
                new Vertex(new Vector3f(-1, 1, 0), new Vector2f(0.5f,0)),
                new Vertex(new Vector3f( 0, 1, 0), new Vector2f(1.0f,0)),
                new Vertex(new Vector3f( 0,-1, 1), new Vector2f(0.0f,0.5f))};

        int[] indices = new int[]{3,1,0,
                2,1,3,
                0,1,2,
                0,2,3};
        material = new Material(ResourceLoader.loadTexture("voxel.png"), new Vector3f(0,1,1));
        mesh = new Mesh(vertices, indices, true);

        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
        Transform.setCamera(camera);

        //PhongShader.setAmbientLight(new Vector3f(1,1,1));
        //PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));

        //PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1,0,0), 0.8f), new Attenuation(0,0,1), new Vector3f(-2,0,5f));
        //PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0,0,1), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f));

        //PhongShader.setMaxPointLight(new PointLight[]{pLight1, pLight2});
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

        transform.setTranslation(0, 0, 5);
        transform.setRotation(0 ,temp * 180, 0);
        //transform.setScale(sinTemp, sinTemp, sinTemp);
    }

    public void render(){
        //RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectionTransformation(), material);
        mesh.draw();
    }
}
