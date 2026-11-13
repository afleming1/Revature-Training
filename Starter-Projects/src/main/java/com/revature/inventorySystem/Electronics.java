package com.revature.inventorySystem;

public class Electronics implements Item {
    private String name;
    private double price;

    public Electronics(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double price() {
        return price;
    }
}
