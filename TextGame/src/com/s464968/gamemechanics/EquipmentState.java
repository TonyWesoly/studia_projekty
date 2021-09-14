package com.s464968.gamemechanics;

import com.s464968.GameData;
import com.s464968.gamedata.Equipment;
import com.s464968.gamedata.item.ItemType;
import com.s464968.graphics.Graphics;

public class EquipmentState extends State {
    public EquipmentState(GameData gameData) {
        super(gameData);
        playerEquipment = gameData.getPlayer().getEquipment();
        setChoices();
    }

    @Override
    public State updateData(Integer input) {
        if(input==1){
            return getItemInEquipment(ItemType.HELM);
        }else if(input==2){
            return getItemInEquipment(ItemType.ARMOR);
        }else if(input==3){
            return getItemInEquipment(ItemType.BOOTS);
        }else if(input==4){
            return getItemInEquipment(ItemType.WEAPON);
        }else if(input==5){
            return new PreparationState(gameData);
        }
        return null;
    }

    @Override
    public void updateGraphics(Graphics graphics) {
        drawErrorItemMissing(graphics);
        graphics.drawChoices(choices);
    }

    @Override
    protected void setChoices() {
        choices.addAll(playerEquipment.getEquipmentMenu());
        choices.add("Wyjdź z ekwipunku");
    }

    private ItemInEquipmentState getItemInEquipment(ItemType itemType){
        if(playerEquipment.itemExist(itemType)){
            return new ItemInEquipmentState(gameData,itemType);
        }else{
            isSelectedItemMissing = true;
        }
        return null;
    }

    private void drawErrorItemMissing(Graphics graphics){
        if(isSelectedItemMissing){
            graphics.drawError("Wybrany przedmiot nie istnieje!");
        }
    }

    private final Equipment playerEquipment;
    private boolean isSelectedItemMissing = false;
}
