# Campus cafeteria Strategy pattern

This Java project chooses a price for one order plus a preparation plan for several orders. Both choices can change while the program runs.

## Run

Use JDK 8 or newer plus Maven.

Extract the ZIP before opening the project in IntelliJ IDEA. Open the extracted `cafeteria-strategy` folder, then run `Main.java`. On Windows, `run.bat` compiles the source files plus starts the demo without Maven.

```text
mvn test
mvn package
java -cp target/classes cafeteria.Main
```

`Main` prints every algorithm on the same input. It also shows manual selection, automatic selection, plus replacement through `setStrategy()`.

## Rules

The price selector uses busy pricing when the demand multiplier exceeds 1.2. Otherwise it uses combo pricing for an order containing food plus a drink. Other orders use regular pricing. Busy pricing gets its multiplier through `DemandSource`.

The preparation selector uses a batch machine when one is available. Otherwise it uses two cooks when a second cook is available. Otherwise it uses one cook. One cook adds all preparation times. Two cooks alternate orders between workers. Batch preparation uses the longest preparation time plus two minutes per order for handling.

## Classes

| Class | Responsibility |
|---|---|
| `Order` | Stores the details of one student order. |
| `DemandSource` | Supplies the current demand multiplier. |
| `FixedDemandSource` | Supplies a fixed demand multiplier for the demo. |
| `PricingStrategy` | Defines the price calculation method. |
| `RegularPrice` | Adds the food price to the drink price. |
| `ComboPrice` | Subtracts a drink discount from the total price. |
| `BusyPrice` | Applies the current demand multiplier to the total price. |
| `PricingContext` | Delegates price calculation to its current strategy. |
| `PriceSelector` | Selects a pricing strategy from demand plus order contents. |
| `Cafeteria` | Connects automatic price selection to price calculation. |
| `PreparationStrategy` | Defines the preparation time calculation method. |
| `OneCookPreparation` | Adds preparation times for one worker. |
| `TwoCooksPreparation` | Distributes preparation times across two workers. |
| `BatchPreparation` | Estimates preparation time for one shared batch. |
| `PreparationContext` | Delegates preparation calculation to its current strategy. |
| `PreparationSelector` | Selects a preparation strategy from available kitchen resources. |
| `Main` | Demonstrates manual selection, automatic selection, runtime replacement. |

## Why Strategy

Each family has one small interface. Its three implementations contain different algorithms. A context receives an implementation in its constructor, then calls the same interface method regardless of the selected algorithm. Calling `setStrategy()` changes the behavior of the same context object during execution. The selectors are separate classes, so the contexts never inspect a strategy's class. A new pricing rule can be added as another `PricingStrategy`; a new preparation plan can be added as another `PreparationStrategy`. The selectors can then be updated without changing either context.

State would model an object's internal condition and transitions between conditions. Here the program deliberately chooses an algorithm from the current situation. The pricing context does not move itself through states; a caller explicitly supplies a strategy. Template Method would put a fixed algorithm outline in a parent class and let subclasses fill selected steps. Here there is no parent algorithm outline. Each class owns its entire calculation and shares only an interface. This keeps the example small enough to extend during a live defense.

`BusyPrice` reads changing information through `DemandSource`. Tests supply a fake source with a fixed multiplier, so they do not depend on a server or the time of day.
