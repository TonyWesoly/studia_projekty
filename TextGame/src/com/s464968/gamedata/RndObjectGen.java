package com.s464968.gamedata;


import java.util.ArrayList;
import java.util.Random;

public class RndObjectGen <T> {
    public void addObject(T newObject){
        allObjects.add(newObject);
    }
    public T getRndObject(){
        Random random = new Random();
        int randomItemIndex = random.nextInt(allObjects.size());
        return allObjects.get(randomItemIndex);
    }
    private final ArrayList<T> allObjects = new ArrayList<>();
}
