package Roles;

import Gameplay.Game;
import Gameplay.Player;
import Roles.Teams.Team;
import com.google.common.collect.TreeMultimap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** An interface to represent a Roles.Role in ONUW.
 *
 * @author Evan Gao
 */
public interface Role {
    /** Perform this role's specified action.
     *
     * @param currPlayer the Gameplay.Player who has this role
     */
    default void doAction(Player currPlayer) {

    }

    /** A getter method that returns what team this role belongs to.
     *
     * @return the team this role belongs to
     */
    Team getTeam();

    /** A getter method that returns the name of this role.
     *
     * @return the name of this role
     */
    String getName();

    /** A getter method that returns true if this role is sacrificial, and
     *  false otherwise. A sacrificial role is defined as a role whose team
     *  is not Village (or Neutral??), and whose team wins even if the player with the role
     *  dies.
     *
     * @return true if this role is sacrificial, and false otherwise
     */
    default boolean isSacrificial() {
        return false;
    }

    /** A getter method that returns true if this role is a changeling, and
     *  false otherwise. A changeling role is defined as a role that becomes a
     *  different role, while the card remains the same.
     *
     * @return true if this role is sacrificial, and false otherwise
     */
    default boolean isChangeling() {
        return false;
    }

    /** A getter method that returns true if this role performs their action
     *  immediately when a changeling changes into this role, and false
     *  otherwise.
     *
     * @return true if this role performs their action immediately when a
     * changeling changes into this role, and false otherwise.
     */
    default boolean performImmediately() {
        return false;
    }

    /** A getter method that returns the final role of this role (this may be
     *  different than the role on the card because of changelings).
     *
     * @return the final role that this role is
     */
    default Role getFinalRole() {
        return this;
    }

    /** A getter method that returns true if this role has won in the given
     *  game, and false otherwise. Assumes the game is over.
     *
     * @param game a game
     * @return true if this role has won in the given game, and false otherwise
     */
    default boolean won(Game game) {
        return game.getWinningTeam().contains(getTeam());
    }

     /** A helper method that finds the player(s) with the specified role in the
     *  given game. Assumes that there is at least one player with the specified
     *  role.
     *
     * @param game the game to look for the player(s) in
     * @param role the role of the player(s) to look for
     * @return the player(s) with the specified role in the given game
     */
    static Set<Player> findPlayersWithRole(Game game, Role role) {
        TreeMultimap<Role, Player> map = game.getRoleMap();
        return new HashSet<>(map.get(role));
    }

    /** A getter method that returns the phase during which this role operates.
     *
     * @return the phase during which this role operates
     */
    default Phase getPhase() {
        return StandardPhase.NONE;
    }

    //default void changeTeam(); ?? TODO: Maybe implement a way to change a team, since so many roles have an initial team but can easily switch teams
    //TODO: Or, make team an instance variable in player...nah that's only for marks and stuff, which is covered in player already
}
