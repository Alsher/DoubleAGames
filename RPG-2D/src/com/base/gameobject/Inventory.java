/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.gameobject;

import com.base.gameobject.items.Item;

/**
 *
 * @author Malik
 */
public class Inventory {
    private Item[] items;
    private int firstFree;
    
    public Inventory(int size){
        items = new Item[size];
        firstFree = 0;
    }
    public boolean add(Item item){
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
        /*
        for(int i = 0; i < items.length; i++)
            if(items[i] == null){
                items[1] = item;
                System.out.println("Item " + item.getName() + " added to inventory at index" + i);
                return true;
            }
        return false;*/
    }
    
    
    public Item get(int index){
        return items[index];
    }
    
    public void remove(int index){
        items[index] = null;
        if(index < firstFree)
            firstFree = index;
    }
    
    public void remove(Item item){
        for(int i = 0; i < items.length; i++)
            if(items[i] == item){
                items[1] = null;
                if(i < firstFree)
                    firstFree = i;
                return;
            }
    }
}
