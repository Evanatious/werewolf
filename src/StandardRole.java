/** An enum to represent and store the standard roles in a game of ONUW:
 *  Doppelganger, Werewolf, Minion, Mason, Seer, Robber, Troublemaker, Drunk,
 *  Insomniac, Villager, Hunter, and Tanner.
 *
 * @author Evan Gao
 */
public enum StandardRole implements Role {
    DOPPELGANGER("Doppelgänger", null) {
        @Override
        public boolean isChangeling() {
            return true;
        }
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

    public static final String DOPPELGANGER_MESSAGE = "Doppelgänger, wake up "
        + "and look at another player's card. You are now that role. If your "
        + "new role has a night action, do it now."; //FIXME: Might change, get rid of the last sentence, add a sentence with the special case about copycats, etc.

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;
    /** The new role that the original role changed into. */
    private Role _newRole;

    /** The constructor for a StandardRole object. */
    StandardRole(String name, Team team) {
        _name = name;
        _team = team;
    }

    @Override
    public void doAction(Game game, Player currPlayer) {
        if (this == DOPPELGANGER) {
            Player otherPlayer = currPlayer.promptChoosePlayerAction(
                DOPPELGANGER_MESSAGE, 1, false)[0];
            Role newRole = otherPlayer.getCard().getRole();
            if (newRole == VampireRole.COPYCAT) {
                if (game.getHouseRules().contains(HouseRule.DOPPELCAT)) {
                    //TODO: view card in center, do it immediately
                } else {
                    _newRole = newRole; //TODO: Should it be newRole.getFinalRole()?
                    currPlayer.swapRole(newRole);
                }
            } else {
                _newRole = newRole;
                currPlayer.swapRole(newRole);
                if (newRole.performImmediately()) {
                    newRole.doAction(game, currPlayer);
                }
            }
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
