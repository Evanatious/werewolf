public enum StandardRoles implements Role {
    DOPPELGANGER("Doppelganger", null) {
        //FIXME, also team should not be null, we will figure it out
    },
    WEREWOLF("Werewolf", StandardTeams.WEREWOLF) {

    },
    MINION("Minion", StandardTeams.WEREWOLF) {

    },
    MASON("Mason", StandardTeams.TOWN) {

    },
    SEER("Seer", StandardTeams.TOWN) {

    },
    ROBBER("Robber", StandardTeams.TOWN) {

    },
    TROUBLEMAKER("Troublemaker", StandardTeams.TOWN) {

    },
    DRUNK("Drunk", StandardTeams.TOWN) {

    },
    INSOMNIAC("Insomniac", StandardTeams.TOWN) {

    },
    VILLAGER("Villager", StandardTeams.TOWN),
    HUNTER("Hunter", StandardTeams.TOWN) {

    },
    TANNER("Tanner", StandardTeams.TANNER);

    private String _name;
    private Team _team;

    StandardRoles(String name, Team team) {
        _name = name;
        _team = team;
    }

    @Override
    public void doAction() {

    }

    @Override
    public Team getTeam() {
        return _team;
    }

    @Override
    public String getName() {
        return _name;
    }
}
