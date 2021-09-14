package com.s464968.gamemechanics;

import com.s464968.GameData;
import com.s464968.gamedata.Player;
import com.s464968.graphics.Graphics;

import java.util.ArrayList;

public class MenuState extends State {

    public MenuState(GameData gameData) {
        super(gameData);
        setChoices();
    }

    public void updateGraphics(Graphics graphics) {
        graphics.drawChoices(choices);
    }

    public State updateData(Integer playerInput) {
        if (playerInput == 1) {
            initGame();
            final String infoToDraw = "Witaj w mojej (niezbyt) poważnej grze rpg (lub inczej projekcie na studia).\n" +
                    "Celen gry jest zdobycie 10 poziomu poprzez wygrywanie z przeciwnikami i zdobywanie odpowiednich przedmiotów.\n" +
                    "Uwaga, wygrać grę można jedynie pokonując potwora.\n" +
                    "Jeżeli zdobędziesz 10 poziom lub wyższy poprzez przedmiot wciąż nie wygrywasz gry.\n" +
                    "Za pokonanie potwora zdobywasz losowe przedmioty które automatycznie trafiaja do Twojego plecaka.\n" +
                    "Aby przedmiot zaczął działać musisz go zalożyć (przenieść do ekwipunku).\n" +
                    "Uwaga, maksymalna pojemność plecaka to: 3. \n" +
                    "Jeżeli wygrasz z potworem mając zapełniony plecak to tracisz przedmioty za potwora.\n" +
                    "Na start gry zdobywasz 3 losowe przedmioty do Twojego plecaka.\n" +
                    "Miłej zabawy!";
            return new GameInfoState(gameData,infoToDraw);
        } else if (playerInput == 2) {
            System.exit(0);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return null;
    }

    @Override
    protected void setChoices() {
        choices.add("Nowa gra");
        choices.add("Wyjdź z gry");
    }

    private void initGame(){
        Player player = gameData.getPlayer();
        for (int i = 0; i < 3; i++) {
            player.addItemToInventory(gameData.getRndItem());
        }
    }
}
