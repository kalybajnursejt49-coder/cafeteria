package cafeteria;

public class PreparationSelector {
    public PreparationStrategy select(boolean batchMachineAvailable, boolean secondCookAvailable) {
        if (batchMachineAvailable) {
            return new BatchPreparation();
        }
        if (secondCookAvailable) {
            return new TwoCooksPreparation();
        }
        return new OneCookPreparation();
    }
}
