package Gameplay;

import Roles.Role;

/** A class that represents a card in ONUW.
 *
 * @author Evan Gao
 */
public class Card {
    /** This card's role. */
    private Role _role;
    /** The game this card belongs to. */
    private Game _game;
    /** True if this card is flipped up, and false otherwise. */
    private boolean _visible;

    /** The constructor for a Gameplay.Card object.
     *
     * @param role the role this card will have
     * @param game the game this card belongs to
     */
    public Card(Role role, Game game) {
        _role = role;
        _game = game;
        _visible = false;
    }

    /** A getter method that returns this card's role.
     *
     * @return this card's role
     */
    public Role getRole() {
        return _role;
    }

    /** A getter method that returns the game this card belongs to.
     *
     * @return the game this card belongs to
     */
    public Game getGame() {
        return _game;
    }

    /** Flips this card.
     *
     * @return true if this card is now visible, and false otherwise
     */
    public boolean flip() {
        _visible = !_visible;
        return _visible;
    }

    /** A getter method that returns true if this card is visible, and false
     *  otherwise.
     *
     * @return true if this card is visible, and false otherwise
     */
    public boolean isVisible() {
        return _visible;
    }
}
