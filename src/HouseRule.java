/** An enum to represent and store the some alternative/house rules in a game
 *  of ONUW.
 *
 * @author Evan Gao
 */
public enum HouseRule implements Rule {
    TANNERONLY, //If a Tanner dies, only the Tanner wins, and no one else
    TANNERTEAM, //If a Tanner dies, all Tanners win
    MINIONBECOMESWW, //If there is a lone minion, they "become" a lone werewolf and can look at one of the center cards
    NOVILLAGEDEATH, //Villagers lose if a villager is killed
    DOPPELCAT, //If a Doppelganger views a Copycat card, they do the copycat action and then immediately do that new role's action as well
    EVILSMUSTKILL; //Non-villagers lose if no one is killed
}
