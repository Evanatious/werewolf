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
        for (Role r: StandardRole.values()) {
            if (r == StandardRole.VILLAGER) {
                for (int i = 0; i < 3; i++) {
                    temp.add(new Card(r, app));
                }
            } else if (r == StandardRole.WEREWOLF
                || r == StandardRole.MASON) {
                for (int i = 0; i < 2; i++) {
                    temp.add(new Card(r, app));
                }
            } else {
                temp.add(new Card(r, app));
            }
        }
        DEFAULT_CARDS = Collections.unmodifiableList(temp);
    }
    /*
    private boolean usingDefault;
    private boolean usingDaybreak;
    private boolean usingVampire;
    private boolean usingAlien;
    private boolean usingSuperVillain;
    private boolean usingBonus1;
    private boolean usingBonus2;
    private boolean usingBonus3;
     */


    /** The number of cards that belong in the middle. */
    public static final int NUM_CARDS_IN_MIDDLE = 3;
    /** The minimum number of players a game can have. */
    public static final int MIN_NUM_PLAYERS = 3;
    /** THe number of teams required for a game to be considered an
     *  "Epic Battle". */
    public static final int EPIC_BATTLE_NUM = 3;

    //TODO: Implement time limits between moves, etc.
    /*TODO: Implement alternate rules
       (EX: If Tanner dies, they alone win. If there are multiple Tanners, they
        both win if one is killed. If there is a lone minion with no werewolves,
        the minion becomes a lone werewolf and can look at the center card. If
        there is a lone minion, reshuffle (same idea-ish). If villagers kill a
        villager, they lose, etc.) */


    /** The set of house/alternate rules that are active. */
    private Set<Rule> _houseRules;


    /** The role that the doppelganger card becomes. Assumes that there is a
     *  doppelganger in play, and shouldn't be accessed otherwise. */
    private Role _doppelRole; //TODO: Might need one for every role that changes roles without swapping cards, like copycat and body snatcher
    /** The set of players in this game. They are ordered in order of their
     *  initial role priority, unless the role is a changeling, in which case
     *  their order is dynamically changed as a result of the change. */
    private SortedSet<Player> _players;
    /** The three center cards. */
    private Card[] _middle;
    /** The state of the game. */
    private boolean _gameOver;
    /** The winning team of this game. */
    private Set<Team> _winningTeam;
    /** The singleton Game object. */
    private static Game _app;
    /** The list of cards that are available for this game of ONUW. */
    private static List<Card> _cards;
    /** The teams that are currently present in this game. */
    private Set<Team> _teams;
    /** The set of roles that are currently in the game. (Note, sacrificial
     *  roles do not count toward a role in this game.)*/
    private static Set<Role> _roles;


    /** A helper method that returns the priority of a player in terms of the
     *  order in which their role takes their turn in the process of the game.
     *  The lower the number, the higher the priority.
     *
     * @param p a Player
     * @return an integer that corresponds to this player's priority in the
     * turn order
     */
    public static int getPriority(Player p) {
        return 0; //FIXME
    }

    /** A comparator that compares two Players based on their order in which
     *  their role takes turns in the process of the game. */
    private static final Comparator<Player> ROLE_ORDER =
        Comparator.comparingInt(Game::getPriority);

    /** The private Game constructor. */
    private Game() {
        _roles = new HashSet<Role>();
        _houseRules = new HashSet<Rule>();
        _teams = new HashSet<Team>();
        _players = new TreeSet<Player>(ROLE_ORDER); //TODO: Need to see if the comparator works the way it does with addAll
        _cards = new ArrayList<Card>();
        _winningTeam = new HashSet<Team>();
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

    /** A getter method that returns the set of all alternate/house rules that
     *  are currently applied in this game.
     *
     * @return the set of all alternate/house rules that are currently applied
     */
    public Set<Rule> getHouseRules() {
        return _houseRules;
    }

    /** A method that toggles the rule denoted "r" on if it is off, and off if
     *  it is on. Returns false if the rule is now toggled off, and true
     *  otherwise.
     *
     * @param r a rule
     * @return false if the rule is now toggled off, and true otherwise
     */
    public boolean toggleRule(Rule r) {
        boolean result = _houseRules.add(r);
        if (result) {
            _houseRules.remove(r);
        }
        return result;
    }

    /** A setter method that sets the players in this game.
     *
     * @param players a set containing the players in this game
     * @result true if the set of players has changed, and false otherwise
     */
    public boolean setPlayers(Set<Player> players) {
        assert players.size() >= MIN_NUM_PLAYERS
            && players.size() <= DEFAULT_CARDS.size() - NUM_CARDS_IN_MIDDLE;
        return _players.addAll(players);
    }

    /** A setter method that sets the cards in this game. Assumes that cards is
     *  taken from DEFAULT_CARDS (for now).
     *
     * @param cards a list containing the cards in this game
     * @return true if the set of cards has changed, and false otherwise
     */
    public boolean setCards(List<Card> cards) {
        assert cards.size() == _players.size() + NUM_CARDS_IN_MIDDLE;
        return _cards.addAll(cards);
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

    /** A getter method that returns the array of cards in the middle.
     *
     * @return the array of cards in the middle
     */
    public Card[] getMiddle() {
        return _middle;
    }

    /** A helper method that returns whether or not this game is an "Epic
     *  Battle". An Epic Battle means that there are three or more teams in
     *  play in this game (in play means that only player cards count towards
     *  a team being a team, and center cards don't count).
     *
     * @return true if this game is an "Epic Battle", and false otherwise
     */
    private boolean isEpic() {
        return _teams.size() >= EPIC_BATTLE_NUM;
    }

    /** Sets up this game. Shuffles all the given cards. Then, sets the middle
     *  cards, and distributes the rest of the cards to the players (one card
     *  each). */
    public void setUpGame() {
        assert _players.size() >= MIN_NUM_PLAYERS
            && _players.size() <= DEFAULT_CARDS.size() - NUM_CARDS_IN_MIDDLE
            && _cards.size() == _players.size() + NUM_CARDS_IN_MIDDLE;

        restart();
        _middle = new Card[NUM_CARDS_IN_MIDDLE];

        Collections.shuffle(_cards);
        for (int i = 0; i < 3; i++) {
            _middle[i] = _cards.get(i);
        }

        int index = 3;
        for (Player p: _players) {
            Card c = _cards.get(index);
            Role r = c.getRole();
            Team t = r.getTeam();
            if (t != StandardTeam.NEUTRAL && !r.isSacrificial()) {
                _teams.add(t); //FIXME: Doesn't account for a Copycat that might become something in the center
            }
            _roles.add(r);
            p.initCard(c);
            index++;
        }

        Set<Player> withoutRoles = new HashSet<Player>(_players);
        _players.clear();
        _players.addAll(withoutRoles); //TODO: Check that this works as intended with the comparator
    }

    /** A getter method that returns the state of this game.
     *
     * @return true if this game is over, and false otherwise
     */
    public boolean gameOver() {
        return _gameOver;
    }

    /** A method that returns the winning team(s) of this game. Assumes that
     *  this game is over.
     *
     * @return the winning team(s) of this game
     */
    public Set<Team> getWinningTeam() {
        assert gameOver();
        if (_winningTeam.size() == 0) {
            if (isEpic()) {
                //TODO: Implement the logic for Epic Battles
            } else {
                Set<Team> died = new HashSet<Team>();
                boolean villageExists = _teams.contains(StandardTeam.VILLAGE);
                boolean tannerDied = false;

                for (Player p : _players) {
                    if (!p.isAlive()) {
                        Role finalRole = p.getFinalRole();

                        if (finalRole.getTeam() != StandardTeam.VILLAGE
                            && !finalRole.isSacrificial()) { //died will not be affected by sacrificial roles
                            if (villageExists && !_houseRules.contains(
                                HouseRule.NOVILLAGEDEATH)) {
                                _winningTeam.add(StandardTeam.VILLAGE);
                                break;
                            } else {
                                died.add(finalRole.getTeam());
                            }
                        } else if (finalRole == StandardRole.TANNER
                            || (finalRole == Bonus2Role.APPRENTICE_TANNER
                            && !_roles.contains(StandardRole.TANNER))) {
                            if (_houseRules.contains(HouseRule.TANNERONLY)) {
                                _winningTeam.add(null);
                                break;
                            } else {
                                tannerDied = true;
                            }
                        } else if (villageExists) {
                            died.add(StandardTeam.VILLAGE);
                        }
                    }
                }

                if (_winningTeam.size() == 0) {
                    if (died.isEmpty() && _teams.size() == 1
                        && _teams.contains(StandardTeam.VILLAGE)) {
                        _winningTeam.add(StandardTeam.VILLAGE);
                    }
                    if (_houseRules.contains(HouseRule.NOVILLAGEDEATH)) {
                        if (!died.isEmpty() && !died.contains(StandardTeam.VILLAGE)) {
                            _winningTeam.add(StandardTeam.VILLAGE);
                        }
                    }
                    if (!tannerDied) {
                        if (_houseRules.contains(HouseRule.EVILSMUSTKILL)
                        && !died.isEmpty()) {
                            if (_winningTeam.isEmpty()) {
                                _winningTeam.add(null);
                            }
                        } else {
                            _winningTeam.addAll(_teams);
                            _winningTeam.removeAll(died);
                        }
                    } else {
                        _winningTeam.add(null); //No winner (Or Tanner won)
                    }
                }
            }
        }
        return _winningTeam;
    }

    /** A method that restarts the state of the game, setting everything back
     *  as if a completely new game were being started. */
    public void restart() {
        _roles.clear();
        _houseRules.clear();
        _teams.clear();
        _winningTeam.clear();
        _players.clear();
        _cards.clear();
        _gameOver = false;
    }
}
