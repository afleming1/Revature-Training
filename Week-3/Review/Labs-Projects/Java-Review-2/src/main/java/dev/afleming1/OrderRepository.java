package dev.afleming1;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(String orderId);

    Order save(Order order);
}
