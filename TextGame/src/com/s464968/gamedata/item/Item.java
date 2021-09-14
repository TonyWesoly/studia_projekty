package com.s464968.gamedata.item;

import com.s464968.gamedata.Player;
import com.s464968.gamedata.item.itemcomponents.ItemComponent;

import java.util.ArrayList;

public class Item {
    public Item(Player player,
                ItemData itemData,
                ArrayList<ItemComponent> itemComponentsOnUse) {
        this.player = player;
        this.itemData = itemData;
        this.itemActions = new ArrayList<>(itemComponentsOnUse);
//        this.componentsOnCancelUse = new ArrayList<>(itemComponentsOnCancelUse);
    }

    public void use() {
        for (ItemComponent itemAction :
                itemActions) {
            itemAction.use(player);
        }
    }

    public void cancelUse() {
        for (ItemComponent itemAction :
                itemActions) {
            itemAction.undo(player);
        }
    }

    public String getName() {
        return itemData.getName();
    }

    public String getDescription() {
        return itemData.getDescription();
    }

    public ItemType getItemType() {
        return itemData.getItemType();
    }

    private final Player player;
    private final ItemData itemData;
    private final ArrayList<ItemComponent> itemActions;
}
