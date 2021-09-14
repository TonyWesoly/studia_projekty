package com.s464968;

import com.s464968.gamedata.Player;
import com.s464968.gamedata.item.Item;
import com.s464968.gamedata.item.RndItemsGen;
import com.s464968.gamedata.monster.Monster;
import com.s464968.gamedata.monster.RndMonsterGen;

public class GameData {
    public Player getPlayer() {
        return player;
    }

    public Monster getRndMonster(){
        return monsterGen.getRndMonster();
    }

    public Item getRndItem(){
        return itemsGen.getRndItem(player);
    }

    private final Player player = new Player();
    private final RndMonsterGen monsterGen = new RndMonsterGen();
    private final RndItemsGen itemsGen = new RndItemsGen();
}
