in vec2 texCoord0;
in vec3 worldPos0;
in mat3 tbnMatrix;

uniform sampler2D diffuse;
uniform sampler2D normalMap;

#include "lighting.glh"
