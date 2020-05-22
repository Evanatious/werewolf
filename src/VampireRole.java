public enum VampireRole implements Role {
    COPYCAT("Copycat", StandardTeam.NEUTRAL) {

    };

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;

    /** The constructor for a StandardRole object. */
    VampireRole(String name, Team team) {
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
