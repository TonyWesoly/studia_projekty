package com.s464968.gamedata.item.itemobjects;

import com.s464968.gamedata.item.ItemData;
import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.item.itemcomponents.LvlItemComponent;
import com.s464968.gamedata.item.itemcomponents.PowerItemComponent;

public class StylowaCzapeczkaOrigami extends ItemsGen{
    public StylowaCzapeczkaOrigami(){
        itemData = new ItemData(
                "Stylowa czapeczka origami",
                "+1 do poziomu\n"+
                        "+3 do mocy",
                ItemType.HELM
        );
        itemActions.add(new LvlItemComponent(1));
        itemActions.add(new PowerItemComponent(3));
    }
}
