/** An interface to represent a Role in ONUW.
 *
 * @author Evan Gao
 */
public interface Role {
    /** Perform this role's specified action.
     *
     * @param game the Game to which this role should perform its action on
     */
    default void doAction(Game game) {

    };

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
     *  is not Village, and whose team wins even if the player with the role
     *  dies.
     *
     * @return true if this role is sacrificial, and false otherwise
     */
    default boolean isSacrificial() {
        return false;
    };

    /*
    boolean isSwapper(); //FIXME: Maybe?
     */
}
