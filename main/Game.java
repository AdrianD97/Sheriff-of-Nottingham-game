package main;

import assets.InfoAssets;
import assets.Asset;
import assets.ProfitAssets;
import assets.PenaltyAssets;
import assets.BonusAssets;
import comparator.PlayerComparator;
import players.Player;
import players.PlayersFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class Game {
    private static final int MAX_NUMBER_ASSETS_IN_HAND = 6;
    private static Game myInstance = new Game();
    private List<Player> players;
    private int rounds;
    private int sheriffId;
    private GameInput inputGame;

    public static Game getInstance() {
        return myInstance;
    }

    private Game() {
    }

    /**
     *
     * @param gameInput the input(idsAssets, namesPlayers) of the game
     *    initialize the game.
     */
    public void createGame(final GameInput gameInput) {
        inputGame = gameInput;
        sheriffId = 0;
        rounds = 2 * gameInput.getPlayerNames().size();

        BonusAssets.getInstance().createBonus();
        ProfitAssets.getInstance().createProfitAssets();
        PenaltyAssets.getInstance().createPenaltyAssets();

        players = PlayersFactory.getInstance().createPlayers(gameInput.getPlayerNames());

        completeHandAssets();
    }

    /**
     * complete the hand assets number of the players.
     * each player must to have six assets
     */
    private void completeHandAssets() {
        int idAsset = -1;
        for (int i = 0; i < players.size(); ++i) {
            while (players.get(i).getSizeHandAseets() < MAX_NUMBER_ASSETS_IN_HAND) {
                idAsset = inputGame.getAssetIds().get(0);
                int type = InfoAssets.ID_LEGAL_ASSET;

                if (InfoAssets.isIllegalAsset(idAsset)) {
                    type = InfoAssets.ID_ILLEGAL_ASSET;
                }
                players.get(i).addHandsAsset(new Asset(idAsset,
                        PenaltyAssets.getInstance().getPenalty(idAsset),
                        ProfitAssets.getInstance().getProfit(idAsset), type));
                inputGame.getAssetIds().remove(0);
            }
        }
    }

    /**
     * create the game ranking.
     */
    private void makeRanking() {
        PlayerComparator playerComparator = new PlayerComparator();
        Collections.sort(players, playerComparator);
        Collections.reverse(players);
    }

    /**
     * display the game ranking.
     */
    protected void displayRanking() {
        for (int i = 0; i < players.size(); ++i) {
            System.out.println(inputGame.getPlayerNames().get(
                    players.get(i).getPlayerId()).toUpperCase()
                    + ": " + players.get(i).getScore());
        }
    }

    /**
     *
     * @param assetId the id of the asset
     * @param playersId the player list that have max number assets with id = "idAsset"
     * @param superiorLimit when compute the max for grant queen bonus,
     *                      this max must be different from the max for grant the king bonus.
     * @return a new max lesser then superiorLimit
     */
    private int getPlayersId(final int assetId, final List<Integer> playersId,
                             final Integer superiorLimit) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < players.size(); ++i) {
            if (players.get(i).getNumberAssetsMarketWithId(assetId) != superiorLimit) {
                if (players.get(i).getNumberAssetsMarketWithId(assetId) == max) {
                    playersId.add(i);
                } else {
                    if (players.get(i).getNumberAssetsMarketWithId(assetId) > max) {
                        max = players.get(i).getNumberAssetsMarketWithId(assetId);
                        playersId.clear();
                        playersId.add(i);
                    }
                }
            }
        }
        return max;
    }

    /**
     * performs the game logic.
     * every round: - the players than are not sheriff create their bag
     *              - then, give the bag to the sheriff
     *              - the sheriff inspects the bag, and confiscates the illegal assets
     * at the end of the game:
     *              - compute the profit, add the assets bonus
     *              - create the ranking, display the ranking
     */
    public void run() {
        List<Asset> confiscatedAssets = null;
        while (rounds > 0) {
            for (int i = 0; i < players.size(); ++i) {
                if (i != sheriffId) {
                    players.get(i).bagCreating();
                    confiscatedAssets = players.get(sheriffId).inspection(players.get(i));
                    for (int j = 0; j < confiscatedAssets.size(); ++j) {
                        inputGame.getAssetIds().add(confiscatedAssets.get(j).getId());
                    }

                    players.get(i).addMarketAssets(players.get(i).getBag().getContent());
                    players.get(i).getBag().free();
                }
            }

            sheriffId = ++sheriffId % players.size();
            completeHandAssets();
            --rounds;
        }

        for (int i = 0; i < players.size(); ++i) {
            players.get(i).computeProfit();
        }

        List<Integer> ids = InfoAssets.getLegalAssetsIds();
        for (int i = 0; i < InfoAssets.NR_LEGAL_ASSETS; ++i) {
            List<Integer> playersId = new LinkedList<>();
            int max = Integer.MAX_VALUE;

            max = this.getPlayersId(ids.get(i), playersId, max);

            for (int j = 0; j < playersId.size(); ++j) {
                players.get(playersId.get(j)).setScore(players.get(playersId.get(j)).getScore()
                        + BonusAssets.getInstance().getBonusLegalAsset(ids.get(i),
                        InfoAssets.ID_BONUS_KING));
            }

            playersId.clear();
            max = this.getPlayersId(ids.get(i), playersId, max);

            for (int j = 0; j < playersId.size(); ++j) {
                players.get(playersId.get(j)).setScore(players.get(playersId.get(j)).getScore()
                        + BonusAssets.getInstance().getBonusLegalAsset(ids.get(i),
                        InfoAssets.ID_BONUS_QUEEN));
            }
        }

        makeRanking();
        displayRanking();
    }
}
