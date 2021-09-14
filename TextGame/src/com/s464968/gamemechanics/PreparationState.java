package com.s464968.gamemechanics;

import com.s464968.GameData;
import com.s464968.gamedata.Equipment;
import com.s464968.gamedata.Inventory;
import com.s464968.gamedata.Player;
import com.s464968.graphics.Graphics;

public class PreparationState extends State {
    public PreparationState(GameData gameData) {
        super(gameData);
        playerEquipment = gameData.getPlayer().getEquipment();
        playerInventory = gameData.getPlayer().getInventory();
        player = gameData.getPlayer();
        setChoices();
    }

    @Override
    public State updateData(Integer input) {
        if(input==1){
            return new FightState(gameData);
        }else if(input == 2){
            return new EquipmentState(gameData);
        }else if(input == 3){
            return new InventoryState(gameData);
        }else if(input == 4){
            isPlayerStatsDisplayed = true;
        }
        return null;
    }

    @Override
    public void updateGraphics(Graphics graphics) {
        displayPlayerStats(graphics);
//        graphics.drawEquipment(playerEquipment.getEquipmentMenu());
//        graphics.drawInventory(playerInventory.getItemsNames(),playerInventory.getMaxCapacity());
        graphics.drawChoices(choices);
    }

    @Override
    protected void setChoices() {
        choices.add("Zacznij kolejną walkę");
        choices.add("Przejdź do ekwipunku (założonych przedmiotów)");
        choices.add("Przejdź do plecaka");
        choices.add("Wyświetl statystyki gracza");
    }
    private void displayPlayerStats(Graphics graphics){
        if(isPlayerStatsDisplayed){
            graphics.drawPlayerStats(player);
        }
        isPlayerStatsDisplayed = false;
    }
    private final Equipment playerEquipment;
    private final Inventory playerInventory;
    private final Player player;
    boolean isPlayerStatsDisplayed = false;
}