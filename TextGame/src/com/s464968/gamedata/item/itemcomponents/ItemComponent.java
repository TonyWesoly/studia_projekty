package com.s464968.gamedata.item.itemcomponents;

import com.s464968.gamedata.Player;

public abstract class ItemComponent {
    public abstract void use(Player player);
    public abstract void undo(Player player);
}
