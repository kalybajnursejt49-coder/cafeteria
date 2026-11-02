package cafeteria;

public class Cafeteria {
    private final DemandSource demandSource;

    public Cafeteria(DemandSource demandSource) {
        this.demandSource = demandSource;
    }

    public int priceFor(Order order) {
        int total = order.getFoodPrice() + order.getDrinkPrice();
        if (demandSource.getMultiplier() > 1.2) {
            return (int) Math.round(total * demandSource.getMultiplier());
        } else if (order.getFoodPrice() > 0 && order.getDrinkPrice() > 0) {
            return total - Math.min(order.getDrinkPrice(), order.getFoodPrice() / 5);
        } else {
            return total;
        }
    }
}
