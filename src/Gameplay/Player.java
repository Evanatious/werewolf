package Gameplay;

import Roles.AlienRole;
import Roles.Role;
import Roles.StandardRole;
import Roles.VampireRole;
import Tokens.*;

import java.util.*;

/** A class that represents a player in ONUW.
 *
 * @author Evan Gao
 */
public class Player {
    /** The word 'Yes'. */
    public static final String YES = "Yes";
    /** The word 'No'. */
    public static final String NO = "No";
    /** The list of options when prompted with a "May" ability: Yes or No. */
    public static final List<String> MAY_OPTIONS;
    static {
        MAY_OPTIONS = new ArrayList<>();
        MAY_OPTIONS.add(YES);
        MAY_OPTIONS.add(NO);
    }
    /** The name of this player. */
    private String _name;
    /** The initial role (that is, the role that this player starts off with) of
     *  this player. This role determines when a player takes their action, even
     *  if their card has been changed. When a changeling changes roles,
     *  _initRole also changes to reflect that. */
    private Role _initRole;
    /** The card that this player possesses. */
    private Card _card;
    /** The set of tokens this player possesses. */
    private Set<Token> _tokens;
    /** The status of this player's mortality. */
    private boolean _alive;
    /** The status of this player's victory. */
    private boolean _won;
    /** The game that this player is a part of. */
    private Game _game;
    /** The player this player voted for. */
    private Player _vote;

    /** The constructor for a Player object.
     *
     * @param name the name of the player
     * @param game the Game this player belongs to
     */
    public Player(String name, Game game) {
        _name = name;
        _game = game;
        _tokens = new HashSet<>();
        _alive = true;
        _won = true;
    }

    /** Shows the provided card to this player.
     *
     * @param message a message that accompanies the reveal of the card
     * @param card the card that is revealed to this player
     */
    public void showCard(String message, Card card) {
        //TODO
    }

    /** Shows the provided players to this player.
     *
     * @param message a message that accompanies the reveal of the players
     * @param players the set of players that is revealed to this player
     */
    public void showPlayers(String message, List<Player> players) {
        //TODO
    }

    /** Displays the given info to this player.
     *
     * @param info the info that is displayed to this player
     */
    public void displayInfo(String info) {
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
        //TODO: print on the player's screen the message
        //TODO: print on the player's screen: "Would you like to take this action?"
        String choice = (String) choose("Would you like to take this action?",
            Collections.singletonList(MAY_OPTIONS)); //TODO: Check that singletonlist works
        return choice.equals(YES);
    }

    /** Adds the provided token to the set of tokens this player possesses.
     *
     * @param t a token
     */
    public void receiveToken(Token t) {
        _tokens.add(t);
    }

    /** A getter method that returns the set of this player's tokens.
     *
     * @return the set of this player's tokens
     */
    public Set<Token> getTokens() {
        return new HashSet<>(_tokens);
    }

    public void swapMark(Token markToken) {
        //TODO: Maybe try and do a more general swapToken method
        //Assumes that there is only one of each kind of token (i.e. one artifact, one mark, one whatever new tokens there are)
        //At the point in the game where this is called, each player should only have one token, their mark (at least according to the current rules, who knows what new stuff will be added)
        assert _tokens.size() == 1;
        _tokens.clear();
        _tokens.add(markToken);
    }

    /** Prompts this player to pick a card from a list of cards.
     *
     * @param message the message that is displayed along with the choice
     * @param pool the set of cards to exclude from the choice
     * @return the card that this player chooses
     */
    public Card chooseCard(String message, List<Card> pool) {
        List<Object> cardPool = new ArrayList<>(pool);
        return (Card) choose(message, cardPool);
    }



    /** Prompts this player to pick a player from the provided pool of players.
     *  Will automatically remove any players that are invalid choices (For
     *  example, if a player has a shield token).
     *  FIXME: Or, should I make it so that in choose, it gets rid of them? At the least,
     *  FIXME: there should be an interface that greys out a player if they have the shield token or something and explains why
     *
     * @param message the message that is displayed along with the choice
     * @param pool the pool of players to choose from
     * @return the player that this player chooses
     */
    public Player choosePlayer(String message, List<Player> pool) {
        List<Object> playerPool = new ArrayList<>(pool);
        for (Object p: playerPool) {
            if (!((Player) p).getTokens().contains(Shield.SHIELD)) {
                playerPool.add(p);
            }
        }
        return (Player) choose(message, playerPool);
    } //TODO: Maybe make a method that lets someone choose multiple people

