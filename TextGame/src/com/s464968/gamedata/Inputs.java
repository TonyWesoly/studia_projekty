package com.s464968.gamedata;

import java.util.Scanner;

public class Inputs {
    public String getInput() {
        return inputHandler.nextLine();
    }

    private final Scanner inputHandler = new Scanner(System.in);
}
