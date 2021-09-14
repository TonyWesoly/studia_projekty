package com.s464968.graphics;

public class Colors {
    public static String red() {
        return RED;
    }

    public static String blue() {
        return BLUE;
    }

    public static String green() {
        return GREEN;
    }

    public static String resetColor() {
        return RESET_COLOR;
    }

    public static String cyan() {
        return CYAN;
    }

    public static String yellow(){
        return YELLOW;
    }

    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET_COLOR = "\u001B[0m";
}
