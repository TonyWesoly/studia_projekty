package com.s464968.gamedata.item.itemcomponents;

import com.s464968.gamedata.Player;

public class PowerItemComponent extends ItemComponent {
    public PowerItemComponent(int power) {
        this.powerModifier = power;
    }

    public void use(Player player) {
        player.modifyItemPower(powerModifier);
    }

    public void undo(Player player) {
        player.modifyItemPower(powerModifier * -1);
    }

    private int powerModifier;
}
