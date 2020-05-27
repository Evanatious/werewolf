package Roles;

import Gameplay.*;
import Roles.Teams.*;

import java.util.*;

import static java.util.Arrays.asList;

/** An enum to represent and store the standard roles in a game of ONUW:
 *  Doppelganger, Werewolf, Minion, Mason, Seer, Robber, Troublemaker, Drunk,
 *  Insomniac, Villager, Hunter, and Tanner.
 *
 * @author Evan Gao
 */
public enum StandardRole implements Role {
    DOPPELGANGER("Doppelgänger", StandardTeam.NEUTRAL) {
        @Override
        public Phase getPhase() {
            return StandardPhase.NIGHT;
        }

        @Override
        public boolean isChangeling() {
            return true;
        }

        @Override
        public boolean won(Game game) {
            return getFinalRole().won(game);
        }
    }, WEREWOLF("Werewolf", StandardTeam.WEREWOLF) {
        @Override
        public Phase getPhase() {
            return StandardPhase.NIGHT;
        }

        @Override
        public void doAction(Player currPlayer) {
            Game currGame = currPlayer.getGame();
            List<Player> werewolves = StandardTeam.WEREWOLF.findAll(currGame); //See if this works
            for (Player p: werewolves) {
                if (p.getInitRole().isSacrificial()) {
                    werewolves.remove(p);
                }
            }

            if (werewolves.size() == 1) {
                currPlayer.displayInfo("You are the lone werewolf!");
                if (currGame.getHouseRules().contains(HouseRule.MANDATORY)
                    || currPlayer.promptMayAction("You may choose one center card.")) {
                    Card middleCard = currPlayer.promptChooseCardAction(
                        "Pick one center card", 1, currGame.getMiddle())[0];
                    currPlayer.showCard("The card you have chosen was: ", middleCard);
                }
            } else {
                werewolves.remove(currPlayer);
                currPlayer.showPlayers("Here are the other werewolves: ", werewolves);
            }
        }
    }, MINION("Minion", StandardTeam.WEREWOLF) {
        @Override
        public Phase getPhase() {
            return StandardPhase.NIGHT;
        }

        @Override
        public boolean performImmediately() {
            return true;
        }

        @Override
        public boolean isSacrificial() {
            return true;
        }

        @Override
        public void doAction(Player currPlayer) {
            Game currGame = currPlayer.getGame();
            List<Player> werewolves = StandardTeam.WEREWOLF.findAll(currGame); //See if this works
            for (Player p: werewolves) {
                if (p.getInitRole().isSacrificial()) {
                    werewolves.remove(p);
                }
            }

            if (currGame.getHouseRules().contains(HouseRule.MINIONBECOMESWW)
                && werewolves.size() == 0) {
                currPlayer.displayInfo("There were no other Werewolves, so you have become the lone werewolf!");
                if (currGame.getHouseRules().contains(HouseRule.MANDATORY)
                    || currPlayer.promptMayAction("You may view one center card.")) {
                    Card middleCard = currPlayer.promptChooseCardAction(
                        "Pick one center card.", 1, currGame.getMiddle())[0];
                    currPlayer.showCard("The card you have chosen was: ", middleCard);
                }
            } else {
                currPlayer.showPlayers("The following players are Werewolves: ", werewolves);
            }
        }
    }, MASON("Mason", StandardTeam.VILLAGE) {
        @Override
        public Phase getPhase() {
            return StandardPhase.NIGHT;
        }

        @Override
        public void doAction(Player currPlayer) {
            Game currGame = currPlayer.getGame();
            List<Player> masons = Role.findPlayersWithRole(currGame, this);
            masons.remove(currPlayer);

            if (masons.isEmpty()) {
                currPlayer.displayInfo("You are the only Mason!");
            } else {
                currPlayer.showPlayers("The following players are Masons: ", masons);
            }
        }
    }, SEER("Seer", StandardTeam.VILLAGE) {
        @Override
        public Phase getPhase() {
            return StandardPhase.NIGHT;
        }

        @Override
        public boolean performImmediately() {
            return true;
        }

        @Override
        public void doAction(Player currPlayer) {
            Game currGame = currPlayer.getGame();

            if (currGame.getHouseRules().contains(HouseRule.MANDATORY)
                || currPlayer.promptMayAction("You may look at another player's card or two of the center cards")) {
                if (currPlayer.choose(Collections.singletonList(SEER_OPTIONS),
                    1)[0].equals(SEER_OPTION1)) { //TODO: See if singletonList does whatever the heck it's supposed to do

                    Card[] twoCards = currPlayer.promptChooseCardAction(
                        "Pick two center cards.", 2, currGame.getMiddle()); //FIXME: Either needs to choose one card at a time or have choose in Player be REALLY fleshed out
                    currPlayer.showCard("DO I EVEN NEED A MESSAGE? ", twoCards[0]);
                    currPlayer.showCard("DO I EVEN NEED A MESSAGE? ", twoCards[1]);
                } else {
                    //TODO: Look at another player's card
                }
            }
        }
    }, ROBBER("Robber", StandardTeam.VILLAGE) {

    }, TROUBLEMAKER("Troublemaker", StandardTeam.VILLAGE) {

    }, DRUNK("Drunk", StandardTeam.VILLAGE) {

    }, INSOMNIAC("Insomniac", StandardTeam.VILLAGE) {

    },
    VILLAGER("Villager", StandardTeam.VILLAGE),
    HUNTER("Hunter", StandardTeam.VILLAGE) {

    },
    TANNER("Tanner", StandardTeam.NEUTRAL);

