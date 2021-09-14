package com.s464968.gamedata.item.itemobjects;

import com.s464968.gamedata.item.ItemData;
import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.item.itemcomponents.PowerItemComponent;

public class Ciupaga extends ItemsGen{
    public Ciupaga(){
        itemData = new ItemData(
                "Ciupaga",
                "+3 do mocy",
                ItemType.WEAPON
        );
        itemActions.add(new PowerItemComponent(3));
    }
}
