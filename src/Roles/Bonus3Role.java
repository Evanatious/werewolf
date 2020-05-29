package Roles;

import Roles.Teams.StandardTeam;
import Roles.Teams.Team;

/** An enum to represent and store the roles in Bonus Pack 3:
 *  Body Snatcher, Empath, and Nostradamus.
 *
 * @author Evan Gao
 */
public enum Bonus3Role implements Role{
    BODY_SNATCHER("Body Snatcher", StandardTeam.Village) {

    }, EMPATH("Empath", StandardTeam.Village) {

    }, NOSTRADAMUS("Nostradamus", StandardTeam.Village) {

    };

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;

    /** The constructor for a Roles.StandardRole object. */
    Bonus3Role(String name, Team team) {
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
