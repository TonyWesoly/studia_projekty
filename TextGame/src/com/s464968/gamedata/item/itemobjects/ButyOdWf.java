package com.s464968.gamedata.item.itemobjects;

import com.s464968.gamedata.item.ItemData;
import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.item.itemcomponents.EscapeItemComponent;
import com.s464968.gamedata.item.itemcomponents.LvlItemComponent;
import com.s464968.gamedata.item.itemcomponents.PowerItemComponent;

public class ButyOdWf extends ItemsGen {
    public ButyOdWf() {
        itemData = new ItemData(
                "Buty od WF",
                "+50% do ucieczki\n" +
                        "+1 do mocy\n" +
                        "-2 do poziomu",
                ItemType.BOOTS
        );
        itemActions.add(new EscapeItemComponent(50));
        itemActions.add(new PowerItemComponent(1));
        itemActions.add(new LvlItemComponent(-2));
    }
}
