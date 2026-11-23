package dev.afleming1;

public class PaymentFailedExecution extends RuntimeException {
    public PaymentFailedExecution(String message, Throwable cause) {
        super(message, cause);
    }
}
