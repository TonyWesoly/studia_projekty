package com.s464968.gamedata.item.itemobjects;

import com.s464968.gamedata.item.ItemData;
import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.item.itemcomponents.EscapeItemComponent;
import com.s464968.gamedata.item.itemcomponents.PowerItemComponent;

public class KulaUNogi extends ItemsGen{
    public KulaUNogi(){
        itemData = new ItemData(
                "Kula u nogi",
                "+5 do mocy\n" +
                        "-25% do ucieczki",
                ItemType.BOOTS
        );
        itemActions.add(new EscapeItemComponent(-25));
        itemActions.add(new PowerItemComponent(5));
    }
}
