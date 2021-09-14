package com.s464968.gamedata.item;

import com.s464968.gamedata.Player;
import com.s464968.gamedata.RndObjectGen;
import com.s464968.gamedata.item.itemobjects.*;

public class RndItemsGen {
    public RndItemsGen() {
        allItemsGen.addObject(new ButyOdWf());
        allItemsGen.addObject(new Ciupaga());
        allItemsGen.addObject(new CzapkaZCekinami());
        allItemsGen.addObject(new KulaUNogi());
        allItemsGen.addObject(new PlotArmor());
        allItemsGen.addObject(new Przepis());
        allItemsGen.addObject(new StylowaCzapeczkaOrigami());
        allItemsGen.addObject(new Usos());
//        allItemsGen.addObject(new ButyTylkokopania());
//        allItemsGen.addObject(new RekawiceNaizdupa());
    }

    public ItemsGen getRndItemGen() {
        return allItemsGen.getRndObject();
    }

    public Item getRndItem(Player player) {
        ItemsGen rndItemsGen = allItemsGen.getRndObject();
        return rndItemsGen.getItem(player);
    }

    private final RndObjectGen<ItemsGen> allItemsGen = new RndObjectGen<>();
}
