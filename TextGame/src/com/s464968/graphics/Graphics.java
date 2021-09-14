package com.s464968.graphics;

import com.s464968.gamedata.Player;
import com.s464968.gamedata.item.Item;
import com.s464968.gamedata.item.ItemType;
import com.s464968.gamedata.monster.Monster;

import java.util.ArrayList;

//TODO: rożne kolory tła dla potwora, gracz itp
public class Graphics {
    //    public void DrawInfo()
    public void drawPlayerStats(Player player) {
        final int playerLvlString = player.getLvl();
        final int playerItemPower = player.getItemsPower();
//        final int playerWholePowerString = player.getWholePlayerPower();
        final int playerChanceToEscape = player.getChanceToEscape();
        draw.drawLvl("Poziom gracza: "+playerLvlString);
        draw.drawPower("Łączna moc przedmiotów: "+playerItemPower);
//        draw.drawPower("Całkowita moc gracza: "+playerWholePowerString);
        draw.drawInfo("Szansa na ucieczkę: "+ playerChanceToEscape + "%");
        draw.drawSeparator();
    }

    public void drawEquipment(ArrayList<String> itemsMenu){
        drawShortInfo("Ekwipunek:");
        for (String itemMenuName :
                itemsMenu) {
            draw.drawInfo(itemMenuName);
        }
        draw.drawSeparator();
    }

    public void drawInventory(ArrayList<String> itemsInInventoryNames, int maxInventoryCapacity){
        drawShortInfo("Przedmioty w plecaku:");
        for (int i =0; i<itemsInInventoryNames.size();i++){
            drawShortInfo(i+1+": "+itemsInInventoryNames.get(i));
        }
        if(itemsInInventoryNames.size()==0){
            drawShortInfo("Brak przedmitów w plecaku!");
        }
        drawShortInfo("Maksymalna ilość przedmiotów w plecaku: "+maxInventoryCapacity);
        draw.drawSeparator();
    }

    public void drawItem(Item item){
        draw.drawName(item.getName());
        drawShortInfo(item.getDescription());
        String itemTypeName = getItemTypeName(item.getItemType());
        drawShortInfo("Typ: "+itemTypeName);
        draw.drawSeparator();
    }

//    private String changeNullItemWithString(String itemName){
//        String resultName;
//        if(itemName == null){
//            resultName = "brak";
//        }else{
//            resultName = itemName;
//        }
//        return resultName;
//    }
//
    public void drawShortInfo(String info) {
        draw.drawInfo(info);
    }

    public void drawLongInfo(String info){
        draw.drawInfo(info);
        draw.drawSeparator();
    }

    public void drawChoices(ArrayList<String> choices) {
        for (int i = 0; i < choices.size(); i++) {
            draw.drawChoice((i + 1) + ": " + choices.get(i));
        }
        draw.drawSeparator();
    }

    public void drawMonsterStats(Monster monster){
        draw.drawName("Nazwa: "+monster.getName());
        draw.drawDescription("Opis: "+monster.getDescription());
        final String monster_power = monster.getPower().toString();
        draw.drawPower("Siła: "+monster_power);
        draw.drawAlert("Efekt przegranej: "+monster.getLossDescription());
        draw.drawInfo("Ilość przedmiotów za pokonanie: "+monster.getNumberOfItemsForWinning());
        draw.drawSeparator();
    }

    public void drawError(String error){
        draw.drawError(error);
        draw.drawSeparator();
    }

    private String getItemTypeName(ItemType itemType){
        String itemTypeName = "";
        switch (itemType){
            case BOOTS -> itemTypeName = "Buty";
            case ARMOR -> itemTypeName = "Zbroja";
            case HELM -> itemTypeName = "Hełm";
            case WEAPON -> itemTypeName = "Broń";
        }
        return itemTypeName;
    }

    private final DrawSimpleData draw = new DrawSimpleData();
}
