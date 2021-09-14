package com.s464968.gamedata.monster.monsterObjects;

import com.s464968.gamedata.monster.MonsterData;
import com.s464968.gamedata.monster.monsterComponents.ReducePlayerLvlComponent;

public class NiemocTworcza extends MonstersGen{
    public NiemocTworcza(){
        monsterData = new MonsterData(
                "Niemoc twórcza",
                "Dopadła autora. W tym momencie",
                "Przegrywasz z deadlinem. Tracisz 1 poziom",
                10,
                2
        );
        monsterActions.addMonsterActionWhenLoss(new ReducePlayerLvlComponent(1));
    }
}
