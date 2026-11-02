package cafeteria;

import java.util.List;

public class OneCookPreparation implements PreparationStrategy {
    public int minutesFor(List<Order> orders) {
        int minutes = 0;
        for (Order order : orders) {
            minutes += order.getPreparationMinutes();
        }
        return minutes;
    }
}
