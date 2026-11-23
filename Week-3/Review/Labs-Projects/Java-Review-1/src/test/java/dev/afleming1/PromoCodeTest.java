package dev.afleming1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PromoCodeTest {
    @Test
    public void test1() {
        Assertions.assertEquals(90.0, new PromoCode("SAVE10", 10, 0).apply(100.0), 0.001);
    }

    @Test
    public void twentyPercentComesOffAQualifyingOrder() {
        PromoCode promoCode = new PromoCode("SAVE20", 20, 50);

        double discounted = promoCode.apply(80.0);

        Assertions.assertEquals(64.0, discounted, 0.001);
    }

    @Test
    void blankCodeIsRejected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PromoCode("   ", 20, 50));
    }

    @Test
    void orderBelowMinimumIsRejectedWithAHelpfulMessage() {
        PromoCode promo = new PromoCode("SAVE20", 20, 50);

        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> promo.apply(20.0));

        Assertions.assertTrue(ex.getMessage().contains("minimum"));
    }
}
