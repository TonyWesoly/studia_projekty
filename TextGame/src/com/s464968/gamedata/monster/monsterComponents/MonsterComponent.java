package com.s464968.gamedata.monster.monsterComponents;

import com.s464968.gamedata.Player;
import com.s464968.gamedata.monster.Monster;

public abstract class MonsterComponent {
    public abstract void use(Monster monster, Player player);
    public abstract void redo(Monster monster, Player player);
}
