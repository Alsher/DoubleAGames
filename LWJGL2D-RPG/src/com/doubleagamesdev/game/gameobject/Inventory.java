/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.game.item.Item;

/**
 *
 * @author Philipp Friese
 */
public class Inventory {
    
    private Item[] items; //Array because fixed size and empty slots, no use of ArrayList
    private int firstFree;
    
    public Inventory(int size)
    {
        items = new Item[size];
        firstFree = 0;
    }
    
    public boolean add(Item item)
    {
        if(firstFree == items.length)
            return false;
        
        items[firstFree] = item;
        
        for(int i = firstFree + 1; i < items.length; i++)
            if(items[i] == null)
            {
                firstFree = i;
                return true;
            }
        
        firstFree = items.length;
        
        return true;
    }
    
    
    public Item get(int index)
    {
        return items[index];
    }

    public void remove(int index)
    {
        items[index] = null;
        if(index < firstFree)
            firstFree = index;
    }
    
    public void remove(Item item)
    {
       for(int i = 0; i < items.length; i++)
           if(items[i] == item)
           {
               remove(i);               
               return;
           }
    }
}

