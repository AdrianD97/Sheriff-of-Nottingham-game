package assets;

import java.util.HashMap;

public final class PenaltyAssets {
    private static PenaltyAssets penaltyAssetsInstance = new PenaltyAssets();

    public static PenaltyAssets getInstance() {
        return penaltyAssetsInstance;
    }

    private HashMap<Integer, Integer> penaltyAssets;

    private PenaltyAssets() {
        penaltyAssets = new HashMap<>();
    }

    public void createPenaltyAssets() {
        penaltyAssets.put(InfoAssets.APPLE_ID, InfoAssets.APPLE_PENALTY);
        penaltyAssets.put(InfoAssets.CHEESE_ID, InfoAssets.CHEESE_PENALTY);
        penaltyAssets.put(InfoAssets.BREAD_ID, InfoAssets.BREAD_PENALTY);
        penaltyAssets.put(InfoAssets.CHICKEN_ID, InfoAssets.CHICKEN_PENALTY);
        penaltyAssets.put(InfoAssets.SILK_ID, InfoAssets.SILK_PENALTY);
        penaltyAssets.put(InfoAssets.PEPPER_ID, InfoAssets.PEPPER_PENALTY);
        penaltyAssets.put(InfoAssets.BARREL_ID, InfoAssets.BARREL_PENALTY);
    }

    public int getPenalty(final int id) {
        return penaltyAssets.get(id);
    }
}
