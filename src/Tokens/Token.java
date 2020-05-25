package Tokens;

import Gameplay.Player;

/** An interface to represent a token in ONUW.
 *
 * @author Evan Gao
 */
public interface Token {
    /** Apply this token's specified status.
     *
     * @param currPlayer the player on which this status should be applied
     */
    default void applyStatus(Player currPlayer) {

    }

    /** A getter method that returns the name of this token.
     *
     * @return the name of this token
     */
    String getName();

    /** A getter method that returns true if this token is visible to other
     *  players (like the Tokens.Shield token), and false otherwise.
     *
     * @return true if this token is visible to other players, and false
     * otherwise
     */
    default boolean isVisible() {
        return true;
    };
}
