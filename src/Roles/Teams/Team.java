package Roles.Teams;

import Gameplay.Game;
import Gameplay.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/** An interface for representing teams in ONUW.
 *
 * @author Evan Gao
 */
public interface Team {
    /** A helper method that returns the set of all players in the provided game
     *  that are on this team.
     *
     * @param g a game
     * @return all players in the provided game that are on this team
     */
    default List<Player> findAll(Game g) {
        ArrayList<Player> result = new ArrayList<>();
        for (Player p: g.getPlayers()) {
            if (p.getCard().getRole().getFinalRole().getTeam() == this) {
                result.add(p);
            }
        }
        return result;
    }
}
