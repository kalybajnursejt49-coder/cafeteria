package cafeteria;

public class RegularPrice implements PricingStrategy {
    public int calculate(Order order) {
        return order.getFoodPrice() + order.getDrinkPrice();
    }
}
