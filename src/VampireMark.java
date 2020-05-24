/** An enum to represent and store the marks in a game of One Night Ultimate
 *  Vampire: FIXME
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
