package com.s464968.gamedata.item.itemobjects;

import com.s464968.gamedata.item.ItemData;
import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.item.itemcomponents.EscapeItemComponent;
import com.s464968.gamedata.item.itemcomponents.LvlItemComponent;
import com.s464968.gamedata.item.itemcomponents.PowerItemComponent;

public class PlotArmor extends ItemsGen{
    public PlotArmor(){
        itemData = new ItemData(
                "Plot armor",
                "+6 do mocy\n"+
                        "-20% do ucieczki\n"+
                        "-1 do poziomu",
                ItemType.ARMOR
        );
        itemActions.add(new PowerItemComponent(6));
        itemActions.add(new EscapeItemComponent(-20));
        itemActions.add(new LvlItemComponent(-1));
    }
}
