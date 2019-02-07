package assets;

import java.util.HashMap;

public final class ProfitAssets {
    private static ProfitAssets profitAssetsInstance = new ProfitAssets();

    public static ProfitAssets getInstance() {
        return profitAssetsInstance;
    }

    private HashMap<Integer, Integer> profitAssets;

    private ProfitAssets() {
        profitAssets = new HashMap<>();
    }

    public void createProfitAssets() {
        profitAssets.put(InfoAssets.APPLE_ID, InfoAssets.APPLE_PROFIT);
        profitAssets.put(InfoAssets.CHEESE_ID, InfoAssets.CHEESE_PROFIT);
        profitAssets.put(InfoAssets.BREAD_ID, InfoAssets.BREAD_PROFIT);
        profitAssets.put(InfoAssets.CHICKEN_ID, InfoAssets.CHICKEN_PROFIT);
        profitAssets.put(InfoAssets.SILK_ID, InfoAssets.SILK_PROFIT);
        profitAssets.put(InfoAssets.PEPPER_ID, InfoAssets.PEPPER_PROFIT);
        profitAssets.put(InfoAssets.BARREL_ID, InfoAssets.BARREL_PROFIT);
    }

    public int getProfit(final int id) {
        return profitAssets.get(id);
    }
}
