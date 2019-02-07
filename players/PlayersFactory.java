package players;

import java.util.LinkedList;
import java.util.List;

public final class PlayersFactory {
    private static PlayersFactory playersFactoryInstance = new PlayersFactory();

    public static PlayersFactory getInstance() {
        return playersFactoryInstance;
    }

    private PlayersFactory() {
    }

    /**
     *
     * @param names type of the players
     * @return a list with players
     */
    public List<Player> createPlayers(final List<String> names) {
        int nrPlayers = names.size();
        List<Player> players = new LinkedList<>();
        for (int i = 0; i < nrPlayers; ++i) {
            if (names.get(i).equals("basic")) {
                players.add(new BasicPlayer(i));
            } else {
                if (names.get(i).equals("greedy")) {
                    players.add(new GreedyPlayer(i));
                } else {
                    if (names.get(i).equals("royal")) {
                        players.add(new RoyalPlayer(i));
                    } else {
                        int left = i - 1;
                        int right = i + 1;
                        if (left < 0) {
                            left = nrPlayers - 1;
                        } else {
                            if (right >= nrPlayers) {
                                right = 0;
                            }
                        }
                        players.add(new BribePlayer(i, left, right));
                    }
                }
            }
        }

        return players;
    }
}
