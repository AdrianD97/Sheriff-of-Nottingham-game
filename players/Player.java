package players;

import assets.InfoAssets;
import assets.PenaltyAssets;
import assets.ProfitAssets;
import assets.Asset;
import assets.BonusAssets;
import bag.Bag;

import java.util.LinkedList;
import java.util.List;

public abstract class Player implements Sheriff, Trader {
    private static final int INITIAL_AMOUNT = 50;
    private final int playerId;
    private int money;
    private final List<Asset> marketAssets;
    private final List<Asset> handAssets;
    private final Bag bag;
    private int nrIllegalHandAssets;
    private int nrLegalHandAssets;
    private int score;

    public Player(final int id) {
        score = 0;
        playerId = id;
        money = INITIAL_AMOUNT;
        nrIllegalHandAssets = 0;
        nrLegalHandAssets = 0;
        marketAssets = new LinkedList<>();
        handAssets = new LinkedList<>();
        bag = new Bag();
    }

    /**
     *
     * @return the player id
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     *
     * @return the amount of money the player has
     */
    public int getMoney() {
        return money;
    }

    /**
     *
     * @param amount New amount of money
     */
    public void setMoney(final int amount) {
        money = amount;
    }

    /**
     *
     * @return how many assets the player has on market
     */
    public int getSizeMarketAseets() {
        return marketAssets.size();
    }

    /**
     *
     * @return the assets that player has on market
     */
    public List<Asset> getMarketAssets() {
        return marketAssets;
    }

    /**
     *
     * @return how many assets the player have in hand
     */
    public int getSizeHandAseets() {
        return handAssets.size();
    }

    /**
     *
     * @return the assets that player has in hand
     */
    public List<Asset> getHandAssets() {
        return handAssets;
    }

    /**
     *
     * @param assets list of the assets that must be added to player market.
     *               add assets to player market
     */
    public void addMarketAssets(final List<Asset> assets) {
        for (int i = 0; i < assets.size(); ++i) {
            marketAssets.add(assets.get(i));
        }
    }

    /**
     *
     * @param asset the asset that is added in player hand.
     *              add an asset in player hand
     */
    public void addHandsAsset(final Asset asset) {
            handAssets.add(asset);
            if (asset.getType() == InfoAssets.ID_LEGAL_ASSET) {
                ++nrLegalHandAssets;
            } else {
                ++nrIllegalHandAssets;
            }
    }

    /**
     *
     * @return number of the legal assets that player has in hand
     */
    public int getNrLegalHandAssets() {
        return nrLegalHandAssets;
    }

    /**
     *
     * @return number of the illegal assets that player has in hand
     */
    public int getNrIllegalHandAssets() {
        return nrIllegalHandAssets;
    }

    /**
     *
     * @return legal assets that the player has in hand
     */
    public List<Asset> getLegalHandAssets() {
        List<Asset> assets = new LinkedList<>();
        for (int i = 0; i < getSizeHandAseets(); ++i) {
            if (handAssets.get(i).getType() == InfoAssets.ID_LEGAL_ASSET) {
                assets.add(handAssets.get(i));
            }
        }
        return assets;
    }

    /**
     *
     * @return illegal assets that the player has in hand
     */
    public List<Asset> getIllegalHandAssets() {
        List<Asset> assets = new LinkedList<>();
        for (int i = 0; i < getSizeHandAseets(); ++i) {
            if (handAssets.get(i).getType() == InfoAssets.ID_ILLEGAL_ASSET) {
                assets.add(handAssets.get(i));
            }
        }
        return assets;
    }

    /**
     *
     * @return legal assets that the player has on market
     */
    public List<Asset> getLegalMarketAssets() {
        List<Asset> assets = new LinkedList<>();
        for (int i = 0; i < getSizeMarketAseets(); ++i) {
            if (marketAssets.get(i).getType() == InfoAssets.ID_LEGAL_ASSET) {
                assets.add(marketAssets.get(i));
            }
        }
        return assets;
    }

