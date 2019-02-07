package assets;

public class Asset {
    private final int id;
    private final int penalty;
    private final int profit;
    private final int type;

    public Asset(final int idAsset, final int penaltyAsset, final int profitAsset,
                 final int typeAsset) {
        id = idAsset;
        penalty = penaltyAsset;
        profit = profitAsset;
        type = typeAsset;
    }

    /**
     *
     * @return the asset id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return the asset penalty
     */
    public int getPenalty() {
        return penalty;
    }

    /**
     *
     * @return the asset profit
     */
    public int getProfit() {
        return profit;
    }

    /**
     *
     * @return the asset type (legal = 0 or illegal = 1)
     */
    public int getType() {
        return type;
    }
}
