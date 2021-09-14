package com.s464968.graphics;

public class DrawSimpleData {
    public void drawSeparator(){
        drawColorString("---------------",Colors.yellow());
    }

    public void drawName(String name){
        drawColorString(name, Colors.green());
    }

    public void drawDescription(String description){
        drawColorString(description,Colors.green());
    }

    public void drawPower(String powerInfo){
        drawColorString(powerInfo,Colors.blue());
    }

    public void drawLvl(String lvlInfo){
        drawColorString(lvlInfo,Colors.red());
    }

    public void drawInfo(String info){
        drawColorString(info,Colors.resetColor());
    }

    public void drawChoice(String choice){
        drawColorString(choice,Colors.resetColor());
    }

    public void drawError(String error){drawColorString(error,Colors.red());}

    public void drawAlert(String alert){
        drawColorString(alert,Colors.red());
    }

    private void drawColorString(String toDraw, String color) {
        System.out.println(color + toDraw + Colors.resetColor());
    }
}
