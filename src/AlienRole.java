/** An enum to represent and store the roles in a game of One Night Ultimate
 *  Alien: Oracle, Alien, Synthetic Alien, Cow, Groob, Zerb, Leader, Psychic,
 *  Rascal, Exposer, Blob, and Mortician.
 *
 * @author Evan Gao
 */
public enum AlienRole implements Role {
    ORACLE("Oracle", StandardTeam.VILLAGE) {

    }, ALIEN("Alien", AlienTeam.ALIEN) {

    }, SYNTHETIC_ALIEN("Synthetic Alien", AlienTeam.ALIEN) {
        @Override
        public boolean hasAlternateWinCon() {
            return true;
        }
    }, COW("Cow", StandardTeam.VILLAGE) {

    }, GROOB("Groob", AlienTeam.ALIEN) {
        @Override
        public boolean hasAlternateWinCon() {
            return true;
        }
    }, ZERB("Zerb", AlienTeam.ALIEN) {
        @Override
        public boolean hasAlternateWinCon() {
            return true;
        }
    }, LEADER("Leader", StandardTeam.VILLAGE) {
        @Override
        public boolean hasAlternateWinCon() {
            return true;
        }
    }, PSYCHIC("Psychic", StandardTeam.VILLAGE) {

    }, RASCAL("Rascal", StandardTeam.VILLAGE) {

    }, EXPOSER("Exposer", StandardTeam.VILLAGE) {

    }, BLOB("Blob", StandardTeam.NEUTRAL) {
        @Override
        public boolean hasAlternateWinCon() {
            return true;
        }
    }, MORTICIAN("Mortician", StandardTeam.NEUTRAL) {
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
    AlienRole(String name, Team team) {
        _name = name;
        _team = team;
    }

    @Override
    public void doAction(Game game, Player currPlayer) {
        //TODO
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
