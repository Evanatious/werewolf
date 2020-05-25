package Tokens;

/** An enum to represent and store the token in Bonus Pack 3:
 *  Alien Artifact
 *
 * @author Evan Gao
 */
public enum Bonus3Token implements Artifact {
    ALIEN_ARTIFACT;

    @Override
    public String getName() {
        return "Alien Artifact";
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
