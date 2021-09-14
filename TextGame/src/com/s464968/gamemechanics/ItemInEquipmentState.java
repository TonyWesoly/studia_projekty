package com.s464968.gamemechanics;

import com.s464968.GameData;
import com.s464968.gamedata.Equipment;
import com.s464968.gamedata.Inventory;
import com.s464968.gamedata.item.Item;
import com.s464968.gamedata.item.ItemType;
import com.s464968.graphics.Graphics;

public class ItemInEquipmentState extends State{
    public ItemInEquipmentState(GameData gameData, ItemType itemType){
        super(gameData);
        selectedItemType = itemType;
        playerEquipment = gameData.getPlayer().getEquipment();
        playerInventory = gameData.getPlayer().getInventory();
        setChoices();
    }
    @Override
    public State updateData(Integer input) {
        if(input==1){
            putItemToInventory();
            return new EquipmentState(gameData);
        }else if(input==2){
            return new EquipmentState(gameData);
        }
        return null;
    }

    @Override
    public void updateGraphics(Graphics graphics) {
        if(playerInventory.isFull()){
            drawInventoryFullError(graphics);
        }
        Item item = playerEquipment.getItem(selectedItemType);
        graphics.drawItem(item);
        graphics.drawChoices(choices);
    }

    @Override
    protected void setChoices() {
        choices.add("Zdejmij");
        choices.add("Wyjdź");
    }
    private void putItemToInventory(){
        if(!playerInventory.isFull()){
            Item itemToInventory = playerEquipment.remove(selectedItemType);
            playerInventory.addItem(itemToInventory);
        }
    }
    private void drawInventoryFullError(Graphics graphics){
        graphics.drawError("Plecak jest pełen! Nie można zdjąć przedmiotu!");
    }
    private final ItemType selectedItemType;
    private final Equipment playerEquipment;
    private final Inventory playerInventory;
}
