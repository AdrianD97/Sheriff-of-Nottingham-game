package players;

import java.util.LinkedList;
import java.util.List;

import assets.Asset;
import assets.InfoAssets;
import assets.PenaltyAssets;
import bag.Bag;

public class BasicPlayer extends Player {
    public BasicPlayer(final int id) {
        super(id);
    }

    /**
     *
     * @param player the player that will be inspected by the current player.
     * @return a list with the confiscated assets from the player bag
     */
    public List<Asset> inspection(final Player player) {
        List<Asset> confiscatedProducts = new LinkedList<>();
        boolean liar = false;
        Bag bag = player.getBag();

        for (int i = 0; i < bag.getSize(); ++i) {
            if (bag.getContent().get(i).getId() != bag.getDeclaredType()) {
                liar = true;
                this.setMoney(this.getMoney() + bag.getContent().get(i).getPenalty());
                player.setMoney(player.getMoney() - bag.getContent().get(i).getPenalty());
                confiscatedProducts.add(bag.getContent().get(i));
                bag.delete(bag.getContent().get(i).getId());
                --i;
            }
        }

        if (!liar) {
            this.setMoney(this.getMoney() - player.getBag().getSize()
                    * PenaltyAssets.getInstance().getPenalty(player.getBag().getDeclaredType()));
            player.setMoney(player.getMoney() + player.getBag().getSize()
                    * PenaltyAssets.getInstance().getPenalty(player.getBag().getDeclaredType()));
        }

        return confiscatedProducts;

    }

    /**
     * create the basic player bag.
     */
    public void bagCreating() {
        this.getBag().setBribe(0);
        if (this.getNrLegalHandAssets() != 0) {
            List<Asset> legalAssets = this.getLegalHandAssets();
            List<Integer> ids = new LinkedList<>();

            int max = this.getNumberAssetsHandWithId(legalAssets.get(0).getId());
            int profit = legalAssets.get(0).getProfit();
            int idAsset = legalAssets.get(0).getId();
            ids.add(legalAssets.get(0).getId());
            this.getBag().add(legalAssets.get(0));

            for (int i = 1; i < legalAssets.size(); ++i) {
                if (legalAssets.get(i).getId() == idAsset) {
                    if (this.getBag().getSize() == Bag.MAX_SIZE) {
                        break;
                    }
                    ids.add(legalAssets.get(i).getId());
                    this.getBag().add(legalAssets.get(i));
                } else {
                    if ((this.getNumberAssetsHandWithId(legalAssets.get(i).getId()) > max)
                            || (this.getNumberAssetsHandWithId(legalAssets.get(i).getId()) == max
                            && legalAssets.get(i).getProfit() > profit)) {
                        max = this.getNumberAssetsHandWithId(legalAssets.get(i).getId());
                        this.getBag().free();
                        idAsset = legalAssets.get(i).getId();
                        profit = legalAssets.get(i).getProfit();
                        this.getBag().add(legalAssets.get(i));
                        ids.clear();
                        ids.add(legalAssets.get(i).getId());
                    }
                }
            }
            for (int i = 0; i < ids.size(); ++i) {
                this.deleteAssetHand(ids.get(i));
            }
            this.getBag().setDeclaredType(idAsset);
        } else {
            List<Asset> illegalAssets = this.getIllegalHandAssets();
            int max = illegalAssets.get(0).getProfit();
            Asset asset = illegalAssets.get(0);
            int id = illegalAssets.get(0).getId();

            for (int i = 1; i < illegalAssets.size(); ++i) {
                if (illegalAssets.get(i).getProfit() > max) {
                    asset = illegalAssets.get(i);
                    max = illegalAssets.get(i).getProfit();
                    id = illegalAssets.get(i).getId();
                }
            }

            this.deleteAssetHand(id);
            this.getBag().add(asset);
            this.getBag().setDeclaredType(InfoAssets.APPLE_ID);
        }
    }
}
