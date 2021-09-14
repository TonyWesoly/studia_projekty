package com.s464968.gamedata.item.itemobjects;

import com.s464968.gamedata.item.ItemData;
import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.item.itemcomponents.PowerItemComponent;

public class Przepis extends ItemsGen{
    public Przepis(){
        itemData = new ItemData(
                "Przepis",
                "+4 do mocy",
                ItemType.ARMOR
        );
        itemActions.add(new PowerItemComponent(4));
    }
}
