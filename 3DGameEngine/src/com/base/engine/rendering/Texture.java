package com.base.engine.rendering;

import com.base.engine.core.Util;
import com.base.engine.rendering.resourceManagement.TextureResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class Texture
{
    private static HashMap<String, TextureResource> loadedTextures = new HashMap<>();
	private TextureResource resource;
    private String fileName;
	
	public Texture(String fileName)
	{
        this.fileName = fileName;
        TextureResource oldResource = loadedTextures.get(fileName);

        if(oldResource != null)
        {
            resource = oldResource;
            resource.addReference();
        }
        else
        {
            resource = loadTexture(fileName);
            loadedTextures.put(fileName, resource);
        }
	}

    @Override
    protected void finalize()
    {
        if(resource.removeReference() && !fileName.isEmpty())
            loadedTextures.remove(fileName);
    }

    public void bind()
    {
        bind(0);
    }

	public void bind(int samplerSlot)
	{
        assert(samplerSlot >= 0 && samplerSlot <= 31);
        glActiveTexture(GL_TEXTURE0 + samplerSlot);
		glBindTexture(GL_TEXTURE_2D, resource.getId());
	}
	
	public int getID()
	{
		return resource.getId();
	}
	
	private static TextureResource loadTexture(String fileName)
	{
		String[] splitArray = fileName.split("\\.");
		String ext = splitArray[splitArray.length - 1];
		
		try
		{
            BufferedImage image = ImageIO.read(new File("./res/textures/" + fileName));
            int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());

            ByteBuffer buffer = Util.createByteBuffer(image.getHeight() * image.getWidth() * 4);
            boolean hasAlpha = image.getColorModel().hasAlpha();

            for(int y = 0; y < image.getHeight(); y++)
            {
                for(int x = 0; x < image.getWidth(); x++)
                {
                    int pixel = pixels[y * image.getWidth() + x]; //get current pixel

                    buffer.put((byte)((pixel >> 16) & 0xFF)); //get red byte from pixel and put it  to 2nd highest state bits
                    buffer.put((byte)((pixel >> 8) & 0xFF));  //get green byte from pixel and put it to 3rd highest state bits
                    buffer.put((byte)((pixel) & 0xFF));       //get blue byte from pixel and put it to 4th highest state bits

                    if(hasAlpha)
                        buffer.put((byte)((pixel >> 24) & 0xFF)); //if alpha is there put it to the highest state bits
                    else
                        buffer.put((byte)(0xFF)); //put byte of 0xFF | full opacity
                }
            }

            buffer.flip();

            TextureResource resource = new TextureResource();

            glBindTexture(GL_TEXTURE_2D, resource.getId());

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            return resource;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}
