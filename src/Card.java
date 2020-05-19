/** A class that represents a card in ONUW.
 *
 * @author Evan Gao
 */
public class Card {
    /** The card's role. */
    private Role _role;

    /** The constructor for a Card object.
     *
     * @param role the role this card will have
     */
    public Card(Role role) {
        _role = role;
    }

    /** A getter method that returns this card's role.
     *
     * @return this card's role
     */
    public Role getRole() {
        return _role;
    }
}
