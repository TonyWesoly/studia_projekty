package com.s464968.gamedata.monster.monsterComponents;

import com.s464968.gamedata.Equipment;
import com.s464968.gamedata.Inventory;
import com.s464968.gamedata.Player;
import com.s464968.gamedata.item.ItemType;

public class RemoveItemComponent extends LossComponent{
    public RemoveItemComponent(ItemType itemToRemove){
        this.itemToRemove = itemToRemove;
    }

    @Override
    public void use(Player player) {
        Equipment playerEquipment = player.getEquipment();
        playerEquipment.remove(itemToRemove);
    }

    private final ItemType itemToRemove;
}
