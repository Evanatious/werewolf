/** An enum to represent and store the standard roles in a game of ONUW:
 *  Doppelganger, Werewolf, Minion, Mason, Seer, Robber, Troublemaker, Drunk,
 *  Insomniac, Villager, Hunter, and Tanner.
 *
 * @author Evan Gao
 */
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

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;

    /** The constructor for a StandardRole object. */
    StandardRoles(String name, Team team) {
        _name = name;
        _team = team;
    }

    /** A helper method that finds the player with the specified role in the
     *  given game.
     *
     * @param game the game to look for the player in
     * @param role the role of the player to look for
     * @return the player with the specified role in the given game
     */
    private static Player findPlayer(Game game, Role role) {
        for (Player p: game.getPlayers()) {
            if (p.getInitRole() == role) {
                return p;
            }
        }
        return null; //Should never reach this line;
    }
    
    private static void findRole(Game game, Role role) {

    }

    @Override
    public void doAction(Game game) {

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
