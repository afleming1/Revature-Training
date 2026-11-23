package dev.afleming1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ReviewExercisesTest {
    @Test
    public void previewProducesExpectedOutput() {
        PromoCode promoCode = new PromoCode("SAVE20", 20, 100);

        PromoCode.Discount discount = promoCode.preview(200);

        Assertions.assertEquals("Discount[code=SAVE20, amountOff=40.0, finalTotal=160.0]", discount.toString());
    }

    @Test
    public void promoCodeWorks() {
        PromoCode promoCode = new PromoCode("save15", 15, 30);
        Assertions.assertEquals("SAVE15", promoCode.getCode());
        Assertions.assertEquals(15, promoCode.getPercentOff(), 0.001);
        Assertions.assertEquals(30, promoCode.getMinimumOrder(), 0.001);
        Assertions.assertEquals(85.0, promoCode.apply(100.0), 0.001);
        Assertions.assertEquals(15, promoCode.preview(100.0).amountOff(), 0.001);

        Assertions.assertThrows(IllegalArgumentException.class, () -> promoCode.apply(-1));
    }

    @Test
    public void discountMatchesFormula() {
        PromoCode promoCode = new PromoCode("FLASH", 12.5, 0);

        double actual = promoCode.apply(150.0);

        double expected = 150.0 * (1 - 12.5 / 100.0);

        Assertions.assertEquals(expected, actual, 0.001);
    }

    @Disabled("exercise 4 - enable, hand-compute, and fix")
    @Test
    public void twelvePercentComesOffAnEightyDollarOrder() {
        PromoCode promoCode = new PromoCode("SAVE12", 12, 25);

        double discounted = promoCode.apply(80.0);

        Assertions.assertEquals(70.60, discounted, 0.001);
    }
}