    /** Makes this player vote for a player other than themselves. */
    public void vote() {
        List<Player> otherPlayers = new ArrayList<>(getGame().getPlayers());
        otherPlayers.remove(this);
        _vote = choosePlayer("Vote for another player (You may not vote for yourself): ", otherPlayers);
    }

    /** A getter method that returns the player this player voted for.
     *
     * @return the player this player voted for
     */
    public Player voted() {
        return _vote;
    }

    /** A helper method that helps a player choose from a list/pool of
     *  objects (could be players, cards, etc.).
     *
     * @param message the message to accompany the choice
     * @param c the list to choose from
     * @return an array of the objects chosen
     */
    public Object choose(String message, List<Object> c) { //TODO: Might have to create an interface Choosable or something,
        //TODO: so that when actually choosing something, you can click on the actual thing rather than some text buttons
        /*
        if (c.getClass() instanceof Chooseable){

        }

         */
        return null; //FIXME: Maybe needs a String message?

    }

    /** Kills this player (if they are able to be killed). */
    public void kill() {
        if (!_tokens.contains(Bonus1Token.PRINCE)
            || _tokens.contains(VampireMark.LOVE)) {
            _alive = false;
        }
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

    /** Makes this player lose (for example, if they voted for Disease). */
    public void lose() {
        _won = false;
    }

    /** A getter method that returns whether or not this player won. Assumes
     *  that the game is over.
     *
     * @return true if this player has won, and false otherwise
     */
    public boolean isWinner() {
        assert _game.gameOver();

        if (!_won) {
            return false;
        } else {
            if (_tokens.size() > 0) {
                //FIXME: Special cases: Marks (Artifacts taken card of in getEndRole
                //TODO: Love (I think actually it's handled with lose() now, idk
                //TODO: Traitor
                return false;
            } else {
                return getEndRole().won(_game, this);
            }
        }
    }

    /** A getter method that returns this player's card.
     *
     * @return this player's card
     */
    public Card getCard() {
        return _card;
    }

    /** A getter method that returns the game this player belongs to.
     *
     * @return the game this player belongs to
     */
    public Game getGame() {
        return _game;
    }

    /** A getter method that returns this player's name.
     *
     * @return this player's name
     */
    public String getName() {
        return _name;
    }

    //FIXME: Is this necessary? I currently have it for the choose method, so that it can automatically just print out whatever's being chosen
    @Override
    public String toString() {
        return getName();
    }

    /** A setter method that sets this player's role to the indicated role.
     *  Assumes that this player's card is a changeling.
     *
     * @param newRole the role this player now becomes
     */
    public void changeRole(Role newRole) {
        assert _card.getRole().isChangeling();
        _initRole = newRole;
    }

    /** A getter method that returns this player's role at the end of the game.
     *
     * @return the role dictated by the card this player has at the end of the
     * game
     */
    public Role getEndRole() {
        if (_tokens.contains(DaybreakArtifact.WEREWOLF)) {
            return StandardRole.WEREWOLF;
        } else if (_tokens.contains(Bonus2Token.VAMPIRE)) {
            return VampireRole.VAMPIRE;
        } else if (_tokens.contains(Bonus3Token.ALIEN_ARTIFACT)) {
            return AlienRole.ALIEN;
        } else if (_tokens.contains(DaybreakArtifact.TANNER)) {
            return StandardRole.TANNER;
        } else if (_tokens.contains(DaybreakArtifact.VILLAGER)
            || _tokens.contains(Bonus1Token.PRINCE)
            || _tokens.contains(Bonus1Token.BODYGUARD)
            || _tokens.contains(Bonus1Token.HUNTER)) {
            return StandardRole.VILLAGER;
        } else if (_tokens.contains(VampireMark.VAMPIRE)) {
            return VampireRole.VAMPIRE;
        } else {
            return getCard().getRole().getFinalRole();
        }
    }

    /** A getter method that returns this player's role at the start of the game.
     *
     * @return the role this player started with
     */
    public Role getInitRole() {
        return _initRole;
    }
}
