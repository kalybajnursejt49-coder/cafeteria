package cafeteria;

import java.util.List;

public class PreparationContext {
    private PreparationStrategy strategy;

    public PreparationContext(PreparationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PreparationStrategy strategy) {
        this.strategy = strategy;
    }

    public int minutesFor(List<Order> orders) {
        return strategy.minutesFor(orders);
    }
}
