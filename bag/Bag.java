package bag;

import assets.Asset;

import java.util.LinkedList;
import java.util.List;

public class Bag {
    private int bribe;
    private int declaredType;
    private final List<Asset> assets;
    public static final int MAX_SIZE = 5;

    public Bag() {
        assets = new LinkedList<>();
    }

    /**
     *
     * @return  the bribe put in the bag
     */
    public int getBribe() {
        return bribe;
    }

    /**
     *
     * @return the declared type of the bag
     */
    public int getDeclaredType() {
        return declaredType;
    }

    /**
     *
     * @param money the bribe that is put in the bag
     */
    public void setBribe(final int money) {
        bribe = money;
    }

    /**
     *
     * @param type declared type of the assets from bag
     */
    public void setDeclaredType(final int type) {
        declaredType = type;
    }

    /**
     *
     * @param asset the asset that will be added in the bag
     */
    public void add(final Asset asset) {
        assets.add(asset);
    }

    /**
     *
     * @param id the id of the asset that will be deleted from bag
     */
    public void delete(final int id) {
        for (Asset asset: assets) {
            if (asset.getId() == id) {
                assets.remove(asset);
                return;
            }
        }
    }

    /**
     * free the bag.
     */
    public void free() {
        assets.clear();
    }

    /**
     *
     * @return how many assets contains the bag
     */
    public int getSize() {
        return assets.size();
    }

    /**
     *
     * @return the assets from bag
     */
    public List<Asset> getContent() {
        return assets;
    }
}
