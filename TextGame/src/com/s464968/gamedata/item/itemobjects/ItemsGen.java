package com.s464968.gamedata.item.itemobjects;

import com.s464968.gamedata.item.Item;
import com.s464968.gamedata.item.ItemData;
import com.s464968.gamedata.Player;
import com.s464968.gamedata.item.itemcomponents.ItemComponent;

import java.util.ArrayList;

public class ItemsGen {
    public Item getItem(Player player){
        return new Item(player,itemData, itemActions);
    }

    protected ItemData itemData;
    protected ArrayList<ItemComponent> itemActions = new ArrayList<>();
}
