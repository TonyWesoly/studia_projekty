package com.s464968.gamedata.monster.monsterComponents;

import com.s464968.gamedata.Player;

public class KillPlayerComponent extends LossComponent{
    @Override
    public void use(Player player) {
        player.die();
    }
}
