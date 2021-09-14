package com.s464968.gamedata.monster.monsterComponents;

import com.s464968.gamedata.Player;
import com.s464968.gamedata.monster.Monster;

public class ModifyPlayerEscapeComponent extends MonsterComponent{
    public ModifyPlayerEscapeComponent(int escapeModifier){
        this.escapeModifier = escapeModifier;
    }
    @Override
    public void use(Monster monster, Player player) {
        player.modifyChanceToEscape(escapeModifier);
    }

    @Override
    public void redo(Monster monster, Player player) {
        player.modifyChanceToEscape(escapeModifier * -1);
    }
    private final int escapeModifier;
}
