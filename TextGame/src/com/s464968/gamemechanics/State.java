package com.s464968.gamemechanics;

import com.s464968.GameData;
import com.s464968.graphics.Graphics;

import java.util.ArrayList;

public abstract class State {
    protected State(GameData gameData){
        this.gameData = gameData;
    }
    public abstract void updateGraphics(Graphics graphics);
    public abstract State updateData(Integer input);
    public int choicesSize(){
        return choices.size();
    }
    protected abstract void setChoices();
    protected ArrayList<String> choices = new ArrayList<>();
    protected GameData gameData;
}
//TODO: zaimplementować w wszystkich klasach setChoices i choices jako ArrayList
