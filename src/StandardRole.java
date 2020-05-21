/** An enum to represent and store the standard roles in a game of ONUW:
 *  Doppelganger, Werewolf, Minion, Mason, Seer, Robber, Troublemaker, Drunk,
 *  Insomniac, Villager, Hunter, and Tanner.
 *
 * @author Evan Gao
 */
public enum StandardRole implements Role {
    DOPPELGANGER("Doppelganger", null) {
        //FIXME, also team should not be null, we will figure it out
    }, WEREWOLF("Werewolf", StandardTeam.WEREWOLF) {

    }, MINION("Minion", StandardTeam.WEREWOLF) {
        @Override
        public boolean isSacrificial() {
            return true;
        }
    }, MASON("Mason", StandardTeam.VILLAGE) {

    }, SEER("Seer", StandardTeam.VILLAGE) {

    }, ROBBER("Robber", StandardTeam.VILLAGE) {

    }, TROUBLEMAKER("Troublemaker", StandardTeam.VILLAGE) {

    }, DRUNK("Drunk", StandardTeam.VILLAGE) {

    }, INSOMNIAC("Insomniac", StandardTeam.VILLAGE) {

    },
    VILLAGER("Villager", StandardTeam.VILLAGE),
    HUNTER("Hunter", StandardTeam.VILLAGE) {

    },
    TANNER("Tanner", StandardTeam.NEUTRAL);

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;

    /** The constructor for a StandardRole object. */
    StandardRole(String name, Team team) {
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
    public Team getTeam() {
        return _team;
    }

    @Override
    public String getName() {
        return _name;
    }
}
