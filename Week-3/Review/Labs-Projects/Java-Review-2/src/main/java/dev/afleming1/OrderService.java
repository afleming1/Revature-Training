package dev.afleming1;

public class OrderService {
    private final OrderRepository repository;
    private final PaymentGateway gateway;

    public OrderService(OrderRepository repository, PaymentGateway gateway) {
        this.repository = repository;
        this.gateway = gateway;
    }

    public Receipt checkout(String orderId) {
        Order order = repository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        if (!"OPEN".equals(order.getStatus())) {
            throw new IllegalStateException("Order: " + orderId + " is not open for checkout");
        }

        try {
            PaymentResult result = gateway.charge(order.getCustomerId(), order.getTotal());
            order.setStatus("PAID");
            order.setTransactionId(result.transactionId());

            repository.save(order);

            return new Receipt(order.getId(), result.transactionId(), order.getTotal());
        } catch (GatewayTimeoutException exception) {
            throw new PaymentFailedExecution("Payment failed for order: " + orderId, exception);
        }
    }

    public void cancel(String orderId) {
        Order order = repository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        if ("OPEN".equals(order.getStatus())) {
            order.setStatus("CANCELLED");

            repository.save(order);
        } else if ("PAID".equals(order.getStatus())) {
            gateway.refund(order.getTransactionId());

            order.setStatus("REFUNDED");

            repository.save(order);
        } else {
            throw new IllegalStateException("Order: " + orderId + " cannot be cancelled");
        }
    }
}
