/** An enum to represent and store the artifacts in a game of ONUW Daybreak:
 *  Claw of the Werewolf, Brand of the Villager, Cudgel of the Tanner, Void
 *  of Nothingness, Mask of Muting, and Shroud of Shame.
 *
 * @author Evan Gao
 */
public enum DaybreakArtifact implements Artifact {
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
    DaybreakArtifact(String name) {
        _name = name;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
