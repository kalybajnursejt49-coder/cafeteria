package cafeteria;

import java.util.List;

public class BatchPreparation implements PreparationStrategy {
    public int minutesFor(List<Order> orders) {
        int longest = 0;
        for (Order order : orders) {
            longest = Math.max(longest, order.getPreparationMinutes());
        }
        return longest + orders.size() * 2;
    }
}
