package dev.afleming1;

public class PromoCode {
    private final String code;
    private double percentOff;
    private double minimumOrder;

    public PromoCode(String code, double percentOff, double minimumOrder) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Code cannot be null or blank");
        }

        if (percentOff < 0 || percentOff > 50) {
            throw new IllegalArgumentException("Percent off must be between 0 (exclusive) and 50 (inclusive) | Got: " + percentOff);
        }

        if (minimumOrder < 0) {
            throw new IllegalArgumentException("Minimum order cannot be negative");
        }

        this.code = code.trim().toUpperCase();
        this.percentOff = percentOff;
        this.minimumOrder = minimumOrder;
    }

    public double apply(double orderTotal) {
        if (orderTotal <= 0) {
            throw new IllegalArgumentException("Order total must be positive");
        }

        if (orderTotal <= minimumOrder) {
            throw new IllegalArgumentException("Order total is below the minimum of " + minimumOrder);
        }

        return orderTotal * (1 - percentOff / 100.0);
    }

    public Discount preview(double orderTotal) {
        double finalTotal = apply(orderTotal);

        return new Discount(code, orderTotal - finalTotal, finalTotal);
    }

    public record Discount(String code, double amountOff, double finalTotal) { }

    public String getCode() {
        return code;
    }

    public double getPercentOff() {
        return percentOff;
    }

    public double getMinimumOrder() {
        return minimumOrder;
    }
}