package com.s464968.gamedata.monster.monsterComponents;

import com.s464968.gamedata.Player;

public class ReducePlayerLvlComponent extends LossComponent{
    public ReducePlayerLvlComponent(int lvlModifier){
        this.lvlModifier = lvlModifier;
    }
    @Override
    public void use(Player player) {
        player.modifyLvl(lvlModifier * -1);
    }
    private final int lvlModifier;
}
