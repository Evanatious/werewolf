package Roles;

import Gameplay.Player;
import Roles.Teams.AlienTeam;
import Roles.Teams.StandardTeam;
import Roles.Teams.Team;

/** An enum to represent and store the roles in a game of One Night Ultimate
 *  Alien: Oracle, Alien, Synthetic Alien, Cow, Groob, Zerb, Leader, Psychic,
 *  Rascal, Exposer, Blob, and Mortician.
 *
 * @author Evan Gao
 */
public enum AlienRole implements Role {
    ORACLE("Oracle", StandardTeam.Village) {

    }, ALIEN("Alien", AlienTeam.Alien) {

    }, SYNTHETIC_ALIEN("Synthetic Alien", AlienTeam.Alien) {

    }, COW("Cow", StandardTeam.Village) {

    }, GROOB("Groob", AlienTeam.Alien) {

    }, ZERB("Zerb", AlienTeam.Alien) {

    }, LEADER("Leader", StandardTeam.Village) {

    }, PSYCHIC("Psychic", StandardTeam.Village) {

    }, RASCAL("Rascal", StandardTeam.Village) {

    }, EXPOSER("Exposer", StandardTeam.Village) {

    }, BLOB("Blob", StandardTeam.Neutral) {

    }, MORTICIAN("Mortician", StandardTeam.Neutral) {

    };

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;
    /** The new role that the original role changed into. */
    private Role _newRole;

    /** The constructor for a Roles.StandardRole object. */
    AlienRole(String name, Team team) {
        _name = name;
        _team = team;
    }

    @Override
    public void doAction(Player currPlayer) {
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
