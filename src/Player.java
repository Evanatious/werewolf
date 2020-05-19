/** A class that represents a player in ONUW.
 *
 * @authoer Evan Gao
 */
public class Player {
    /** The name of this player. */
    private String _name;
    /** The initial role (that is, the role that this player starts off with) of
     * this player. */
    private Role _initRole;
    /** The card that this player possesses. */
    private Card _card;
    /** The status of this player's mortality. */
    private boolean _alive;
    /** The game that this player is a part of. */
    private Game _game;

    /** The constructor for a Player object.
     *
     * @param name the name of the player
     * @param game the Game this player belongs to
     */
    public Player(String name, Game game) {
        _name = name;
        _game = game;
        _alive = true;
    }

    /** Kills this player by setting their _alive status to false. */
    public void kill() {
        _alive = false;
    }

    /** Gives this player their card along with their initial role (which is
     *  the role specified by the card they receive).
     *
     * @param card the initial Card this player gets at the start of a game
     */
    public void initCard(Card card) {
        _card = card;
        _initRole = card.getRole();
    }

    /** Replaces this player's card with the argument named 'card'.
     *
     * @param card the Card that this player will now have
     */
    public void swapCard(Card card) {
        _card = card;
    }

    /** A getter method that returns whether or not this player is alive.
     *
     * @return true if this player is alive, and false otherwise
     */
    public boolean isAlive() {
        return _alive;
    }

    /** A getter method that returns whether or not this player won. Assumes
     *  that the game is over.
     *
     * @return true if this player has won, and false otherwise
     */
    public boolean isWinner() {
        assert _game.gameOver();
        return _card.getRole().getTeam() == _game.getWinningTeam();
    }

    /** A getter method that returns this player's name.
     *
     * @return this player's name
     */
    public String getName() {
        return _name;
    }

    /** A getter method that returns this player's role at the end of the game.
     *
     * @return the role dictated by the card this player has at the end of the
     * game
     */
    public Role getFinalRole() {
        return _card.getRole();
    }
}
