/** An enum to represent and store the tokens in a game of ONUW Daybreak:
 *  Claw of the Werewolf, Brand of the Villager, Cudgel of the Tanner, Void
 *  of Nothingness, Mask of Muting, and Shroud of Shame.
 *
 * @author Evan Gao
 */
public enum DaybreakToken implements TokenType {
    WEREWOLF("Claw of the Werewolf") {

    }, VILLAGER("Brand of the Villager") {

    }, TANNER("Cudgel of the Tanner") {

    }, NOTHING("Void of Nothingness"),
    MUTING("Mask of Muting") {

    }, SHAME("Shroud of Shame") {

    };

    /** The name of the token. */
    private String _name;

    /** The constructor for a DaybreakToken object. */
    DaybreakToken(String name) {
        _name = name;
    }

    /**
     * Apply this token's specified status.
     *
     * @param game the Game to which this status should be applied on
     */
    @Override
    public void applyStatus(Game game) {

    }

    /**
     * A getter method that returns the name of this token.
     *
     * @return the name of this token
     */
    @Override
    public String getName() {
        return _name;
    }
}
