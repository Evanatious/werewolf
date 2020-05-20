/** An interface to represent a Role in ONUW.
 *
 * @author Evan Gao
 */
public interface Role {
    /** Perform this role's specified action.
     *
     * @param game the Game to which this role should perform its action on
     */
    void doAction(Game game);

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
}
