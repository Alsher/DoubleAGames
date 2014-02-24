#version 150 core

in vec3 vVertex;

uniform mat4 transform;

void main()
{
	gl_Position = transform * vec4(vVertex.x, vVertex.y, vVertex.z, 1.0);
    
}