/** A class that represents a card in ONUW.
 *
 * @author Evan Gao
 */
public class Card {
    /** The card's role. */
    private Role _role;
    /** The Game this card belongs to. */
    private Game _game;

    /** The constructor for a Card object.
     *
     * @param role the role this card will have
     */
    public Card(Role role, Game game) {
        _role = role;
        _game = game;
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
}
