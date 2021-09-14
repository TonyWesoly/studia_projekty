package com.s464968.gamemechanics;

import com.s464968.GameData;
import com.s464968.graphics.Graphics;

public class GameInfoState extends State{
    GameInfoState(GameData gameData,String infoToDraw){
        super(gameData);
        this.infoToDraw = infoToDraw;
        setChoices();
//        super.choices = new String[]{
//                "Przejdź dalej"
//        };
    }
    @Override
    public State updateData(Integer input) {
        if(input == 1){
            return new PreparationState(gameData);
        }
        return null;
    }

    @Override
    public void updateGraphics(Graphics graphics) {
        //TODO: info na początek
        graphics.drawLongInfo(infoToDraw);
//        graphics.drawInfo("Witaj! Info na początel itp. nwm teraz nie mam na to pomysłu");
        graphics.drawChoices(choices);
    }

    @Override
    protected void setChoices() {
        choices.add("Przejdź dalej");
    }
    final private String infoToDraw;
}
