package com.s464968.gamedata;

import com.s464968.gamedata.item.Item;
import com.s464968.gamedata.item.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Equipment {
//    public HashMap<ItemType, String> getItemsNames() {
//        HashMap<ItemType, String> itemNames = new HashMap<>();
//        for (Map.Entry<ItemType, Item> item : equipmentMap.entrySet()
//        ) {
//            String itemName = getItemNameIfNull(item.getKey());
//            itemNames.put(item.getKey(), itemName);
//        }
//        return itemNames;
//    }

    public ArrayList<String> getEquipmentMenu(){

        ArrayList<String> equipmentMenu = new ArrayList<>();
        equipmentMenu.add("Hełm = " + getItemNameIfNull(ItemType.HELM));
        equipmentMenu.add("Zbroja = " + getItemNameIfNull(ItemType.ARMOR));
        equipmentMenu.add("Buty = " + getItemNameIfNull(ItemType.BOOTS));
        equipmentMenu.add("Broń = " + getItemNameIfNull(ItemType.WEAPON));
        return equipmentMenu;
    }


//    public HashMap<ItemType, String> getItemsDescription() {
//        HashMap<ItemType, String> itemDescription = new HashMap<>();
//        for (Map.Entry<ItemType, Item> item : equipmentMap.entrySet()
//        ) {
//            itemDescription.put(item.getKey(), item.getValue().getDescription());
//        }
//        return itemDescription;
//    }

    public Item addItem(Item newItem) {
        Item itemToReturn = null;
        if (equipmentMap.containsKey(newItem.getItemType())) {
            itemToReturn = remove(newItem.getItemType());
        }
        equipmentMap.put(newItem.getItemType(), newItem);
        newItem.use();
        return itemToReturn;
    }

    public String getDescription(ItemType itemType){
        return getItemDescriptionIfNull(itemType);
    }

    public String getName(ItemType itemType){
        return getItemNameIfNull(itemType);
    }

    public boolean itemExist(ItemType itemType){
        return equipmentMap.containsKey(itemType);
    }

    public Item getItem(ItemType itemType){
        return equipmentMap.get(itemType);
    }

    public Item remove(ItemType itemType){
         Item itemToRemove = equipmentMap.remove(itemType);
         if(itemToRemove != null){
             itemToRemove.cancelUse();
         }
         return itemToRemove;
    }

    public void clearEquipment(){
        for (Item item: equipmentMap.values()) {
            item.cancelUse();
        }
        equipmentMap.clear();
    }

    private String getItemNameIfNull(ItemType itemType){
        String resultName = "brak";
        if(equipmentMap.containsKey(itemType)){
            resultName = equipmentMap.get(itemType).getName();
        }
        return resultName;
    }

    private String getItemDescriptionIfNull(ItemType itemType){
        String resultName = "brak";
        if(equipmentMap.containsKey(itemType)){
            resultName = equipmentMap.get(itemType).getDescription();
        }
        return resultName;
    }

    private final HashMap<ItemType, Item> equipmentMap = new HashMap<>();
}
