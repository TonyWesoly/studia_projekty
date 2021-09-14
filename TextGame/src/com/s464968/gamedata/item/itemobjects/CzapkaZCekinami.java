package com.s464968.gamedata.item.itemobjects;

import com.s464968.gamedata.item.ItemData;
import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.item.itemcomponents.EscapeItemComponent;
import com.s464968.gamedata.item.itemcomponents.LvlItemComponent;
import com.s464968.gamedata.item.itemcomponents.PowerItemComponent;

public class CzapkaZCekinami extends ItemsGen{
    public CzapkaZCekinami(){
        itemData = new ItemData(
                "Czapka z cekinami",
                "+1 do poziomu\n"+
                        "+3 do mocy\n"+
                        "-30% do ucieczki (cekiny robią dzyń-dzyń)",
                ItemType.HELM
        );
        itemActions.add(new PowerItemComponent(3));
        itemActions.add(new LvlItemComponent(1));
        itemActions.add(new EscapeItemComponent(-30));
    }
}
