package com.s464968.gamedata.monster.monsterObjects;

import com.s464968.gamedata.monster.MonsterData;
import com.s464968.gamedata.monster.monsterComponents.KillPlayerComponent;

public class Ksiazka extends MonstersGen{
    public Ksiazka(){
        monsterData = new MonsterData(
                "Ta jedna książka którą zapomniałeś oddać do biblioteki",
                "Należy się 300 zł gotówką",
                "Bibliotekarka poraża Cię wzrokiem. Umierasz ze wstydu." +
                        " Tracisz wszystkie przedmioty",
                6,
                1
        );
        monsterActions.addMonsterActionWhenLoss(new KillPlayerComponent());
    }
}
