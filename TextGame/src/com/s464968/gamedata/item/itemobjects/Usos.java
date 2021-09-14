package com.s464968.gamedata.item.itemobjects;

import com.s464968.gamedata.item.ItemData;
import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.item.itemcomponents.LvlItemComponent;
import com.s464968.gamedata.item.itemcomponents.PowerItemComponent;

public class Usos extends ItemsGen{
    public Usos(){
        itemData = new ItemData(
                "USOS",
                "+2 do poziomu\n"+
                        "-3 do mocy",
                ItemType.WEAPON
        );
        itemActions.add(new LvlItemComponent(2));
        itemActions.add(new PowerItemComponent(-3));
    }
}
