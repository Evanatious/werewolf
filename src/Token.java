/** A class to represent a Token in ONUW.
 *
 * @author Evan Gao
 */
public class Token {
    /** This card's type. */
    private TokenType _type;
    /** The Game this card belongs to. */
    private Game _game;

    /** The constructor for a Token object.
     *
     * @param type the type of this token
     * @param game the game this token belongs to
     */
    public Token(TokenType type, Game game) {
        _type = type;
        _game = game;
    }

    /** A getter method that returns this token's type.
     *
     * @return this token's type
     */
    public TokenType getType() {
        return _type;
    }

    /** A getter method that returns the game this card belongs to.
     *
     * @return the game this card belongs to
     */
    public Game getGame() {
        return _game;
    }
}
