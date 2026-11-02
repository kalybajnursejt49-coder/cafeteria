package cafeteria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class StrategyTest {
    private final Order order = new Order("Ali", 50, 20, 1, 8, 25);
    private final List<Order> orders = Arrays.asList(
            new Order("Ali", 50, 20, 1, 8, 25),
            new Order("Sara", 40, 0, 3, 3, 5),
            new Order("Bek", 30, 10, 2, 4, 15));

    @Test
    void regularPrice() {
        assertEquals(70, new RegularPrice().calculate(order));
    }

    @Test
    void comboPrice() {
        assertEquals(60, new ComboPrice().calculate(order));
    }

    @Test
    void busyPriceUsesFakeDemandSource() {
        DemandSource fake = () -> 1.5;
        assertEquals(105, new BusyPrice(fake).calculate(order));
    }

    @Test
    void oneCookPreparation() {
        assertEquals(15, new OneCookPreparation().minutesFor(orders));
    }

    @Test
    void twoCooksPreparation() {
        assertEquals(12, new TwoCooksPreparation().minutesFor(orders));
    }

    @Test
    void batchPreparation() {
        assertEquals(14, new BatchPreparation().minutesFor(orders));
    }

    @Test
    void selectorChoosesBusyPrice() {
        assertTrue(new PriceSelector(() -> 1.5).select(order) instanceof BusyPrice);
    }

    @Test
    void selectorChoosesComboPrice() {
        assertTrue(new PriceSelector(() -> 1.0).select(order) instanceof ComboPrice);
    }

    @Test
    void selectorChoosesRegularPrice() {
        Order foodOnly = new Order("Ali", 50, 0, 1, 8, 25);
        assertTrue(new PriceSelector(() -> 1.0).select(foodOnly) instanceof RegularPrice);
    }

    @Test
    void selectorChoosesBatchPreparation() {
        assertTrue(new PreparationSelector().select(true, true) instanceof BatchPreparation);
    }

    @Test
    void selectorChoosesTwoCooksPreparation() {
        assertTrue(new PreparationSelector().select(false, true) instanceof TwoCooksPreparation);
    }

    @Test
    void selectorChoosesOneCookPreparation() {
        assertTrue(new PreparationSelector().select(false, false) instanceof OneCookPreparation);
    }

    @Test
    void pricingContextSwitchesAtRuntime() {
        PricingContext context = new PricingContext(new RegularPrice());
        assertEquals(70, context.calculate(order));
        context.setStrategy(new ComboPrice());
        assertEquals(60, context.calculate(order));
    }

    @Test
    void preparationContextSwitchesAtRuntime() {
        PreparationContext context = new PreparationContext(new OneCookPreparation());
        assertEquals(15, context.minutesFor(orders));
        context.setStrategy(new TwoCooksPreparation());
        assertEquals(12, context.minutesFor(orders));
    }

    @ParameterizedTest
    @MethodSource("pricingStrategies")
    void pricesAreNeverNegative(PricingStrategy strategy) {
        assertTrue(strategy.calculate(order) >= 0);
    }

    static Stream<PricingStrategy> pricingStrategies() {
        return Stream.of(new RegularPrice(), new ComboPrice(), new BusyPrice(() -> 1.5));
    }
}
