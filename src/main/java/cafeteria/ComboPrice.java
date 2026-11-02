package cafeteria;

public class ComboPrice implements PricingStrategy {
    public int calculate(Order order) {
        int total = order.getFoodPrice() + order.getDrinkPrice();
        int discount = Math.min(order.getDrinkPrice(), order.getFoodPrice() / 5);
        return total - discount;
    }
}
