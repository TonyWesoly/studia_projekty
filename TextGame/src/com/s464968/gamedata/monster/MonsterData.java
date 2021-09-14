package com.s464968.gamedata.monster;

public class MonsterData {
    public MonsterData(String name,
                       String desc,
                       String onPlayersLoss,
                       int power,
                       int numberOfItemsForWinning) {
        this.name = name;
        this.description = desc;
        this.lossDescription = onPlayersLoss;
        this.power = power;
        this.numberOfItemsForWinning = numberOfItemsForWinning;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLossDescription() {
        return lossDescription;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getNumberOfItemsForWinning() {
        return numberOfItemsForWinning;
    }

    private final String name;
    private final String description;
    private final String lossDescription;
    private int power;
    private int numberOfItemsForWinning;
}
