package Tokens;

/** An enum to represent and store the tokens in Bonus Pack 1:
 *  Bow of the Hunter, Cloak of the Prince, and Sword of the Bodyguard.
 *
 * @author Evan Gao
 */
public enum Bonus1Token implements Token { //FIXME: Are these Marks?
    HUNTER("Bow of the Hunter") {

    }, PRINCE("Cloak of the Prince") {

    }, BODYGUARD("Sword of the Bodyguard") {

    };

    /** The constructor for a DaybreakToken object. */
    Bonus1Token(String name) {
        _name = name;
    }

    /** The name of the token. */
    private String _name;

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
