package com.s464968.gamemechanics;

import com.s464968.GameData;
import com.s464968.gamedata.Player;
import com.s464968.gamedata.item.Item;
import com.s464968.gamedata.monster.Monster;
import com.s464968.graphics.Graphics;

import java.util.Random;

public class FightState extends State {
    FightState(GameData gameData) {
        super(gameData);
        monster = gameData.getRndMonster();
        player = gameData.getPlayer();
        monster.fightStarts(player);
        setChoices();
    }

    @Override
    public void updateGraphics(Graphics graphics) {
        //TODO: state w zależności od tego co powinno narysować
        drawGameStats(graphics);
        graphics.drawChoices(choices);
    }

    @Override
    public State updateData(Integer input) {
        if (input == 1) {
            String infoAfterFight = fightMonster();
            if(playerWon()){
                return new WinningState(gameData);
            }else{
                return new GameInfoState(gameData, infoAfterFight);
            }
        } else if (input == 2) {
            dataToDraw = FightDataToDraw.MONSTER;
        } else if (input == 3) {
            dataToDraw = FightDataToDraw.PLAYER;
        } else if (input == 4) {
            String infoAfterEscape = playerEscape();
            return new GameInfoState(gameData,infoAfterEscape);
        }
        return null;
    }

    @Override
    protected void setChoices() {
        choices.add("Walcz z potworem");
        choices.add("Pokaż statystyki potwora");
        choices.add("Pokaż statystyki gracza");
        choices.add("Spróbuj uciec");
    }

    private String playerEscape() {
        Random random = new Random();
        String infoAfterEscape = "";
        int randomEscapeChance = random.nextInt(100);
        if (player.getChanceToEscape() >= randomEscapeChance) {
            infoAfterEscape = "Graczowi udaję się uciec!";
        } else {
            infoAfterEscape = "Graczowi nie udaję się uciec! " +
                    monster.getLossDescription();
            monster.playerLoses(player);
        }
        monster.fightEnds(player);
        return infoAfterEscape;
    }

    private boolean isPlayerStronger() {
        int playerPower = player.getWholePlayerPower();
        int monsterPower = monster.getPower();
        return playerPower > monsterPower;
    }

    private void addItemsAfterWinning() {
        final int numberOfItemsForWinning = monster.getNumberOfItemsForWinning();
        for (int i = 0; i < numberOfItemsForWinning; i++) {
            Item itemForWinning = gameData.getRndItem();
            player.addItemToInventory(itemForWinning);
        }
    }

    private String fightMonster() {
        String infoToDrawAfterFight = "";
        if (isPlayerStronger()) {
            infoToDrawAfterFight = "Gracz wygrywa i zdobywa " +
                    monster.getNumberOfItemsForWinning() + " skarbów!";
            addItemsAfterWinning();
            player.modifyLvl(1);
        } else {
            infoToDrawAfterFight = "Gracz przegrywa!\n" +
                    monster.getLossDescription();
            monster.playerLoses(player);
        }
        monster.fightEnds(player);
        return infoToDrawAfterFight;
    }

    private void drawGameStats(Graphics graphics) {
        switch (dataToDraw) {
            case MONSTER -> graphics.drawMonsterStats(monster);
            case PLAYER -> graphics.drawPlayerStats(player);
        }
    }

    private boolean playerWon(){
        return player.getLvl() >= 10;
    }

    private Monster monster;
    private Player player;
    private FightDataToDraw dataToDraw = FightDataToDraw.MONSTER;
}

enum FightDataToDraw {
    MONSTER,
    PLAYER,
}
