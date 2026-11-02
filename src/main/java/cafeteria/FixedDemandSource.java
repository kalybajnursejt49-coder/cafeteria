package cafeteria;

public class FixedDemandSource implements DemandSource {
    private final double multiplier;

    public FixedDemandSource(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
