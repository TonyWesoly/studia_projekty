package com.s464968.gamedata.monster;

import com.s464968.gamedata.Player;

public class Monster {
    public Monster(MonsterData monsterData, MonsterActions monsterActions) {
        this.monsterData = monsterData;
        this.monsterActions = monsterActions;
    }

    public String getName() {
        return monsterData.getName();
    }

    public String getDescription() {
        return monsterData.getDescription();
    }

    public Integer getPower(){
        return monsterData.getPower();
    }

    public Integer getNumberOfItemsForWinning(){
        return monsterData.getNumberOfItemsForWinning();
    }

    public String getLossDescription() {
        return monsterData.getLossDescription();
    }

    public void fightStarts(Player player){
        monsterActions.fightStarts(this,player);
    }
    public void fightEnds(Player player){
        monsterActions.fightEnds(this,player);
    }
    public void playerLoses(Player player){
        monsterActions.playerLoses(player);
    }

    protected final MonsterData monsterData;
    protected final MonsterActions monsterActions;
}
