package comparator;

import players.Player;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    /**
     *
     * @param playerOne first player
     * @param playerTwo second player
     * @return an integer that will be use for create the ranking
     */
    public int compare(final Player playerOne, final Player playerTwo) {
        return (playerOne.getScore() - playerTwo.getScore() > 0 ? 1 : -1);
    }
}
