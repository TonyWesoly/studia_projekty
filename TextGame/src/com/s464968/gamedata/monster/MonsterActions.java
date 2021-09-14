package com.s464968.gamedata.monster;

import com.s464968.gamedata.Player;
import com.s464968.gamedata.monster.monsterComponents.LossComponent;
import com.s464968.gamedata.monster.monsterComponents.MonsterComponent;

import java.util.ArrayList;

public class MonsterActions {
    public void fightStarts(Monster monster, Player player) {
        for (MonsterComponent componentOnStart :
                monsterActionsWhenFight) {
            componentOnStart.use(monster, player);
        }
    }

    public void fightEnds(Monster monster, Player player) {
        for (MonsterComponent componentOnEnd :
                monsterActionsWhenFight) {
            componentOnEnd.redo(monster, player);
        }
    }

    public void playerLoses(Player player) {
        for (LossComponent lossComponent :
                monsterActionsWhenLoss) {
            lossComponent.use(player);
        }
    }

    public void addMonsterActionWhenFight(MonsterComponent monsterAction){
        monsterActionsWhenFight.add(monsterAction);
    }

    public void addMonsterActionWhenLoss(LossComponent monsterAction){
        monsterActionsWhenLoss.add(monsterAction);
    }

    private final ArrayList<MonsterComponent> monsterActionsWhenFight = new ArrayList<>();
    private final ArrayList<LossComponent> monsterActionsWhenLoss = new ArrayList<>();
}
