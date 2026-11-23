package dev.afleming1;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String orderId) {
        super("No Such Order: " + orderId);
    }
}
