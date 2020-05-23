/** An interface to represent a Role in ONUW.
 *
 * @author Evan Gao
 */
public interface Role {
    /** Perform this role's specified action.
     *
     * @param game the Game to which this role should perform its action on
     * @param currPlayer the Player who has this role
     */
    default void doAction(Game game, Player currPlayer) {

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
    };

     /** A helper method that finds the player with the specified role in the
     *  given game. Assumes that there is a player with the specified role.
     *
     * @param game the game to look for the player in
     * @param role the role of the player to look for
     * @return the player with the specified role in the given game
     */
    static Player findPlayer(Game game, Role role) {
        for (Player p: game.getPlayers()) {
            if (p.getInitRole() == role) {
                return p;
            }
        }
        return null; //Should never reach this line;
    }
}
