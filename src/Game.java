import java.util.*;

/** A class that represents a game of ONUW. It is a singleton class (there
 *  should only be one object of type Game in existence).
 *
 * @author Evan Gao
 */
public class Game {
    /** The set of players in this game. */
    private Set<Player> _players;
    /** The list of roles in this game. */
    private List<Role> _roles;
    /** The three center cards. */
    private Card[] _middle;
    /** The state of the game. */
    private boolean _gameOver;
    /** The winning team of the game. */
    private Team _winningTeam;
    /** The singleton Game object. */
    private static Game _app;

    /** The private Game constructor. */
    private Game() {
        _players = new HashSet<Player>();
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
        assert players.size() > 2;
        _players = new HashSet<Player>(players);
    }

    /** A setter method that sets the roles in this game.
     *
     * @param roles a list containing the roles in this game
     */
    public void setRoles(List<Role> roles) {
        assert roles.size() == _players.size() + 3;
        _roles = new ArrayList<Role>(roles);
    }

    /** A getter method that returns the set of players in this game.
     *
     * @return the set of players in this game
     */
    public Set<Player> getPlayers() {
        return _players;
    }

    /** A getter method that returns the list of roles in this game.
     *
     * @return the list of roles in this game
     */
    public List<Role> getRoles() {
        return _roles;
    }

    /** Starts this game. Creates all the necessary cards from the given roles,
     *  and shuffles them. Then, initializes the middle three cards, and also
     *  distributes each player a card. */
    public void startGame() {
        assert _players.size() > 2 && _roles.size() == _players.size() + 3;
        _middle = new Card[3];
        ArrayList<Card> cards = new ArrayList<Card>();
        for (Role r: _roles) {
            cards.add(new Card(r));
        }

        Collections.shuffle(cards);
        for (int i = 0; i < 3; i++) {
            _middle[i] = cards.get(i);
        }

        int index = 3;
        for (Player p: _players) {
            p.initCard(cards.get(index));
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
