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
    private int numCardsInMiddle = 3;
    /** The minimum number of players a game can have. */
    public static final int MIN_NUM_PLAYERS = 3;
    /** THe number of teams required for a game to be considered an
     *  "Epic Battle". */
    public static final int EPIC_BATTLE_NUM = 3;
    /** The default amount of time allotted for discussion, in minutes. */
    public static final int DEFAULT_DISC_TIME = 5;
    /** The default amount of time allotted for a player to take their action,
     *  in seconds. */
    public static final int DEFAULT_ACTION_TIME = 10;

    //TODO: Implement time limits between moves, etc.


    /** The set of house/alternate rules that are active. */
    private Set<Rule> _houseRules;
    /** The time allotted for discussion, in between when the Day phase starts
     *  and the Voting phase starts. */
    private int _discussionTime = DEFAULT_DISC_TIME;
    /** The time allotted for each player to perform their action. */
    private int _actionTime = DEFAULT_ACTION_TIME;


    /** The list of players in this game. */
    private List<Player> _players;
    /** The center cards. */
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

    public static void dayPhase() {
        //TODO: maybe needs a prevoting phase and a postvoting phase?

    }

    public static void votingPhase() {
        //TODO: Maybe: a vote phase, a predeath phase, a death phase, a postdeath death phase, and then a calculate win phase
        //TODO: Also, IN the death phase, there are things like people being immune so needing to shift votes, or epic battle multiple votes, or Richochet Rhino
        //TODO: ALSO, Artifacts override Marks, cards, and Vote actions

    }

    /** A helper method that updates a player (because they now have a new role)
     *  in the TreeMultimap of roles to players.
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
        _middle = new Card[3];
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

    public TreeMultimap<Role, Player> getRoleMap() { //FIXME: Try to have it return a copy if possible
        return _rolesToPlayers;
    }

    /** A getter method that returns the set of all alternate/house rules that
     *  are currently applied in this game.
     *
     * @return the set of all alternate/house rules that are currently applied
     */
    public Set<Rule> getHouseRules() {
        return new HashSet<>(_houseRules);
    }

    /** Toggles the rule denoted "r" on if it is off, and off if
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

    /** Adds the provided player into this game.
     *
     * @param p a player
     */
    public void addPlayer(Player p) {
        _players.add(p);
    }

    /** Adds the provided card into this game. Assumes that cards is
     *  taken from DEFAULT_CARDS (for now).
     *
     * @param c a card
     * @param centerWolf true if the card is the center wolf card, and false
     *                   otherwise
     */
    public void addCard(Card c, boolean centerWolf) {
        if (c.getRole() == DaybreakRole.ALPHA_WOLF) {
            numCardsInMiddle = 4;
            _middle = new Card[4];
        }
        if (centerWolf) {
            assert numCardsInMiddle == 4;
            _middle[3] = c;
        }

        _cards.add(c);
    }

    /** A getter method that returns the list of players in this game.
     *
     * @return the list of players in this game
     */
    public List<Player> getPlayers() {
        return new ArrayList<>(_players);
    }

    /** A getter method that returns the list of cards in this game.
     *
     * @return the list of cards in this game
     */
    public List<Card> getCards() {
        return new ArrayList<>(_cards);
    }

    /** A getter method that returns the array of cards in the middle.
     *
     * @return the array of cards in the middle
     */
    public List<Card> getMiddle() {
        return Arrays.asList(_middle);
    }

    /** Swaps the provided player's card with the provided card from the center.
     *
     * @param p a player
     * @param c a card
     */
    public void swapPlayerAndCenter(Player p, Card c) {
        assert Arrays.asList(_middle).contains(c);
        int index = -1;
        for (int i = 0; i < _middle.length; i++) {
            if (_middle[i] == c) {
                index = i;
            }
        }

        _middle[index] = p.swapCard(c);
    }

    /** Swaps the two provided players' cards.
     *
     * @param p1 the first player
     * @param p2 the second player
     */
    public void swapPlayerAndPlayer(Player p1, Player p2) {
        Card p1OGCard = p1.getCard();
        p1.swapCard(p2.swapCard(p1OGCard));
    }

    /* TODO: Maybe? Idk how the animations will work
    public void swapAnimation(Card card1, Card card2) {

    }

     */

    /** A helper method that returns whether or not this game is an "Epic
     *  Battle". An Epic Battle means that there are three or more teams in
     *  play in this game (in play means that only player cards count towards
     *  a team being a team, and center cards don't count). Also, figures out
     *  the teams in play.
     *
     * @return true if this game is an "Epic Battle", and false otherwise
     */
    private boolean isEpic() {
        for (Player p: _players) {
            Role r = p.getEndRole();
            Team t = r.getTeam();
            if (t != StandardTeam.Neutral && !r.isSacrificial()) {
                _teams.add(t);
            }
        }
        return _teams.size() >= EPIC_BATTLE_NUM;
    } //TODO: Figure out what to do about the hero team

    /** Sets up this game. Shuffles all the given cards. Then, sets the middle
     *  cards, and distributes the rest of the cards to the players (one card
     *  each). */
    public void setUpGame() {
        assert _players.size() >= MIN_NUM_PLAYERS
            //&& _players.size() <= DEFAULT_CARDS.size() - numCardsInMiddle Needs to be changed so that the total number of players does not exceed the total amount of cards - 3 or 4
            && _cards.size() == _players.size() + numCardsInMiddle;

        restart();

        if (numCardsInMiddle == 4) { //Could also be _rolesToPlayers.containsKey(DaybreakRole.ALPHA_WOLF)
            _cards.remove(_middle[3]);
        }

        Collections.shuffle(_cards);
        for (int i = 0; i < 3; i++) {
            Card c = _cards.get(i);
            Role r = c.getRole();
            _middle[i] = c;
            _rolesToPlayers.put(r, null);
        }

        int index = 3;
        for (Player p: _players) {
            Card c = _cards.get(index);
            Role r = c.getRole();
            _rolesToPlayers.put(r, p);
            p.initCard(c);
            index++;
        }

        if (numCardsInMiddle == 4) {
            _cards.add(_middle[3]);
        }
    }

    /** A getter method that returns the state of this game.
     *
     * @return true if this game is over, and false otherwise
     */
    public boolean gameOver() {
        return _gameOver;
    }

    /** Returns the winning team(s) of this game. Assumes this game is over.
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
                boolean villageExists = _teams.contains(StandardTeam.Village);
                boolean tannerDied = false; //FIXME: What about Mad scientist

                for (Player p : _players) {
                    if (!p.isAlive()) {
                        Role finalRole = p.getEndRole();

                        if (finalRole.getTeam() != StandardTeam.Village
                            && !finalRole.isSacrificial()) { //died will not be affected by sacrificial roles
                            if (villageExists && !_houseRules.contains(
                                HouseRule.NOVILLAGEDEATH)) {
                                _winningTeam.add(StandardTeam.Village);
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
                            died.add(StandardTeam.Village);
                        }
                    }
                }

                if (_winningTeam.size() == 0) {
                    boolean nothingHappened = true;
                    if (died.isEmpty() && _teams.size() == 1 //No evils, and no one dies
                        && _teams.contains(StandardTeam.Village)) {
                        _winningTeam.add(StandardTeam.Village);
                        nothingHappened = false;
                    }
                    if (_houseRules.contains(HouseRule.NOVILLAGEDEATH)) {
                        if (!died.isEmpty() && !died.contains(StandardTeam.Village)) {
                            _winningTeam.add(StandardTeam.Village);
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
        return new HashSet<>(_winningTeam);
    }

    /** Restarts the state of the game, setting everything back as if a
     *  completely new game were being started. */
    public void restart() {
        _middle = new Card[3];
        _rolesToPlayers.clear();
        _houseRules.clear();
        _teams.clear();
        _winningTeam.clear();
        _players.clear();
        _cards.clear();
        _gameOver = false;
    }
}
