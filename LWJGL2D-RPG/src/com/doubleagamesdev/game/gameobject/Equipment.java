/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.doubleagamesdev.game.gameobject;

import com.doubleagamesdev.game.item.EquippableItem;

/**
 *
 * @author Philipp Friese
 */
public class Equipment {
    
    private EquippableItem[] items;
    private Inventory inv;

    public Equipment(Inventory inv)
    {
       
        items = new EquippableItem[EquippableItem.NUM_SLOTS];
        this.inv = inv;
    }
    
    
    public boolean equip(EquippableItem item)
    {
        int index = item.getSlot();
        
        if(items[index] != null)
        {
            if(!deEquip(index))
                return false;
        }
        inv.remove(item);
        items[index] = item;
        return true;
    }
    
    public boolean deEquip(int slot)
    {
        if (inv.add(items[slot]))
        {
            items[slot] = null;
            return true;
        }
        return false;
    }
}

