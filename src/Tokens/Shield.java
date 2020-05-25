package Tokens;

/** An enum to represent and store the Shield Token in a game of ONUW Daybreak.
 *
 * @author Evan Gao
 */
public enum Shield implements Token {
    SHIELD;

    @Override
    public String getName() {
        return "Shield Token";
    }
}
