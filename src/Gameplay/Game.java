package Gameplay;

import Roles.*;
import Roles.Teams.*;
import com.google.common.collect.TreeMultimap;

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


    /** The set of house/alternate rules that are active. */
    private Set<Rule> _houseRules;


    /** The list of players in this game. */
    private List<Player> _players;
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
    /** The teams that are currently present in this game. (Note, sacrificial
     *  roles do not count toward a role in this game.) */
    private Set<Team> _teams;
    /** The mapping of roles that are currently in this game to the players in
     *  this game that have that role. This changes dynamically when changeling
     *  roles change roles (except for certain cases where the changeling
     *  performs their new role immediately. This mapping helps to decide the
     *  turn order in this game. */
    private static TreeMultimap<Role, Player> _rolesToPlayers;

    public static void earlyPhase() {
        //TODO: Maybe unnecessary, or can be a helper method for nightPhase
    }

    public static void duskPhase() {
        //TODO: Maybe unnecessary, or can be a helper method for nightPhase
    }

    public static void nightPhase() {
        //TODO: Might combine all these phases into one general method that can take in any phase
    }

    public static void votingPhase() {
        //TODO
    }

    /** A helper method that updates a player (because they are a changeling
     *  that now has a new role) in the TreeMultimap of roles to players.
     *
     * @param p a player
     * @param oldRole the player's old role (to find them in the TreeMultimap
     */
    public void updatePlayer(Player p, Role oldRole) {
        _rolesToPlayers.remove(oldRole, p);
        _rolesToPlayers.put(p.getInitRole(), p);
    }

    /** A helper method that returns the priority of a role in terms of the
     *  order in which the role takes their turn in the process of the game.
     *  The lower the number, the higher the priority.
     *
     * @param r a Roles.Role
     * @return an integer that corresponds to this role's priority in the
     * turn order
     */
    public static float getPriority(Role r) {
        return 0; //FIXME
    }

    /** A comparator that compares two Roles based on their order in which
     *  the role takes turns in the process of the game. NOTE: This is
     *  inconsistent with .equals, because in enums , .equals is final, but
     *  changeling roles might be different roles, and still be considered
     *  equal. */
    private static final Comparator<Role> ROLE_COMPARATOR =
        (o1, o2) -> (int) (getPriority(o1) - getPriority(o2)) * 10;

    //So...a doppelganger-role has LOWER priority than the actual role (i.e. they go directly after the actual role)

    /** A comparator that compares two Players based on their order in which
     *  their role takes turns in the process of the game. NOTE: This is
     *  inconsistent with .equals, because in enums , .equals is final, but
     *  changeling roles might be different roles, and still be considered
     *  equal. */
    private static final Comparator<Player> PLAYER_COMPARATOR = (o1, o2) ->
        (int) (getPriority(o1.getInitRole()) -
            getPriority(o2.getInitRole())) * 10;

    /** The private Game constructor. */
    private Game() {
        _rolesToPlayers =
            TreeMultimap.create(ROLE_COMPARATOR, PLAYER_COMPARATOR);
        _houseRules = new HashSet<>();
        _teams = new HashSet<>();
        _players = new ArrayList<>();
        _cards = new ArrayList<>();
        _winningTeam = new HashSet<>();
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

    public TreeMultimap<Role, Player> getRoleMap() {
        return _rolesToPlayers;
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

    /** A getter method that returns the list of players in this game.
     *
     * @return the list of players in this game
     */
    public List<Player> getPlayers() {
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
    } //TODO: Figure out what to do about the hero team

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
                _teams.add(t); //FIXME: Doesn't account for a Copycat that might become something in the center...Might fix on the spot when the copycat takes their role
            }
            _rolesToPlayers.put(r, p);
            p.initCard(c);
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
            } else { //TODO: This whole thing needs a LOT of testing
                Set<Team> died = new HashSet<>();
                boolean villageExists = _teams.contains(StandardTeam.VILLAGE);
                boolean tannerDied = false;

                for (Player p : _players) {
                    if (!p.isAlive()) {
                        Role finalRole = p.getEndRole();

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
                            && !_rolesToPlayers.containsKey(StandardRole.TANNER))) {
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
                    boolean nothingHappened = true;
                    if (died.isEmpty() && _teams.size() == 1 //No evils, and no one dies
                        && _teams.contains(StandardTeam.VILLAGE)) {
                        _winningTeam.add(StandardTeam.VILLAGE);
                        nothingHappened = false;
                    }
                    if (_houseRules.contains(HouseRule.NOVILLAGEDEATH)) {
                        if (!died.isEmpty() && !died.contains(StandardTeam.VILLAGE)) {
                            _winningTeam.add(StandardTeam.VILLAGE);
                            nothingHappened = false;
                        }
                    }
                    if (!tannerDied && !villageExists) {
                        if (_houseRules.contains(HouseRule.EVILSMUSTKILL)
                        && !died.isEmpty()) {
                            if (_winningTeam.isEmpty()) {
                                _winningTeam.add(null); //no one died
                            }
                        } else {
                            _winningTeam.addAll(_teams);
                            _winningTeam.removeAll(died);
                        }
                        nothingHappened = false;
                    }
                    if (nothingHappened) {
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
        _rolesToPlayers.clear();
        _houseRules.clear();
        _teams.clear();
        _winningTeam.clear();
        _players.clear();
        _cards.clear();
        _gameOver = false;
    }
}
