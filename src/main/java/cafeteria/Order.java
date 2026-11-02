package cafeteria;

public class Order {
    private final String student;
    private final int foodPrice;
    private final int drinkPrice;
    private final int arrivalMinute;
    private final int preparationMinutes;
    private final int minutesUntilClass;

    public Order(String student, int foodPrice, int drinkPrice, int arrivalMinute,
                 int preparationMinutes, int minutesUntilClass) {
        this.student = student;
        this.foodPrice = foodPrice;
        this.drinkPrice = drinkPrice;
        this.arrivalMinute = arrivalMinute;
        this.preparationMinutes = preparationMinutes;
        this.minutesUntilClass = minutesUntilClass;
    }

    public String getStudent() { return student; }
    public int getFoodPrice() { return foodPrice; }
    public int getDrinkPrice() { return drinkPrice; }
    public int getArrivalMinute() { return arrivalMinute; }
    public int getPreparationMinutes() { return preparationMinutes; }
    public int getMinutesUntilClass() { return minutesUntilClass; }
}
