package dev.afleming1;

public class Main {
    public static void main(String[] args) {
        OrderRepository repository = new InMemoryOrderRepository();
        PaymentGateway gateway = new FakePaymentGateway();
        OrderService service = new OrderService(repository, gateway);

        repository.save(new Order("A-1", "cust-42", 120.00, "OPEN"));

        Receipt receipt = service.checkout("A-1");

        System.out.println("Receipt: " + receipt);
        System.out.println("Order after checkout: " + repository.findById("A-1").orElseThrow());
    }
}
