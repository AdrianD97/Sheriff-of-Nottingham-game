package assets;

import java.util.HashMap;

class BonusKingQueen {
    private final int kingBonus;
    private final int queenBonus;

    BonusKingQueen(final int kingB, final int queenB) {
        kingBonus = kingB;
        queenBonus = queenB;
    }

    public int getKingBonus() {
        return kingBonus;
    }

    public int getQueenBonus() {
        return queenBonus;
    }
}

class BonusIllegalAsset {
    private int idAsset;
    private int numberAssets;

    BonusIllegalAsset(final int id, final int nrAssets) {
        idAsset = id;
        numberAssets = nrAssets;
    }

    public int getIdAsset() {
        return idAsset;
    }

    public int getNumberAssets() {
        return numberAssets;
    }
}

public final class BonusAssets {
    private static BonusAssets bonusAssetsInstance = new BonusAssets();

    public static BonusAssets getInstance() {
        return bonusAssetsInstance;
    }

    private final HashMap<Integer, BonusKingQueen> bonusLegalAssets;
    private final HashMap<Integer, BonusIllegalAsset> bonusIllegalAssets;

    private BonusAssets() {
        bonusLegalAssets = new HashMap<>();
        bonusIllegalAssets = new HashMap<>();
    }

    public void createBonus() {
        bonusLegalAssets.put(InfoAssets.APPLE_ID,
                new BonusKingQueen(InfoAssets.APPLE_KING_BONUS, InfoAssets.APPLE_QUEEN_BONUS));
        bonusLegalAssets.put(InfoAssets.CHEESE_ID,
                new BonusKingQueen(InfoAssets.CHEESE_KING_BONUS, InfoAssets.CHEESE_QUEEN_BONUS));
        bonusLegalAssets.put(InfoAssets.BREAD_ID,
                new BonusKingQueen(InfoAssets.BREAD_KING_BONUS, InfoAssets.BREAD_QUEEN_BONUS));
        bonusLegalAssets.put(InfoAssets.CHICKEN_ID,
                new BonusKingQueen(InfoAssets.CHICKEN_KING_BONUS, InfoAssets.CHICKEN_QUEEN_BONUS));

        bonusIllegalAssets.put(InfoAssets.SILK_ID,
                new BonusIllegalAsset(InfoAssets.CHEESE_ID, InfoAssets.SILK_NR_BONUS_LEGAL_ASSETS));
        bonusIllegalAssets.put(InfoAssets.PEPPER_ID,
                new BonusIllegalAsset(InfoAssets.CHICKEN_ID,
                        InfoAssets.PEPPER_NR_BONUS_LEGAL_ASSETS));
        bonusIllegalAssets.put(InfoAssets.BARREL_ID,
                new BonusIllegalAsset(InfoAssets.BREAD_ID,
                        InfoAssets.BARREL_NR_BONUS_LEGAL_ASSETS));
    }

    public int getBonusLegalAsset(final int id, final int typeBonus) {
        return (typeBonus == 0) ? bonusLegalAssets.get(id).getKingBonus()
                : bonusLegalAssets.get(id).getQueenBonus();
    }

    public int getNrAssetsLegal(final int id) {
        return bonusIllegalAssets.get(id).getNumberAssets();
    }

    public int getIdAsset(final int id) {
        return bonusIllegalAssets.get(id).getIdAsset();
    }
}
