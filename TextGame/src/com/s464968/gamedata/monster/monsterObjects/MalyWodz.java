package com.s464968.gamedata.monster.monsterObjects;

import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.monster.MonsterData;
import com.s464968.gamedata.monster.monsterComponents.RemoveItemComponent;

public class MalyWodz extends MonstersGen{
    public MalyWodz(){
        monsterData = new MonsterData(
                "Mały wódz \"wielkiego niepokoju\"",
                "-Ależ wodzu co wódz...\n"+
                        "-To ja przepraszam",
                "Wyruszasz na poszukiwania wody sodowej," +
                        " potczas której natrafiasz na złodziejskie małpy. Tracisz buty",
                3,
                1
        );
        monsterActions.addMonsterActionWhenLoss(new RemoveItemComponent(ItemType.BOOTS));
    }
}
