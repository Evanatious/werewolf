import java.util.*;

/** A class that represents a game of ONUW. It is a singleton class (there
 *  should only be one object of type Game in existence).
 *
 * @author Evan Gao
 */
public class Game {
    /** The list of ALL cards that are available for game of ONUW. By default,
     *  there are sixteen cards: 1 Doppelganger, 2 Werewolves, 1 Minion, 2
     *  Masons, 1 Seer, 1 Robber, 1 Troublemaker, 1 Drunk, 1 Insomniac, 3
     *  Villagers, 1 Hunter, and 1 Hunter. */
    public static final List<Card> DEFAULT_CARDS; //FIXME: Might be unnecessary?
    static {
        Game app = getApp();
        List<Card> temp = new ArrayList<Card>();
        for (Role r: StandardRoles.values()) {
            if (r == StandardRoles.VILLAGER) {
                for (int i = 0; i < 3; i++) {
                    temp.add(new Card(r, app));
                }
            } else if (r == StandardRoles.WEREWOLF
                || r == StandardRoles.MASON) {
                for (int i = 0; i < 2; i++) {
                    temp.add(new Card(r, app));
                }
            } else {
                temp.add(new Card(r, app));
            }
        }
        DEFAULT_CARDS = Collections.unmodifiableList(temp);
    }
    /** The number of cards that belong in the middle. */
    public static final int NUM_CARDS_IN_MIDDLE = 3;
    /** The minimum number of players a game can have. */
    public static final int MIN_NUM_PLAYERS = 3;
    /** The set of players in this game. */
    private Set<Player> _players;
    /** The three center cards. */
    private Card[] _middle;
    /** The outer cards (should be # of players) */
    private Card[] _outer; //FIXME: Unnecessary?
    /** The state of the game. */
    private boolean _gameOver;
    /** The winning team of this game. */
    private Team _winningTeam;
    /** The singleton Game object. */
    private static Game _app;
    /** The list of cards that are available for this game of ONUW. */
    private static List<Card> _cards;

    /** The private Game constructor. */
    private Game() {
        _players = new HashSet<Player>();
        _cards = new ArrayList<Card>();
    }

    /** A getter method that returns _app if it already exists, and creates it
     *  if it doesn't.
     *
     * @return the singleton Game object
     */
    public static Game getApp() {
        if (_app == null) {
            _app = new Game();
        }
        return _app;
    }

    /** A setter method that sets the players in this game.
     *
     * @param players a set containing the players in this game
     */
    public void setPlayers(Set<Player> players) {
        assert players.size() >= MIN_NUM_PLAYERS
            && players.size() <= DEFAULT_CARDS.size() - NUM_CARDS_IN_MIDDLE;
        _players = new HashSet<Player>(players);
    }

    /** A setter method that sets the cards in this game. Assumes that cards is
     *  taken from DEFAULT_CARDS (for now).
     *
     * @param cards a list containing the cards in this game
     */
    public void setCards(List<Card> cards) {
        assert cards.size() == _players.size() + NUM_CARDS_IN_MIDDLE;
        _cards = new ArrayList<Card>(cards);
    }

    /** A getter method that returns the set of players in this game.
     *
     * @return the set of players in this game
     */
    public Set<Player> getPlayers() {
        return _players;
    }

    /** A getter method that returns the list of cards in this game.
     *
     * @return the list of cards in this game
     */
    public List<Card> getCards() {
        return _cards;
    }

    /** Starts this game. Shuffles all the given cards. Then, sets the middle
     *  three cards, and also distributes each player a card. */
    public void startGame() {
        assert _players.size() >= MIN_NUM_PLAYERS
            && _players.size() <= DEFAULT_CARDS.size() - NUM_CARDS_IN_MIDDLE
            && _cards.size() == _players.size() + NUM_CARDS_IN_MIDDLE;

        _middle = new Card[NUM_CARDS_IN_MIDDLE];

        Collections.shuffle(_cards);
        for (int i = 0; i < 3; i++) {
            _middle[i] = _cards.get(i);
        }

        int index = 3;
        for (Player p: _players) {
            p.initCard(_cards.get(index));
            index++;
        }
    }

    /** A getter method that returns the state of this game.
     *
     * @return true if this game is over, and false otherwise
     */
    public boolean gameOver() {
        return _gameOver;
    }

    /** A getter method that returns the winning team of this game. Assumes that
     *  this game is over.
     *
     * @return the winning team of this game
     */
    public Team getWinningTeam() {
        assert gameOver();
        return _winningTeam;
    }
}
