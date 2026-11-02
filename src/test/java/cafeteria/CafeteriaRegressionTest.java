package cafeteria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CafeteriaRegressionTest {
    @Test
    void regularOrder() {
        Order order = new Order("Ali", 50, 0, 1, 5, 30);
        assertEquals(50, new Cafeteria(() -> 1.0).priceFor(order));
    }

    @Test
    void comboOrder() {
        Order order = new Order("Ali", 50, 20, 1, 5, 30);
        assertEquals(60, new Cafeteria(() -> 1.0).priceFor(order));
    }

    @Test
    void busyOrder() {
        Order order = new Order("Ali", 50, 20, 1, 5, 30);
        assertEquals(105, new Cafeteria(() -> 1.5).priceFor(order));
    }
}
