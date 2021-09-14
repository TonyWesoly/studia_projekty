package com.s464968.gamedata.item.itemcomponents;

import com.s464968.gamedata.Player;

public class LvlItemComponent extends ItemComponent{
    public LvlItemComponent(int lvlModifier){
        this.lvlModifier = lvlModifier;
    }
    @Override
    public void use(Player player) {
        player.modifyLvl(lvlModifier);
    }

    @Override
    public void undo(Player player) {
        player.modifyLvl(lvlModifier * -1);
    }

    private int lvlModifier;
}
