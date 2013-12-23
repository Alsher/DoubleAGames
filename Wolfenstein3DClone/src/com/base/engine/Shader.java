package com.base.engine;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import org.lwjgl.opengl.GL11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import org.lwjgl.util.glu.GLU;

public class Shader {

	private static int program;
	private HashMap<String, Integer> uniforms;
	
	private boolean hasShader;
	
	public Shader()
	{
		program = glCreateProgram();
		uniforms = new HashMap<String, Integer>();
		hasShader = false;
		
		if(program == 0)
		{
			System.err.println("Shader creation failed: Could not find valid memory location in constructor");
			System.exit(1);
		}
		
	}
	
	public void bind()
	{
		glUseProgram(program);
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
	{ 
		
	}
	
	public void addUniform(String uniform)
	{
		int uniformLocation = glGetUniformLocation(program, uniform);
		int errorCheckValue = GL11.glGetError();
		
		if(uniformLocation == 0xFFFFFFFF)
		{
			System.err.println("Error: Could not find uniform: " + uniform);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		uniforms.put(uniform, uniformLocation);
		
		errorCheckValue = GL11.glGetError();
		if (errorCheckValue != GL11.GL_NO_ERROR) {
			System.out.println("ERROR - Could not create the shaders:" + GLU.gluErrorString(errorCheckValue));
			System.exit(-1);
			}
	}
	
	
	
	public void setUniformi(String uniformName, int value)
	{
		glUniform1i(uniforms.get(uniformName), value);
	}
	
	public void setUniformf(String uniformName, float value)
	{
		glUniform1f(uniforms.get(uniformName), value);
	}
	
	public void setUniform(String uniformName, Vector3f value)
	{
		glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
	}
	
	public void setUniform(String uniformName, Matrix4f value)
	{
		glUniformMatrix4(uniforms.get(uniformName), true, Util.createFlippedBuffer(value));;
	}
	
	public void addVertexShaderFromFile(String text)
	{
		addProgram(loadShader(text), GL_VERTEX_SHADER);
		
	}
	
	public void addGeometryShaderFromFile(String text)
	{
		addProgram(loadShader(text), GL_GEOMETRY_SHADER);
	}
	
	public void addFragmentShaderFromFile(String text)
	{
		addProgram(loadShader(text), GL_FRAGMENT_SHADER);
	}
	
	
	
	public void addVertexShader(String text)
	{
		addProgram(text, GL_VERTEX_SHADER);
		
	}
	
	public void addGeometryShader(String text)
	{
		addProgram(text, GL_GEOMETRY_SHADER);
	}
	
	public void addFragmentShader(String text)
	{
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void compileShader()
	{
		int errorCheckValue = GL11.glGetError();
		
		glLinkProgram(program); //link shader into one		
		glValidateProgram(program);
		
		errorCheckValue = GL11.glGetError();
		if (errorCheckValue != GL11.GL_NO_ERROR) {
			System.out.println("ERROR - Could not create the shaders:" + GLU.gluErrorString(errorCheckValue));
			System.exit(-1);
			}
	}
	
	private void addProgram(String text, int type)
	{
		if(hasShader==true)
		{
			System.out.println("already using a shader");
			return;
		}
		
		// create a fragment shader
	    int shaderId = glCreateShader(type);
	    glShaderSource(shaderId, text);
	    glCompileShader(shaderId);
	    if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL11.GL_FALSE) {
	    	System.err.println(glGetShaderInfoLog(shaderId, 1024));
	      System.exit(-1);
	    }

	    // attach to program
	    glAttachShader(program, shaderId);
	}

	private static String loadShader(String fileName)
	{
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;
		
		try
		{
			shaderReader = new BufferedReader(new FileReader("./res/shader/" + fileName));
			String line;
			
			while((line = shaderReader.readLine()) != null)
			{
				shaderSource.append(line).append("\n"); //end line param
			}
			
			shaderReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return shaderSource.toString();
	}

}
