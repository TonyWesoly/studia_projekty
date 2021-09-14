package com.s464968.gamedata.monster.monsterObjects;

import com.s464968.gamedata.monster.MonsterData;
import com.s464968.gamedata.monster.monsterComponents.ModifyPlayerEscapeComponent;
import com.s464968.gamedata.monster.monsterComponents.ReducePlayerLvlComponent;

public class ZaliczenieSysopow extends MonstersGen{
    public ZaliczenieSysopow(){
        monsterData = new MonsterData(
                "Zaliczenie sysopów",
                "Legendy powiadają że tylko 8 (osiem) osób przeżyło spotkanie z nim\n"+
                "+20% do ucieczki podczas walki",
                "Musisz pisać to. Znowu. Tracisz 3 poziomy",
                20,
                3
        );
        monsterActions.addMonsterActionWhenLoss(new ReducePlayerLvlComponent(3));
        monsterActions.addMonsterActionWhenFight(new ModifyPlayerEscapeComponent(20));
    }
}
