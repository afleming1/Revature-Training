package com.revature.inventorySystem;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Book("The Hobbit", 10.99));
        items.add(new Book("The Lord of the Rings", 29.99));
        items.add(new Electronics("Samsung Galaxy S20", 1299.99));
        items.add(new Electronics("iPhone 12 Pro", 999.99));

        items.add(new DiscountedItem(new Book("The Hunger Games", 14.99), 25));
        items.add(new DiscountedItem(new Electronics("MacBook Pro 16", 2499.99), 15));

        for (Item item : items) {
            System.out.printf("Item Name: %s | Price: $%.2f%n", item.name(), item.price());
        }

        double total = 0;
        for (Item item : items) {
            total += item.price();
        }

        System.out.printf("Total: $%.2f%n", total);
    }
}
