package com.s464968.gamedata.monster;

import com.s464968.gamedata.RndObjectGen;
import com.s464968.gamedata.monster.monsterObjects.*;

public class RndMonsterGen {
    public RndMonsterGen() {
        allMonsterGens.addObject(new Automat());
        allMonsterGens.addObject(new Cyberbug());
        allMonsterGens.addObject(new KacSponiewieracz());
        allMonsterGens.addObject(new Ksiazka());
        allMonsterGens.addObject(new MalyWodz());
        allMonsterGens.addObject(new NiemocTworcza());
        allMonsterGens.addObject(new WiesminDzikiZgon());
        allMonsterGens.addObject(new ZaliczenieSysopow());
    }

    public MonstersGen getRndMonsterGens() {
        return allMonsterGens.getRndObject();
    }

    public Monster getRndMonster() {
        MonstersGen rndMonstersGen = allMonsterGens.getRndObject();
        return rndMonstersGen.getMonster();
    }

    private final RndObjectGen<MonstersGen> allMonsterGens = new RndObjectGen<>();
}
