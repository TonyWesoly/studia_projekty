package com.s464968.gamedata;

import com.s464968.gamedata.item.Item;

public class Player {
    public Integer getLvl() {
        if(lvl < 1){
            return 1;
        }
        return lvl;
    }

    public Integer getItemsPower() {
        if(itemsPower<0){
            return 0;
        }
        return itemsPower;
    }
    public Integer getWholePlayerPower(){
        if((itemsPower + lvl) < 1){
            return 1;
        }
        return itemsPower + lvl;
    }
//    public void setItemsPower(int newPower){
//        itemsPower = newPower;
//    }
    public int modifyItemPower(int modifier){
        itemsPower += modifier;
        return itemsPower;
    }
//TODO zrobic żeby może nie zwracało się na chama inventory i eq
    public Inventory getInventory() {
        return inventory;
    }

    public void addItemToInventory(Item newItem){
        inventory.addItem(newItem);
    }

    public Equipment getEquipment() {
        return equipment;
    }

//    public int lvlUp(){
//        lvl++;
//        return lvl;
//    }

    public int modifyLvl(int lvlModifier){
        lvl+=lvlModifier;
        return lvl;
    }

    public int getChanceToEscape(){
        return chanceToEscape;
    }

    public int modifyChanceToEscape(int modifier){
        chanceToEscape += modifier;
//        if(chanceToEscape>100){
//            chanceToEscape = 100;
//        }
//        if(chanceToEscape<0){
//            chanceToEscape = 0;
//        }
        return chanceToEscape;
    }

    public void die(){
        equipment.clearEquipment();
    }

    public void resetPlayer(){
        lvl = 1;
        equipment.clearEquipment();
        inventory.clearInventory();
    }

    private Integer lvl = 1;
    private Integer itemsPower = 0;
    private Integer chanceToEscape = 50;
    private Inventory inventory = new Inventory();
    private Equipment equipment = new Equipment();
}
