/** An interface to represent a token type in ONUW.
 *
 * @author Evan Gao
 */
public interface TokenType {
    /** Apply this token's specified status.
     *
     * @param game the Game to which this status should be applied on
     */
    default void applyStatus(Game game) {

    }

    /** A getter method that returns the name of this token.
     *
     * @return the name of this token
     */
    String getName();

    /** A getter method that returns true if this token is visible to other
     *  players (like the Shield token), and false otherwise.
     *
     * @return true if this token is visible to other players, and false
     * otherwise
     */
    default boolean isVisible() {
        return true;
    };
}
