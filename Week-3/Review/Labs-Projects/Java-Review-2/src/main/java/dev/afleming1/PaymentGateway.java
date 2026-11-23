package dev.afleming1;

public interface PaymentGateway {
    PaymentResult charge(String customerId, double amount);

    PaymentResult refund(String transactionId);
}
