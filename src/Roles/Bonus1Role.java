package Roles;

import Roles.Teams.StandardTeam;
import Roles.Teams.Team;

/** An enum to represent and store the roles in Bonus Pack 1:
 *  Aura Seer, Cursed, and Prince.
 *
 * @author Evan Gao
 */
public enum Bonus1Role implements Role {
    AURA_SEER("Aura Seer", StandardTeam.Village) {

    }, CURSED("Cursed", StandardTeam.Village) {

    }, PRINCE("Prince", StandardTeam.Village) {

    };

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;

    /** The constructor for a Roles.StandardRole object. */
    Bonus1Role(String name, Team team) {
        _name = name;
        _team = team;
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
