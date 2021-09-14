package com.s464968;

import com.s464968.gamedata.Inputs;
import com.s464968.gamemechanics.MenuState;
import com.s464968.gamemechanics.State;
import com.s464968.graphics.Graphics;

public class Game {
    public void play() {
        gameState.updateGraphics(graphics);
        int optionSelectedByUser = getSelectedOption();
        State newState = gameState.updateData(optionSelectedByUser);
        if(newState != null){
            gameState = newState;
        }
    }
    private int getSelectedOption(){
        String playerInput = "";
        boolean isInputCorrect = true;
        int selectedOption = 0;
        do{
            isInputCorrect = true;
            playerInput = inputs.getInput();
            selectedOption = getUnsignedIntWithoutException(playerInput);
            if(selectedOption < 1 || selectedOption > gameState.choicesSize()){
                isInputCorrect = false;
                graphics.drawError("Błędne dane! Podaj dane jeszcze raz:");
            }
        }while(!isInputCorrect);
        return selectedOption;
    }
    private int getUnsignedIntWithoutException(String input){
        int result = 0;
        try{
            result = Integer.parseUnsignedInt(input);
        } catch (NumberFormatException nfe){
            result = -1;
        }
        return result;
    }
    private final GameData gameData = new GameData();
    private final Inputs inputs = new Inputs();
    private final Graphics graphics = new Graphics();
    private State gameState = new MenuState(gameData);
}
