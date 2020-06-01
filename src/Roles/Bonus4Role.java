package Roles;

import Roles.Teams.StandardTeam;
import Roles.Teams.Team;
import Roles.Teams.VillainTeam;

/** An enum to represent and store the roles in Bonus Pack 4:
 *  Family Man, Defender-er, The Sponge, Richochet Rhino, and Windy Wendy.
 *
 * @author Evan Gao
 */
public enum Bonus4Role implements Role {
    FAMILY_MAN("Body Snatcher", StandardTeam.Neutral) {

    }, DEFENDERER("Defender-er", VillainTeam.Hero) {

    }, SPONGE("The Sponge", VillainTeam.Hero) {

    }, RICHOCHET_RHINO("Richochet Rhino", VillainTeam.Hero) {

    }, WINDY_WENDY("Windy Wendy", VillainTeam.Hero) {

    };

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;

    /** The constructor for a Roles.StandardRole object. */
    Bonus4Role(String name, Team team) {
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