    /**
     *
     * @return illegal assets that the player has on market
     */
    public List<Asset> getIllegalMarketAssets() {
        List<Asset> assets = new LinkedList<>();
        for (int i = 0; i < getSizeMarketAseets(); ++i) {
            if (marketAssets.get(i).getType() == InfoAssets.ID_ILLEGAL_ASSET) {
                assets.add(marketAssets.get(i));
            }
        }
        return assets;
    }

    /**
     *
     * @param id the id of the asset
     * @return number of assets with id = "id" form player market
     */
    public int getNumberAssetsMarketWithId(final int id) {
        int nr = 0;
        for (int i = 0; i < getSizeMarketAseets(); ++i) {
            if (marketAssets.get(i).getId() == id) {
                ++nr;
            }
        }
        return nr;
    }

    /**
     *
     * @param id the id of the asset
     * @return number of assets with id = "id" from player hand
     */
    public int getNumberAssetsHandWithId(final int id) {
        int nr = 0;
        for (int i = 0; i < getSizeHandAseets(); ++i) {
            if (handAssets.get(i).getId() == id) {
                ++nr;
            }
        }
        return nr;
    }

    /**
     *
     * @param id the id of the asset
     * @return assets with id = "id" from player market
     */
    public List<Asset> getAssetsMarketWithId(final int id) {
        List<Asset> assets = new LinkedList<>();

        for (int i = 0; i < getSizeMarketAseets(); ++i) {
            if (marketAssets.get(i).getId() == id) {
                assets.add(marketAssets.get(i));
            }
        }

        return assets;
    }

    /**
     *
     * @param id the id of the asset
     * @return assets with id = "id" from player hand
     */
    public List<Asset> getAssetsHandWithId(final int id) {
        List<Asset> assets = new LinkedList<>();

        for (int i = 0; i < getSizeHandAseets(); ++i) {
            if (handAssets.get(i).getId() == id) {
                assets.add(handAssets.get(i));
            }
        }

        return assets;
    }

    /**
     *
     * @param id the id of the asset that will be remove from player hand
     * remove the first asset with id = "id".
     */
    public void deleteAssetHand(final int id) {
        for (int i = 0; i < getSizeHandAseets(); ++i) {
            if (handAssets.get(i).getId() == id) {
                if (handAssets.get(i).getType() == InfoAssets.ID_LEGAL_ASSET) {
                    --nrLegalHandAssets;
                } else {
                    --nrIllegalHandAssets;
                }
                handAssets.remove(i);
                return;
            }
        }
    }

    /**
     *
     * @return the player score
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param val the value that will be the new player score
     */
    public void setScore(final int val) {
        score = val;
    }

    /**
     * compute the profit of the market assets
     * for each illegal assets add legal assets that are bonus
     * brought by that asset.
     */
    public void computeProfit() {
        int profit = this.money;
        List<Asset> assets = null;

        //add to "profit" the profit of the each illegal asset
        assets = this.getIllegalMarketAssets();
        for (int i = 0; i < assets.size(); ++i) {
            profit += assets.get(i).getProfit();
            int id = BonusAssets.getInstance().getIdAsset(assets.get(i).getId());
            int nrAssets = BonusAssets.getInstance().getNrAssetsLegal(assets.get(i).getId());
            List<Asset> addedAssets = new LinkedList<>();

            while (nrAssets > 0) {
                addedAssets.add(new Asset(id, PenaltyAssets.getInstance().getPenalty(id),
                        ProfitAssets.getInstance().getProfit(id), InfoAssets.ID_LEGAL_ASSET));
                --nrAssets;
            }
            this.addMarketAssets(addedAssets);
        }


        //add to "profit" the profit of the each legal asset
        assets = this.getLegalMarketAssets();
        for (int i = 0; i < assets.size(); ++i) {
            profit += assets.get(i).getProfit();
        }

        score += profit;
    }

    /**
     *
     * @return the player bag
     */
    public Bag getBag() {
        return bag;
    }
}

