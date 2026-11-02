package cafeteria;

public class PriceSelector {
    private final DemandSource demandSource;

    public PriceSelector(DemandSource demandSource) {
        this.demandSource = demandSource;
    }

    public PricingStrategy select(Order order) {
        if (demandSource.getMultiplier() > 1.2) {
            return new BusyPrice(demandSource);
        }
        if (order.getFoodPrice() > 0 && order.getDrinkPrice() > 0) {
            return new ComboPrice();
        }
        return new RegularPrice();
    }
}
