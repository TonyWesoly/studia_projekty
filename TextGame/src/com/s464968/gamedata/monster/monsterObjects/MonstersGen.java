package com.s464968.gamedata.monster.monsterObjects;

import com.s464968.gamedata.monster.Monster;
import com.s464968.gamedata.monster.MonsterActions;
import com.s464968.gamedata.monster.MonsterData;

public abstract class MonstersGen {
    public Monster getMonster(){
        return new Monster(monsterData,monsterActions);
    }
    protected MonsterData monsterData;
    protected final MonsterActions monsterActions = new MonsterActions();
}
