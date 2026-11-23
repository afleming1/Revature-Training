package dev.afleming1;

import java.util.concurrent.atomic.AtomicInteger;

public class FakePaymentGateway implements PaymentGateway {
    private final AtomicInteger sequence = new AtomicInteger(1000);

    @Override
    public PaymentResult charge(String customerId, double amount) {
        return new PaymentResult("TX-" + sequence.incrementAndGet());
    }

    @Override
    public PaymentResult refund(String transactionId) {
        return new PaymentResult("RF-" + sequence.incrementAndGet());
    }
}
