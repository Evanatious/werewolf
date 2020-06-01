package Tokens;

/** An enum to represent and store the tokens in Bonus Pack 2:
 *  Mist of the Vampire, and Dagger of the Traitor.
 *
 * @author Evan Gao
 */
public enum Bonus2Token implements Artifact {
    VAMPIRE("Mist of the Vampire") {

    }, TRAITOR("Dagger of the Traitor") {

    };

    /** The constructor for a DaybreakToken object. */
    Bonus2Token(String name) {
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
