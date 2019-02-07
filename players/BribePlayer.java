package players;

import assets.Asset;
import bag.Bag;

import java.util.LinkedList;
import java.util.List;

public final class BribePlayer extends BasicPlayer {
    private int leftId;
    private int rightId;
    private static final int BRIBE_FOR_MAX_TWO_ASSETS = 5;
    private static final int BRIBE_FOR_MIN_THREE_ASSETS = 10;

    public BribePlayer(final int id, final int left, final int right) {
        super(id);
        leftId = left;
        rightId = right;
    }

    /**
     *
     * @param player the player that will be inspected by the current player.
     * @return a list with the confiscated assets from the player bag
     */
    public List<Asset> inspection(final Player player) {
        if (player.getPlayerId() != leftId && player.getPlayerId() != rightId) {
            this.setMoney(this.getMoney() + player.getBag().getBribe());
            player.setMoney(player.getMoney() - player.getBag().getBribe());
            return new LinkedList<>();
        } else {
            return super.inspection(player);
        }
    }

    /**
     * create the bribe player bag.
     */
    public void bagCreating() {
        if (this.getMoney() <= 0 || this.getNrIllegalHandAssets() == 0) {
            super.bagCreating();
            return;
        }

        if (this.getNrIllegalHandAssets() <= 2) {
            if (this.getMoney() - BRIBE_FOR_MAX_TWO_ASSETS < 0) {
                super.bagCreating();
                return;
            }
        }

        if (this.getNrIllegalHandAssets() > 2) {
            if (this.getMoney() - BRIBE_FOR_MIN_THREE_ASSETS < 0) {
                super.bagCreating();
                return;
            }
        }

        List<Asset> assets = this.getIllegalHandAssets();

        if (this.getNrIllegalHandAssets() <= 2) {
            this.getBag().setBribe(BRIBE_FOR_MAX_TWO_ASSETS);
        } else {
            this.getBag().setBribe(BRIBE_FOR_MIN_THREE_ASSETS);
        }

        if (this.getNrIllegalHandAssets() > Bag.MAX_SIZE) {
            int min = assets.get(0).getProfit();
            int idAsset = 0;
            for (int i = 1; i < this.getNrIllegalHandAssets(); ++i) {
                if (assets.get(i).getProfit() < min) {
                    min = assets.get(i).getProfit();
                    idAsset = i;
                }
            }
            assets.remove(idAsset);
        }

        for (int i = 0; i < assets.size(); ++i) {
            this.getBag().add(assets.get(i));
            this.deleteAssetHand(assets.get(i).getId());
        }

        this.getBag().setDeclaredType(0);
    }
}
