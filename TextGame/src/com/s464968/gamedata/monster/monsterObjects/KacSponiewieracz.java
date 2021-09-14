package com.s464968.gamedata.monster.monsterObjects;

import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.monster.MonsterData;
import com.s464968.gamedata.monster.monsterComponents.RemoveItemComponent;

public class KacSponiewieracz extends MonstersGen{
    public KacSponiewieracz(){
        monsterData = new MonsterData(
                "Kac sponiewieracz",
                "Częsta przypadłość wykładowców i studentów w poniedziałkowe poranki",
                "Nawet najlepsze pranie nie pomoże. Tracisz hełm",
                8,
                1
        );
        monsterActions.addMonsterActionWhenLoss(new RemoveItemComponent(ItemType.HELM));
    }
}