    public static final String SEER_OPTION1 = "2 Center Cards";
    public static final String SEER_OPTION2 = "1 (Other) Player's Card";

    public static final Set<String> SEER_OPTIONS;
    static {
        SEER_OPTIONS = new HashSet<>();
        SEER_OPTIONS.add(SEER_OPTION1);
        SEER_OPTIONS.add(SEER_OPTION2);
    }

    public static final String CHOOSE_ONE_CARD =
        "You may choose one center card"; //FIXME: May make all messages come from a function in the future

    public static final String DOPPELGANGER_MESSAGE = "Doppelgänger, wake up "
        + "and look at another player's card. You are now that role. If your "
        + "new role has a night action, do it now."; //FIXME: Might change, get rid of the last sentence, add a sentence with the special case about copycats, etc.

    /** The name of the role. */
    private String _name;
    /** The team that this role belongs to. */
    private Team _team;
    /** The new role that the original role changed into. */
    private Role _newRole;

    /** The constructor for a Roles.StandardRole object. */
    StandardRole(String name, Team team) {
        _name = name;
        _team = team;
    }

    @Override
    public void doAction(Player currPlayer) {
        if (this == DOPPELGANGER) {
            Game currGame = currPlayer.getGame();
            List<Player> players = currGame.getPlayers();
            players.remove(currPlayer);
            Player otherPlayer = currPlayer.promptChoosePlayerAction(
                DOPPELGANGER_MESSAGE, 1, players)[0];
            Role newRole = otherPlayer.getCard().getRole();

            if (newRole == VampireRole.COPYCAT) {
                if (currGame.getHouseRules().contains(HouseRule.DOPPELCAT1)) {
                    //TODO: view card in center, do it immediately (if it performs immediately?) idk
                } else {
                    _newRole = newRole; //TODO: Should it be newRole.getFinalRole()?
                    currPlayer.swapRole(newRole); //FIXME: I think this will have whatever role the copycat turned into as well
                }
            } else { //FIXME: Maybe some of this should belong in Game? I don't think so though because it's best to just call doAction and not worry about it
                Role oldRole = currPlayer.getInitRole();
                _newRole = newRole;
                currPlayer.swapRole(newRole);
                if (newRole.performImmediately()) {
                    newRole.doAction(currPlayer);
                } else {
                    currGame.updatePlayer(currPlayer, oldRole);
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
