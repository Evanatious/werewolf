/** An enum to represent and store the roles in a game of One Night Ultimate
 *  Super Villains: FIXME
 *
 * @author Evan Gao
 */
public enum VillainRole implements Role {
    MIRROR_MAN("Mirror Man", StandardTeam.NEUTRAL) {

    }, TEMPTRESS("Temptress", VillainTeam.SUPERVILLAIN) {

    }, DR_PEEKER("Dr. Peeker", VillainTeam.SUPERVILLAIN) {

    }, RAPSCALLION("Rapscallion", VillainTeam.SUPERVILLAIN) {

    }, EVILOMETER("Evilometer", VillainTeam.HERO) {

    }, MAD_SCIENTIST("Mad Scientist", StandardTeam.NEUTRAL) {

    }, INTERN("Intern", StandardTeam.NEUTRAL) {

    }, ANNOYING_LAD("Annoying Lad", VillainTeam.HERO) {

    }, DETECTOR("Detector", VillainTeam.HERO) {

    }, ROLE_RETRIEVER("Role Retriever", VillainTeam.HERO) {

    }, VOODOO_LOU("Voodoo Lou", VillainTeam.HERO) {

    }, SWITCHEROO("Switcheroo", VillainTeam.HERO) {

    }, SELF_AWARENESS_GIRL("Self-Awareness Girl", VillainTeam.HERO) {

    }, FLIPPER("Flipper", VillainTeam.HERO) {

    }, INNOCENT_BYSTANDER("Innocent Bystander", StandardTeam.NEUTRAL) {
        @Override
        public boolean hasAlternateWinCon() {
            return true;
        }
    };

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;
    /** The new role that the original role changed into. */
    private Role _newRole;

    /** The constructor for a StandardRole object. */
    VillainRole(String name, Team team) {
        _name = name;
        _team = team;
    }

    @Override
    public void doAction(Game game, Player currPlayer) {
        //TODO: Mirror Man
    }

    @Override
    public Team getTeam() {
        return _team;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public Role getFinalRole() {
        if (isChangeling()) {
            return _newRole.getFinalRole();
        } else {
            return this;
        }
    }
}
