package players;

import assets.Asset;
import assets.BonusAssets;
import assets.InfoAssets;
import assets.ProfitAssets;
import bag.Bag;

import java.util.LinkedList;
import java.util.List;

public class RoyalPlayer extends BasicPlayer {
    private static final int NR_MIN_BAG_ASSETS = 4;
    public RoyalPlayer(final int id) {
        super(id);
    }

    /**
     *
     * @param player the player that will be inspected by the current player.
     * @return a list with the confiscated assets from the player bag
     */
    public List<Asset> inspection(final Player player) {
        if (player.getBag().getSize() >= NR_MIN_BAG_ASSETS) {
            return super.inspection(player);
        } else {
            return new LinkedList<>();
        }
    }

    /**
     * create the basic player bag.
     */
    public void bagCreating() {
        if (this.getNrLegalHandAssets() == 0) {
            super.bagCreating();
        } else {
            this.getBag().setBribe(0);
            int score;
            int maxScore = 0;
            int idAsset = 0;
            for (int i = 0; i < InfoAssets.NR_LEGAL_ASSETS; ++i) {
                score = (this.getNumberAssetsMarketWithId(i) + this.getNumberAssetsHandWithId(i))
                        * ProfitAssets.getInstance().getProfit(i)
                        + BonusAssets.getInstance().getBonusLegalAsset(i,
                        InfoAssets.ID_BONUS_KING) + BonusAssets.getInstance().getBonusLegalAsset(i,
                        InfoAssets.ID_BONUS_QUEEN);
                if (score > maxScore) {
                    maxScore = score;
                    idAsset = i;
                }
            }

            List<Asset> assets = this.getAssetsHandWithId(idAsset);
            for (Asset asset: assets) {
                if (this.getBag().getSize() == Bag.MAX_SIZE) {
                    break;
                }
                this.getBag().add(asset);
                this.deleteAssetHand(asset.getId());
            }

            this.getBag().setDeclaredType(idAsset);
        }
    }
}
