package com.s464968.gamedata.item;

public class ItemData {
    public String getName(){
        return name;
    };
    public String getDescription(){
        return description;
    }
    public ItemType getItemType(){
        return itemType;
    }
    public ItemData(String name, String description, ItemType itemType){
        this.name=name;
        this.description=description;
        this.itemType=itemType;
    }
    private final String name;
    private final String description;
    private final ItemType itemType;
}
