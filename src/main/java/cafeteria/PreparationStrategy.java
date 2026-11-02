package cafeteria;

import java.util.List;

public interface PreparationStrategy {
    int minutesFor(List<Order> orders);
}
