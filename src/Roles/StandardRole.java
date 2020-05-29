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
    DOPPELGANGER("Doppelgänger", StandardTeam.Neutral) {
        @Override
        public Phase getPhase() {
            return StandardPhase.NIGHT;
        }

        @Override
        public boolean isChangeling() {
            return true;
        }

        @Override
        public boolean won(Game game, Player player) {
            return getFinalRole().won(game, player);
        }
    }, WEREWOLF("Werewolf", StandardTeam.Werewolf) {
        @Override
        public Phase getPhase() {
            return StandardPhase.NIGHT;
        }

        @Override
        public void doAction(Player currPlayer) {
            super.doAction(currPlayer);
            Game currGame = currPlayer.getGame();
            List<Player> werewolves = StandardTeam.Werewolf.findAll(currGame); //See if this works
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
    }, MINION("Minion", StandardTeam.Werewolf) {
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
            super.doAction(currPlayer);
            Game currGame = currPlayer.getGame();
            List<Player> werewolves = StandardTeam.Werewolf.findAll(currGame); //See if this works
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
    }, MASON("Mason", StandardTeam.Village) {
        @Override
        public Phase getPhase() {
            return StandardPhase.NIGHT;
        }

        @Override
        public void doAction(Player currPlayer) {
            super.doAction(currPlayer);
            Game currGame = currPlayer.getGame();
            List<Player> masons = Role.findPlayersWithRole(currGame, this);
            masons.remove(currPlayer);

            if (masons.isEmpty()) {
                currPlayer.displayInfo("You are the only Mason!");
            } else {
                currPlayer.showPlayers("The following players are Masons: ", masons);
            }
        }
    }, SEER("Seer", StandardTeam.Village) {
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
            super.doAction(currPlayer);
            Game currGame = currPlayer.getGame();

            if (currGame.getHouseRules().contains(HouseRule.MANDATORY)
                || currPlayer.promptMayAction("You may look at another player's card or two of the center cards.")) {
                if (currPlayer.choose(Collections.singletonList(SEER_OPTIONS),
                    1)[0].equals(SEER_OPTION1)) { //TODO: See if singletonList does whatever the heck it's supposed to do
                    Card[] twoCards = currPlayer.promptChooseCardAction(
                        "Pick two center cards.", 2, currGame.getMiddle()); //FIXME: Either needs to choose one card at a time or have choose in Player be REALLY fleshed out
                    currPlayer.showCard("The first card you picked was: ", twoCards[0]);
                    currPlayer.showCard("The second card you picked was: ", twoCards[1]);
                } else {
                    List<Player> otherPlayers = currGame.getPlayers();

                    //TODO: Needs to check for shield token

                    otherPlayers.remove(currPlayer);
                    Player chosen = currPlayer.promptChoosePlayerAction(
                        "Pick another player.", 1, otherPlayers)[0];
                    Card chosenCard = chosen.getCard();
                    String chosenName = chosen.getName();
                    currPlayer.showCard(chosenName + "'s card is: ", chosenCard);
                }
            }
        }
    }, ROBBER("Robber", StandardTeam.Village) {
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
            super.doAction(currPlayer);
            Game currGame = currPlayer.getGame();

            //TODO: Needs to check for shield token

            if (currGame.getHouseRules().contains(HouseRule.MANDATORY)
                || currPlayer.promptMayAction("You may exchange your card with another player's card, and then view your new card.")) {
                List<Player> otherPlayers = currGame.getPlayers();
                otherPlayers.remove(currPlayer);
                Player chosen = currPlayer.promptChoosePlayerAction(
                    "Pick another player.", 1, otherPlayers)[0];
                currGame.swapPlayerAndPlayer(currPlayer, chosen);
                currPlayer.showCard("Your new card is: ", currPlayer.getCard());
            }
        }
    }, TROUBLEMAKER("Troublemaker", StandardTeam.Village) {
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
            super.doAction(currPlayer);
            Game currGame = currPlayer.getGame();

            //TODO: Needs to check for shield token

            if (currGame.getHouseRules().contains(HouseRule.MANDATORY)
                || currPlayer.promptMayAction("You may exchange cards between two other players.")) {
                List<Player> otherPlayers = currGame.getPlayers();
                otherPlayers.remove(currPlayer);
                Player[] chosen = currPlayer.promptChoosePlayerAction(
                    "Pick two other players.", 2, otherPlayers);
                currGame.swapPlayerAndPlayer(chosen[0], chosen[1]);
            }
        }
    }, DRUNK("Drunk", StandardTeam.Village) {
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
            super.doAction(currPlayer);
            Game currGame = currPlayer.getGame();

            //TODO: Needs to check for shield token

            Card chosen = currPlayer.promptChooseCardAction(
                "You must exchange your card with a card from the center.",
                1, currGame.getMiddle())[0];
            currGame.swapPlayerAndCenter(currPlayer, chosen);

            //FIXME: Needs to update
        }
    }, INSOMNIAC("Insomniac", StandardTeam.Village) {
        @Override
        public Phase getPhase() {
            return StandardPhase.NIGHT;
        }

        @Override
        public void doAction(Player currPlayer) {
            super.doAction(currPlayer);
            Game currGame = currPlayer.getGame();

            //TODO: Needs to check for shield token

            currPlayer.showCard("You are now: ", currPlayer.getCard());
        }
    },
    VILLAGER("Villager", StandardTeam.Village),
    HUNTER("Hunter", StandardTeam.Village) {
        @Override
        public Phase getPhase() {
            return StandardPhase.VOTE;
        }

        @Override
        public void doAction(Player currPlayer) {

        }
    },
    TANNER("Tanner", StandardTeam.Neutral) {
        @Override
        public Phase getPhase() {
            return StandardPhase.VOTE;
        }

        @Override
        public boolean won(Game game, Player player) {
            return false; //FIXME
        }
    };

    public static final String SEER_OPTION1 = "2 Center Cards";
    public static final String SEER_OPTION2 = "1 (Other) Player's Card";

    public static final List<String> SEER_OPTIONS;
    static {
        SEER_OPTIONS = new ArrayList<>();
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
        currPlayer.displayInfo(getDescription());

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
                    currPlayer.changeRole(newRole); //FIXME: I think this will have whatever role the copycat turned into as well
                }
            } else { //FIXME: Maybe some of this should belong in Game? I don't think so though because it's best to just call doAction and not worry about it
                Role oldRole = currPlayer.getInitRole();
                _newRole = newRole;
                currPlayer.changeRole(newRole);
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
