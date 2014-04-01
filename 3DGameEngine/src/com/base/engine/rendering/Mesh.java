package com.base.engine.rendering;

import com.base.engine.core.Util;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.meshLoading.IndexedModel;
import com.base.engine.rendering.meshLoading.OBJModel;
import com.base.engine.rendering.resourceManagement.MeshResource;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.glu.GLU;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;


public class Mesh { //storing  on graphics card of some data of some length

    private static HashMap<String, MeshResource> loadedModels = new HashMap<>();
    private MeshResource resource;
    private String fileName;

    public Mesh(String fileName)
    {
        this.fileName = fileName;
        MeshResource oldResource = loadedModels.get(fileName);

        if(oldResource != null)
        {
            resource = oldResource;
            resource.addReference();
        }
        else
        {
            loadMesh(fileName);
            loadedModels.put(fileName, resource);
        }
    }

    public Mesh(Vertex[] vertices, int[] indices)
    {
        this(vertices, indices, false);
    }

    public Mesh(Vertex[] vertices, int[] indices, boolean calcNormals)
    {
        fileName = "";
        addVertices(vertices, indices, calcNormals);
    }

    @Override
    protected void finalize()
    {
        if(resource.removeReference() && !fileName.isEmpty())
            loadedModels.remove(fileName);

    }

    private void addVertices(Vertex[] vertices, int[] indices, boolean calcNormals)
    {
        if(calcNormals)
            calcNormals(vertices, indices);

        resource = new MeshResource(indices.length, glGenVertexArrays());

        //new Vertex Array Object (VAO) in memory and selected (bound)
        glBindVertexArray(resource.getVao());

        //new Vertex Buffer Object (VBO) in memory and selected (bound)
        //vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
        GL15.glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, resource.getIbo());
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);

        //put VBO in attributes list at index 0, texCoord at index 1 and normals at index 2
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);

        //deselect (bind to 0) the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        //deselect (bind to o) the VAO
        glBindVertexArray(0);

        this.exitOnGLError("Error in addVertices()");
    }



    public void draw()
    {
        //bind to VAO containing the info
        //glClear(GL_COLOR_BUFFER_BIT);

        //GL13.glActiveTexture(GL13.GL_TEXTURE0);
        //glBindTexture(GL_TEXTURE_2D, ibo);

        glBindVertexArray(resource.getVao());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        //draw vertices
        //glDrawArrays(GL_TRIANGLES, 0, Vertex.SIZE);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, resource.getIbo());
        glDrawElements(GL_TRIANGLES, resource.getSize(), GL_UNSIGNED_INT, 0);

        //deselect (bind to 0) everything
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);

        this.exitOnGLError("Error in draw()");
    }

    private void calcNormals(Vertex[] vertices, int[] indices)
    {
        for(int i = 0; i < indices.length; i += 3)
        {
            int i0 = indices[i];
            int i1 = indices[i + 1];
            int i2 = indices[i + 2];

            Vector3f v1 = vertices[i1].getPos().sub(vertices[i0].getPos());
            Vector3f v2 = vertices[i2].getPos().sub(vertices[i0].getPos());

            Vector3f normal = v1.cross(v2).normalized();

            vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
            vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
            vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
        }

        for(int i = 0; i < vertices.length; i++)
            vertices[i].setNormal(vertices[i].getNormal().normalized());
    }



    private Mesh loadMesh(String fileName)
    {
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length -1];

        if(!ext.equals("obj"))
        {
            System.err.println("Error: File Format not supported for mesh data: " + ext);
            new Exception().printStackTrace();
            System.exit(-1);
        }

        OBJModel test = new OBJModel("./res/models/" + fileName);
        IndexedModel model = test.toIndexedModel();
        model.calcNormals();

        ArrayList<Vertex> vertices = new ArrayList<>();

        for(int i = 0; i < model.getPositions().size(); i++)
        {
            vertices.add(new Vertex(model.getPositions().get(i),
                                    model.getTexCoords().get(i),
                                    model.getNormals().get(i)));
        }

        Vertex[] vertexData = new Vertex[vertices.size()];
        vertices.toArray(vertexData);

        Integer[] indexData = new Integer[model.getIndices().size()];
        model.getIndices().toArray(indexData);

        addVertices(vertexData, Util.toIntArray(indexData), false);

        return null;
    }


    public void exitOnGLError(String errorMessage) {
        int errorValue = GL11.glGetError();

        if (errorValue != GL11.GL_NO_ERROR) {

            String errorString = GLU.gluErrorString(errorValue);
            System.err.println("ERROR - " + errorMessage + ": " + errorString);

            if (Display.isCreated()) Display.destroy();
            System.exit(-1);
        }
    }

}