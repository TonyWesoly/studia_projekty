package com.s464968.gamemechanics;

import com.s464968.GameData;
import com.s464968.gamedata.Equipment;
import com.s464968.gamedata.Inventory;
import com.s464968.gamedata.item.Item;
import com.s464968.gamedata.item.ItemType;
import com.s464968.graphics.Graphics;

public class ItemInInventoryState extends State {
    public ItemInInventoryState(GameData gameData, int itemIndex) {
        super(gameData);
        playerInventory = gameData.getPlayer().getInventory();
        playerEquipment = gameData.getPlayer().getEquipment();
        selectedItem = playerInventory.getItem(itemIndex);
        this.itemIndex = itemIndex;
        setChoices();
    }

    @Override
    public State updateData(Integer input) {
        if (input == 1) {
            putOnItem();
            return new InventoryState(gameData);
        } else if (input == 2) {
            removeItem();
            return new InventoryState(gameData);
        } else if (input == 3) {
            return new InventoryState(gameData);
        }
        return null;
    }

    @Override
    public void updateGraphics(Graphics graphics) {
        graphics.drawItem(selectedItem);
        graphics.drawChoices(choices);
    }

    @Override
    protected void setChoices() {
        choices.add("Załóż/zamień");
        choices.add("Wyrzuć");
        choices.add("Wyjdź z plecaka");
    }

    private void putOnItem() {
        ItemType removedItemType = selectedItem.getItemType();
        Item itemFromEquipment = playerEquipment.remove(removedItemType);
        playerEquipment.addItem(selectedItem);
        playerInventory.removeItem(itemIndex);
        if(itemFromEquipment!=null){
            playerInventory.addItem(itemFromEquipment);
        }
    }

    private void removeItem() {
        playerInventory.removeItem(itemIndex);
    }

    final private Item selectedItem;
    final private int itemIndex;
    final private Inventory playerInventory;
    final private Equipment playerEquipment;
}
