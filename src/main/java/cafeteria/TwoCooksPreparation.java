package cafeteria;

import java.util.List;

public class TwoCooksPreparation implements PreparationStrategy {
    public int minutesFor(List<Order> orders) {
        int firstCook = 0;
        int secondCook = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (i % 2 == 0) {
                firstCook += orders.get(i).getPreparationMinutes();
            } else {
                secondCook += orders.get(i).getPreparationMinutes();
            }
        }
        return Math.max(firstCook, secondCook);
    }
}
