package Roles;

import Gameplay.Player;
import Roles.Teams.StandardTeam;
import Roles.Teams.Team;
import Roles.Teams.VillainTeam;

/** An enum to represent and store the roles in a game of One Night Ultimate
 *  Super Villains: Mirror Man, Temptress, Dr. Peeker, Rapscallion, Evilometer,
 *  Mad Scientist, Intern, Annoying Lad, Detector, Role Retriever, Voodoo Lou,
 *  Switcheroo, Self-Awareness Girl, Flipper, and Innocent Bystander.
 *
 * @author Evan Gao
 */
public enum VillainRole implements Role {
    MIRROR_MAN("Mirror Man", StandardTeam.Neutral) {

    }, TEMPTRESS("Temptress", VillainTeam.SUPERVILLAIN) {

    }, DR_PEEKER("Dr. Peeker", VillainTeam.SUPERVILLAIN) {

    }, RAPSCALLION("Rapscallion", VillainTeam.SUPERVILLAIN) {

    }, EVILOMETER("Evilometer", VillainTeam.HERO) {

    }, MAD_SCIENTIST("Mad Scientist", StandardTeam.Neutral) {

    }, INTERN("Intern", StandardTeam.Neutral) {

    }, ANNOYING_LAD("Annoying Lad", VillainTeam.HERO) {

    }, DETECTOR("Detector", VillainTeam.HERO) {

    }, ROLE_RETRIEVER("Role Retriever", VillainTeam.HERO) {

    }, VOODOO_LOU("Voodoo Lou", VillainTeam.HERO) {

    }, SWITCHEROO("Switcheroo", VillainTeam.HERO) {

    }, SELF_AWARENESS_GIRL("Self-Awareness Girl", VillainTeam.HERO) {

    }, FLIPPER("Flipper", VillainTeam.HERO) {

    }, INNOCENT_BYSTANDER("Innocent Bystander", StandardTeam.Neutral) {

    };

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;
    /** The new role that the original role changed into. */
    private Role _newRole;

    /** The constructor for a Roles.StandardRole object. */
    VillainRole(String name, Team team) {
        _name = name;
        _team = team;
    }

    @Override
    public void doAction(Player currPlayer) {
        currPlayer.displayInfo(getDescription());
        //TODO: Mirror Man
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
