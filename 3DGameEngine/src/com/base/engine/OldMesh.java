package com.base.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class OldMesh { //storing  on graphics card of some data of some length
	
	private int vaoId;
	private int vboId;

	
	public OldMesh()
	{
		
	}
	

	
	 public void addVertices(float[] vertices)
	{
		 
		 //sending data requires flipped byte buffers
		 FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
		 verticesBuffer.put(vertices);
		 verticesBuffer.flip();
		 
		 //new Vertex Array Object (VAO) in memory and selected (bound)
		 vaoId = glGenVertexArrays();
		 glBindVertexArray(vaoId);
		 
		 //new Vertex Buffer Object (VBO) in memory and selected (bound)
		 vboId = glGenBuffers();
		 glBindBuffer(GL_ARRAY_BUFFER, vboId);
		 glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
		 
		 //put VBO in attributes list at index 0
		 glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		 
		 //deselect (bind to 0) the VBO
		 glBindBuffer(GL_ARRAY_BUFFER, 0);
		 
		 //deselect (bind to o) the VAO
		 glBindVertexArray(0);
	        
	     this.exitOnGLError("Error in addVertices");
	}
	
	
	
	public void draw()
	{
		//bind to VAO containing the info
		glBindVertexArray(vaoId);
		glEnableVertexAttribArray(0);
		
		//draw vertices
		glDrawArrays(GL_TRIANGLES, 0, Vertex.SIZE);
		
		//deselect (bind to 0) everything
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		
		this.exitOnGLError("Error in draw");
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
