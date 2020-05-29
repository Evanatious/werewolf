package Roles;

import Gameplay.Card;
import Gameplay.Player;
import Roles.Teams.StandardTeam;
import Roles.Teams.Team;
import Roles.Teams.VampireTeam;

import java.util.List;

/** An enum to represent and store the roles in a game of One Night Ultimate
 *  Vampire: Copycat, Vampire, The Count, The Master, Renfield, Diseased, Cupid,
 *  Instigator, Priest, Assassin, Apprentice Assassin, Marksman, Pickpocket,
 *  and Gremlin.
 *
 * @author Evan Gao
 */
public enum VampireRole implements Role {
    COPYCAT("Copycat", StandardTeam.NEUTRAL) {
        @Override
        public boolean isChangeling() {
            return true;
        }
    }, VAMPIRE("Vampire", VampireTeam.VAMPIRE) {

    }, COUNT("The Count", VampireTeam.VAMPIRE) {

    }, MASTER("The Master", VampireTeam.VAMPIRE) {

    }, RENFIELD("Renfield", VampireTeam.VAMPIRE) {
        @Override
        public boolean isSacrificial() {
            return true;
        }

    }, DISEASED("Diseased", StandardTeam.VILLAGE) {

    }, CUPID("Cupid", StandardTeam.VILLAGE) {

    }, INSTIGATOR("Instigator", StandardTeam.VILLAGE) {

    }, PRIEST("Priest", StandardTeam.VILLAGE) {

    }, ASSASSIN("Assassin", StandardTeam.NEUTRAL) {

    }, APPRENTICE_ASSASSIN("Apprentice Assassin", StandardTeam.NEUTRAL) {

    }, MARKSMAN("Marksman", StandardTeam.VILLAGE) {

    }, PICKPOCKET("Pickpocket", StandardTeam.VILLAGE) {

    }, GREMLIN("Gremlin", StandardTeam.VILLAGE) {

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

    /** The constructor for a Roles.StandardRole object. */
    VampireRole(String name, Team team) {
        _name = name;
        _team = team;
    }

    @Override
    public void doAction(Player currPlayer) {
        if (this == COPYCAT) {
            List<Card> pool = currPlayer.getGame().getMiddle();
            Card chosen =
                currPlayer.promptChooseCardAction(COPYCAT_MESSAGE, 1, pool)[0];
            Role chosenRole = chosen.getRole();
            _newRole = chosenRole;
            currPlayer.changeRole(chosenRole);
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
