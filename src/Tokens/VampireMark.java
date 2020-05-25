package Tokens;

/** An enum to represent and store the marks in a game of One Night Ultimate
 *  Vampire: Mark of the Vampire, Mark of Fear, Mark of the Bat, Mark of
 *  Disease, Mark of Love, Mark of the Traitor, Mark of Clarity, and Mark of
 *  the Assassin.
 *
 * @author Evan Gao
 */
public enum VampireMark implements Mark {
    VAMPIRE("Mark of the Vampire") {

    }, FEAR("Mark of Fear") {

    }, BAT("Mark of the Bat") {

    }, DISEASE("Mark of Disease") {

    }, LOVE("Mark of Love") {

    }, TRAITOR("Mark of the Traitor") {

    }, CLARITY("Mark of Clarity") {

    }, ASSASSIN("Mark of the Assassin") {

    };

    /** The name of the token. */
    private String _name;

    /** The constructor for a DaybreakToken object. */
    VampireMark(String name) {
        _name = name;
    }

    @Override
    public String getName() {
        return _name;
    }
}
