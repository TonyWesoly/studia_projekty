package com.s464968.gamedata.item.itemcomponents;

import com.s464968.gamedata.Player;

public class EscapeItemComponent extends ItemComponent {
    public EscapeItemComponent(int escapeModifier){
        this.escapeModifier = escapeModifier;
    }
    @Override
    public void use(Player player) {
        player.modifyChanceToEscape(escapeModifier);
    }

    @Override
    public void undo(Player player) {
        player.modifyChanceToEscape(escapeModifier * -1);
    }
    private int escapeModifier;
}
