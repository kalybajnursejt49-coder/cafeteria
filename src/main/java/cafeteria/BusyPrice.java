package cafeteria;

public class BusyPrice implements PricingStrategy {
    private final DemandSource demandSource;

    public BusyPrice(DemandSource demandSource) {
        this.demandSource = demandSource;
    }

    public int calculate(Order order) {
        int total = order.getFoodPrice() + order.getDrinkPrice();
        return (int) Math.round(total * demandSource.getMultiplier());
    }
}
