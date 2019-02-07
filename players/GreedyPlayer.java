package players;

import assets.Asset;
import bag.Bag;

import java.util.LinkedList;
import java.util.List;

public final class GreedyPlayer extends BasicPlayer {
    private int round;

    public GreedyPlayer(final int id) {
        super(id);
        round = 1;
    }

    /**
     *
     * @param player the player that will be inspected by the current player.
     * @return a list with the confiscated assets from the player bag
     */
    public List<Asset> inspection(final Player player) {
        if (player.getBag().getBribe() != 0) {
            this.setMoney(this.getMoney() + player.getBag().getBribe());
            player.setMoney(player.getMoney() - player.getBag().getBribe());
            return  new LinkedList<>();
        } else {
            return super.inspection(player);
        }
    }

    /**
     * create the greedy player bag.
     */
    public void bagCreating() {
        super.bagCreating();

        if (round % 2 == 0) {
            if (this.getBag().getSize() < Bag.MAX_SIZE) {
                if (this.getNrIllegalHandAssets() > 0) {
                    List<Asset> illegalAssets = this.getIllegalHandAssets();

                    int max = illegalAssets.get(0).getProfit();
                    Asset asset = illegalAssets.get(0);
                    int id = illegalAssets.get(0).getId();

                    for (int i = 1; i < illegalAssets.size(); ++i) {
                        if (illegalAssets.get(i).getProfit() > max) {
                            max = illegalAssets.get(i).getProfit();
                            asset = illegalAssets.get(i);
                            id = illegalAssets.get(i).getId();
                        }
                    }

                    this.deleteAssetHand(id);
                    this.getBag().add(asset);
                }
            }
        }
        ++round;
    }
}
