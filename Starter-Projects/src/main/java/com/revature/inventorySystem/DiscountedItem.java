package com.revature.inventorySystem;

public class DiscountedItem implements Item {
    private final Item wrappedItem;
    private final double discountPercentage;

    public DiscountedItem(Item wrappedItem, double discountPercentage) {
        this.wrappedItem = wrappedItem;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String name() {
        return wrappedItem.name() + " (" + discountPercentage + "% off)";
    }

    @Override
    public double price() {
        double discountMultiplier = 1 - (discountPercentage / 100.0);

        return wrappedItem.price() * discountMultiplier;
    }
}
