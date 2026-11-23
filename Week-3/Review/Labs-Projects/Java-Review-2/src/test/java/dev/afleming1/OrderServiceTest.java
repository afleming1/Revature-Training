package dev.afleming1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    OrderRepository repository;

    @Mock
    PaymentGateway gateway;

    @InjectMocks
    OrderService service;

    @Test
    public void checkoutChargesAndReturnsAReceipt() {
        Order order = new Order("A-1", "cust-42", 120.00, "OPEN");

        Mockito.when(repository.findById("A-1")).thenReturn(Optional.of(order));
        Mockito.when(gateway.charge("cust-42", 120.00)).thenReturn(new PaymentResult("TX-9"));

        Receipt receipt = service.checkout("A-1");

        Assertions.assertAll("receipt",
                () -> Assertions.assertEquals("A-1", receipt.orderId()),
                () -> Assertions.assertEquals("TX-9", receipt.transactionId()),
                () -> Assertions.assertEquals(120.00, receipt.amountCharged(), 0.001));

        Mockito.verify(repository).save(Mockito.argThat(saved -> "PAID".equals(saved.getStatus()) && "TX-9".equals(saved.getTransactionId())));
    }

    @Test
    public void gatewayTimeoutBecomesPaymentFailedAndNothingIsSaved() {
        Order order = new Order("A-1", "cust-42", 120.00, "OPEN");

        Mockito.when(repository.findById("A-1")).thenReturn(Optional.of(order));
        Mockito.when(gateway.charge(ArgumentMatchers.anyString(), ArgumentMatchers.anyDouble())).thenThrow(new GatewayTimeoutException("No Answer"));

        PaymentFailedExecution execution = Assertions.assertThrows(PaymentFailedExecution.class, () -> service.checkout("A-1"));

        Assertions.assertEquals(GatewayTimeoutException.class, execution.getCause().getClass());

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void nonOpenOrderIsRefusedAndNeverCharged() {
        Order shipped = new Order("A-2", "cust-42", 60, "SHIPPED");

        Mockito.when(repository.findById("A-2")).thenReturn(Optional.of(shipped));

        Assertions.assertThrows(IllegalStateException.class, () -> service.checkout("A-2"));

        Mockito.verify(gateway, Mockito.never()).charge(Mockito.anyString(), Mockito.anyDouble());
    }

    @Test
    public void checkoutPersistsThePaidOrderIntact() {
        Order order = new Order("A-1", "cust-42", 120.00, "OPEN");

        Mockito.when(repository.findById("A-1")).thenReturn(Optional.of(order));
        Mockito.when(gateway.charge("cust-42", 120.00)).thenReturn(new PaymentResult("TX-9"));

        service.checkout("A-1");

        ArgumentCaptor<Order> savedOrder = ArgumentCaptor.forClass(Order.class);

        Order saved = savedOrder.getValue();

        Assertions.assertAll("persisted order",
                () -> Assertions.assertEquals("PAID", saved.getStatus()),
                () -> Assertions.assertEquals("TX-9", saved.getTransactionId()),
                () -> Assertions.assertEquals(120.00, saved.getTotal(), 0.001),
                () -> Assertions.assertEquals("cust-42", saved.getCustomerId()));
    }
}
