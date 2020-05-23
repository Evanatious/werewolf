/** An enum to represent and store the roles in a game of One Night Ultimate
 *  Vampire: FIXME
 *
 * @author Evan Gao
 */
public enum VampireRole implements Role {
    COPYCAT("Copycat", StandardTeam.NEUTRAL) {
        @Override
        public boolean isChangeling() {
            return true;
        }
    };

    public static final String COPYCAT_MESSAGE = "Copycat, wake up and look at "
        + "one of the center cards. You are now that role. If that role is "
        + "called, wake up and do that night action.";

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;
    /** The new role that the original role changed into. */
    private Role _newRole;

    /** The constructor for a StandardRole object. */
    VampireRole(String name, Team team) {
        _name = name;
        _team = team;
    }

    @Override
    public void doAction(Game game, Player currPlayer) {
        if (this == COPYCAT) {
            Card chosen =
                currPlayer.promptChooseCardAction(COPYCAT_MESSAGE, 1)[0];
            Role chosenRole = chosen.getRole();
            _newRole = chosenRole;
            currPlayer.swapRole(chosenRole);
        }
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
