package com.s464968.gamemechanics;

import com.s464968.GameData;
import com.s464968.gamedata.Player;
import com.s464968.graphics.Graphics;

public class WinningState extends State{
    public WinningState(GameData gameData){
        super(gameData);
        Player player = gameData.getPlayer();
        player.resetPlayer();
        winningMessage = "Brawo udało Ci się wygrać moją mało grę! Gratulację!";
        setChoices();
    }

    @Override
    public void updateGraphics(Graphics graphics) {
        graphics.drawLongInfo(winningMessage);
        graphics.drawChoices(choices);
    }

    @Override
    public State updateData(Integer input) {
        if(input == 1){
            return new MenuState(gameData);
        }
        return null;
    }

    @Override
    protected void setChoices() {
        choices.add("Wróć do menu");
    }
    private final String winningMessage;
}
