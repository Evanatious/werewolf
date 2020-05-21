/** An enum to represent and store the roles in Bonus Pack 2:
 *  Apprentice Tanner, Thing (that goes bump in the night), Squire, and
 *  Beholder.
 *
 * @author Evan Gao
 */
public enum Bonus2Role implements Role {
    APPRENTICE_TANNER("Apprentice Tanner", StandardTeam.NEUTRAL),
    THING("Thing (that goes bump in the night)", StandardTeam.VILLAGE) {

    }, SQUIRE("Squire", StandardTeam.WEREWOLF) {
        @Override
        public boolean isSacrificial() {
            return true;
        }
    }, BEHOLDER("Beholder", StandardTeam.VILLAGE) {

    };


    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;

    /** The constructor for a StandardRole object. */
    Bonus2Role(String name, Team team) {
        _name = name;
        _team = team;
    }

    @Override
    public Team getTeam() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
