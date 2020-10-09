package com.example.recyclerviewapplication;

public class Animal {

    private String name;
    private int imageID;

    public Animal(String name, int imageID){
        this.name = name;
        this.imageID = imageID;
    }

    public String getName(){
        return name;
    }

    public int getImageID(){
        return imageID;
    }
}
