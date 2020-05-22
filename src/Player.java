import java.util.*;

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

    /** Displays the given info to this player. THIS NEEDS HELLA WORK AND DECKING OUT
     * Need to figure out what the parameter should be. Is it a String or what?
     * Might have a parameter to determine what type of info is displayed (EX:
     * if your has changed, what a role is, what cards are available to you, etc.)
     */
    public void displayInfo() {
        //TODO
    }

    /** Prompts this player when they are given a choice that is optional (has
     *  "may" in its phrasing), and has them make a selection.
     *
     * @param message the message that is displayed along with the choice
     * @return true if this player decides to take the action, and false
     * otherwise
     */
    public boolean promptMayAction(String message) {
        return true; //FIXME
    }

    /** Prompts this player to pick an amount of cards equal to numCards from
     *  the middle cards.
     *
     * @param message the message that is displayed along with the choice
     * @param numCards the number of cards that will be selected
     * @return the Card(s) that this player chooses
     */
    public Card[] promptChooseCardAction(String message, int numCards) {
        List<Object> cardPool = new ArrayList<Object>();
        Card[] mid = _game.getMiddle();
        for (int i = 0; i < mid.length; i++) {
            cardPool.add(mid[i]);
        }
        Card[] selected =
            Arrays.copyOf(choose(cardPool, numCards), numCards, Card[].class);
        return selected;
    }

    /** Prompts this player to pick a number of players equal to numPlayers (the
     *  chosen player(s) must be someone other than this player).
     *
     * @param message the message that is displayed along with the choice
     * @param numPlayers the number of players that will be selected
     * @return the Player(s) that this player chooses
     */
    public Player[] promptChoosePlayerAction(String message, int numPlayers,
                                             boolean selfAllowed) {
        HashSet<Object> playerPool = new HashSet<Object>(_game.getPlayers());
        if (!selfAllowed) {
            playerPool.remove(this);
        }
        return Arrays.copyOf(choose(playerPool, numPlayers),
            numPlayers, Player[].class);
    }

    /** A helper method that helps a player choose from a collection/pool of
     *  objects (could be players, cards, etc.).
     *
     * @param c the collection to choose from
     * @param numObjects the number of objects to choose
     * @return an array of the objects chosen
     */
    public Object[] choose(Collection<Object> c, int numObjects) {
        return null; //FIXME: Maybe needs a String message?
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
     * @return the Card this player used to have
     */
    public Card swapCard(Card card) {
        Card result = _card;
        _card = card;
        return result;
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
        //FIXME: Special cases: Doppelganger, Assassin, Cupid, Diseased, Instigator, Copycat, Apprentice Assassin
        if (true) {
            return true;
        } else {
            return getFinalRole().getTeam() == _game.getWinningTeam();
        }
    }

    /** A getter method that returns this player's card.
     *
     * @return this player's card
     */
    public Card getCard() {
        return _card;
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

    /** A getter method that returns this player's role at the start of the game.
     *
     * @return the role this player started with
     */
    public Role getInitRole() {
        return _initRole;
    }
}
