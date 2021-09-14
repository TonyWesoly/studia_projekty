package com.s464968.gamedata;

import com.s464968.gamedata.item.Item;

import java.util.ArrayList;

public class Inventory {
    public void addItem(Item item){
        if(items.size()< maxCapacity){
            items.add(item);
        }
    }
    public Item getItem(int index){
        return items.get(index);
    }
    public Item removeItem(int index){
        return items.remove(index);
    }
    public ArrayList<String> getItemsNames(){
        ArrayList<String> itemsNames = new ArrayList<>();
        for (Item item :
                items) {
            itemsNames.add(item.getName());
        }
        return itemsNames;
    }
    public ArrayList<String> getItemsDescription(){
        ArrayList<String> itemsDescription = new ArrayList<>();
        for (Item item :
                items) {
            itemsDescription.add(item.getName());
        }
        return itemsDescription;
    }

    public int size(){
        return items.size();
    }

    public int getMaxCapacity(){
        return maxCapacity;
    }

    public boolean isFull(){
        return items.size()==maxCapacity;
    }

    public void clearInventory(){
        items.clear();
    }

    private final ArrayList<Item> items = new ArrayList<>();
    private int maxCapacity = 3;
}
