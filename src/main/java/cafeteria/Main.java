package cafeteria;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Order ali = new Order("Ali", 50, 20, 1, 8, 25);
        Order sara = new Order("Sara", 40, 0, 3, 3, 5);
        Order bek = new Order("Bek", 30, 10, 2, 4, 15);
        List<Order> orders = Arrays.asList(ali, sara, bek);
        DemandSource demand = new FixedDemandSource(1.5);

        PricingContext pricing = new PricingContext(new RegularPrice());
        System.out.println("Prices for the same order:");
        for (PricingStrategy strategy : Arrays.asList(new RegularPrice(), new ComboPrice(), new BusyPrice(demand))) {
            pricing.setStrategy(strategy);
            System.out.println(strategy.getClass().getSimpleName() + ": " + pricing.calculate(ali));
        }
        pricing.setStrategy(new ComboPrice());
        System.out.println("Manual price: " + pricing.calculate(ali));
        pricing.setStrategy(new PriceSelector(demand).select(ali));
        System.out.println("Automatic price: " + pricing.calculate(ali));

        PreparationContext preparation = new PreparationContext(new OneCookPreparation());
        System.out.println("Preparation time for the same orders:");
        for (PreparationStrategy strategy : Arrays.asList(new OneCookPreparation(), new TwoCooksPreparation(), new BatchPreparation())) {
            preparation.setStrategy(strategy);
            System.out.println(strategy.getClass().getSimpleName() + ": " + preparation.minutesFor(orders));
        }
        preparation.setStrategy(new TwoCooksPreparation());
        System.out.println("Manual preparation: " + preparation.minutesFor(orders));
        preparation.setStrategy(new PreparationSelector().select(true, false));
        System.out.println("Automatic preparation: " + preparation.minutesFor(orders));
    }
}
