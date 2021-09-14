package com.s464968.gamemechanics;

import com.s464968.GameData;
import com.s464968.gamedata.Equipment;
import com.s464968.gamedata.Inventory;
import com.s464968.gamedata.item.Item;
import com.s464968.gamedata.item.ItemType;
import com.s464968.graphics.Graphics;

public class InventoryState extends State{
    public InventoryState(GameData gameData){
        super(gameData);
        playerInventory = gameData.getPlayer().getInventory();
        setChoices();
    }

    @Override
    public State updateData(Integer input) {
        int returnOptionNumber = playerInventory.size()+1;
        if(input<=playerInventory.size()){
            return new ItemInInventoryState(gameData,input-1);
        }
        if(input == returnOptionNumber){
            return new PreparationState(gameData);
        }
        return null;
    }

    @Override
    public void updateGraphics(Graphics graphics) {
        graphics.drawChoices(choices);
    }

    @Override
    protected void setChoices() {
        choices.addAll(playerInventory.getItemsNames());
        choices.add("Wyjdź z plecaka");
    }
    final Inventory playerInventory;
}
