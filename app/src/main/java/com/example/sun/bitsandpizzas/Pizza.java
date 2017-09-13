package com.example.sun.bitsandpizzas;


public class Pizza {
    private  String name;
    private int imageResourseId;

    public static final Pizza[] pizzas ={
            new Pizza("Diavolo", R.drawable.diavolo), new Pizza("Funghi", R.drawable.funghi)
    };

    public Pizza(String name, int imageResourseId) {
        this.name = name;
        this.imageResourseId = imageResourseId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourseId() {
        return imageResourseId;
    }
}
