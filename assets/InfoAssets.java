package assets;

import java.util.LinkedList;
import java.util.List;

public final class InfoAssets {
    private InfoAssets() {
    }

    public static final int APPLE_ID = 0;
    public static final int CHEESE_ID = 1;
    public static final int BREAD_ID = 2;
    public static final int CHICKEN_ID = 3;
    public static final int SILK_ID = 10;
    public static final int PEPPER_ID = 11;
    public static final int BARREL_ID = 12;

    public static final int APPLE_PROFIT = 2;
    public static final int CHEESE_PROFIT = 3;
    public static final int BREAD_PROFIT = 4;
    public static final int CHICKEN_PROFIT = 4;
    public static final int SILK_PROFIT = 9;
    public static final int PEPPER_PROFIT = 8;
    public static final int BARREL_PROFIT = 7;

    public static final int APPLE_PENALTY = 2;
    public static final int CHEESE_PENALTY = 2;
    public static final int BREAD_PENALTY = 2;
    public static final int CHICKEN_PENALTY = 2;
    public static final int SILK_PENALTY = 4;
    public static final int PEPPER_PENALTY = 4;
    public static final int BARREL_PENALTY = 4;

    public static final int APPLE_KING_BONUS = 20;
    public static final int APPLE_QUEEN_BONUS = 10;
    public static final int CHEESE_KING_BONUS = 15;
    public static final int CHEESE_QUEEN_BONUS = 10;
    public static final int BREAD_KING_BONUS = 15;
    public static final int BREAD_QUEEN_BONUS = 10;
    public static final int CHICKEN_KING_BONUS = 10;
    public static final int CHICKEN_QUEEN_BONUS = 5;

    public static final int SILK_NR_BONUS_LEGAL_ASSETS = 3;
    public static final int PEPPER_NR_BONUS_LEGAL_ASSETS = 2;
    public static final int BARREL_NR_BONUS_LEGAL_ASSETS = 2;

    public static final int ID_LEGAL_ASSET = 0;
    public static final int ID_ILLEGAL_ASSET = 1;

    public static final int ID_BONUS_KING = 0;
    public static final int ID_BONUS_QUEEN = 1;

    public static final int NR_LEGAL_ASSETS = 4;

    /**
     *
     * @param id the id of the asset
     * @return check if the asset is illegal
     */
    public static boolean isIllegalAsset(final int id) {
        return (id == SILK_ID) || (id == PEPPER_ID) || (id == BARREL_ID);
    }
    /**
    *
    * @return a list with legal assets ids
    */
    public static List<Integer> getLegalAssetsIds() {
        List<Integer> ids = new LinkedList<>();
        ids.add(APPLE_ID);
        ids.add(CHEESE_ID);
        ids.add(BREAD_ID);
        ids.add(CHICKEN_ID);
        return ids;
    }
}
