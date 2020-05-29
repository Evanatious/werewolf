package Roles;

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
    DOPPELCAT1, //If a Doppelganger views a Copycat card, they do the copycat action and then immediately do that new role's action as well
    DOPPELCAT2, //If a Doppelganger views a Copycat card, they do the copycat action, but then do the new role's action when the new role's action is called FIXME: If copycat and doppelganger are the same role, who goes first?
    ALLATONCE, //Everybody does their role all at once so the night phase is very quick, instead of one after the other FIXME: Might not actually be possible, we'll see...Update: YUP, think Shield Token. Also, usually you can't do this because two people would wake up at the same time that usually shouldn't
    MIRRORCAT, //Mirror man and Copycat can view the same role FIXME: If they're both the same role, who goes first??
    MANDATORY, //All "may" actions are now mandatory
    EVILSMUSTKILL; //Non-villagers lose if no one is killed
}
