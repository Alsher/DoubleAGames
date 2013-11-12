/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.uncategorized;

/**
 *
 * @author Philipp Friese
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Textures {
    
    public static Texture loadTexture(String key)
    {
        try
            {
                return TextureLoader.getTexture("PNG", new FileInputStream(new File("assets/" + key + ".png")));
            }
                catch (IOException ex)
            {
        Logger.getLogger(Textures.class.getName()).log(Level.SEVERE, null, ex);
    }

    return null;
    }

}

