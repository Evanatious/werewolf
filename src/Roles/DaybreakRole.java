package Roles;

import Gameplay.Game;
import Gameplay.Player;
import Roles.Teams.StandardTeam;
import Roles.Teams.Team;

/** An enum to represent and store the roles in a game of ONUW Daybreak:
 *  Sentinel, Alpha Wolf, Mystic Wolf, Apprentice Seer, P.I., Witch, Village
 *  Idiot, Revealer, Curator, Dreamwolf, and Bodyguard.
 *
 * @author Evan Gao
 */
public enum DaybreakRole implements Role {
    SENTINEL("Sentinel", StandardTeam.VILLAGE) {

    }, ALPHA_WOLF("Alpha Wolf", StandardTeam.WEREWOLF) {

    }, MYSTIC_WOLF("Mystic Wolf", StandardTeam.WEREWOLF) {

    }, APPRENTICE_SEER("Apprentice Seer", StandardTeam.VILLAGE) {

    }, PI("Paranormal Investigator", StandardTeam.VILLAGE) {
        //TODO: BRUH HE HAS TO SWITCH ROLES
    }, WITCH("Witch", StandardTeam.VILLAGE) {
        //TODO: She swaps cards, luckily that's easier to implement than PI
        //Since promptPlayerAction doesn't allow for you to choose yourself,
        //there will need to be a special button/function for the witch to
        //choose herself.
    }, VILLAGE_IDIOT("Village Idiot", StandardTeam.VILLAGE) {

    }, REVEALER("Revealer", StandardTeam.VILLAGE) {

    }, CURATOR("Curator", StandardTeam.VILLAGE) {

    }, DREAM_WOLF("Dream Wolf", StandardTeam.WEREWOLF) {

    }, BODYGUARD("Bodyguard", StandardTeam.VILLAGE) {

    };

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;

    /** The constructor for a Roles.StandardRole object. */
    DaybreakRole(String name, Team team) {
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