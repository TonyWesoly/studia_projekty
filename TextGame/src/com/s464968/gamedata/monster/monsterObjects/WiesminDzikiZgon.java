package com.s464968.gamedata.monster.monsterObjects;

import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.monster.MonsterData;
import com.s464968.gamedata.monster.monsterComponents.RemoveItemComponent;

public class WiesminDzikiZgon extends MonstersGen{
    public WiesminDzikiZgon(){
        monsterData = new MonsterData(
                "Wieśmin dziki zgon",
                "Jeden miecz na potwory, drugi na ludzi. Na dziekana używa dwóch",
                "Grosza daj wieśminowi. Chociaż zbroja też się nada. Tracisz zbroje",
                6,
                1
        );
        monsterActions.addMonsterActionWhenLoss(new RemoveItemComponent(ItemType.ARMOR));
    }
}
