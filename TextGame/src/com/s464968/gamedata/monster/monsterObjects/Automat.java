package com.s464968.gamedata.monster.monsterObjects;

import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.monster.MonsterData;
import com.s464968.gamedata.monster.monsterComponents.ModifyPlayerEscapeComponent;
import com.s464968.gamedata.monster.monsterComponents.RemoveItemComponent;

public class Automat extends MonstersGen{
    public Automat(){
        monsterData = new MonsterData(
                "Automat pożerający monety",
                "Woźny od 5 lat powtarza że już jutro go naprawi\n" +
                        "+10% do ucieczki podczas walki",
                "Pożera Ci broń. Tracisz broń. Ciesz się że nie rękę",
                15,
                3
        );
        monsterActions.addMonsterActionWhenLoss(new RemoveItemComponent(ItemType.WEAPON));
        monsterActions.addMonsterActionWhenFight(new ModifyPlayerEscapeComponent(10));
    }
}
