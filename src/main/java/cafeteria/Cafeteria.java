package cafeteria;

public class Cafeteria {
    private final DemandSource demandSource;

    public Cafeteria(DemandSource demandSource) {
        this.demandSource = demandSource;
    }

    public int priceFor(Order order) {
        PriceSelector selector = new PriceSelector(demandSource);
        PricingContext context = new PricingContext(selector.select(order));
        return context.calculate(order);
    }
}
