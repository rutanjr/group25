package edu.chl.ChalmersRisk.cardModels;


import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.utilities.Constants;

/**
 * A card that upon being drawn will destroy a territory from a player and makes it neutral, also
 * removes any troops currently on it. This card also destroys troop allocations for the player affected.
 *
 * @author Robin Jansson
 */
public class LoseTerritoryCard implements ICard {

    private String message = "An unsanctioned experiment in one of Chalmers building has gone awry and the people there, are no more";
    private String title = "Ops!";
    private Player currentPlayer = Constants.EMPTY_PLAYER, playerA, playerB;

    /**
     * A constructor for an event card where the current player loses a territory at random
     *
     * @param currentPlayer, a reference to the current player (the player who drew this card).
     */
    public LoseTerritoryCard(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * A constructor for an event card where a random player loses a territory at random
     *
     * @param playerA, a reference to one of the two players.
     * @param playerB, a reference to the other of the two players.
     */
    public LoseTerritoryCard(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    @Override
    public String getTitle() { return this.title; }

    @Override
    public String getMessage() { return this.message; }

    @Override
    public int phaseCheck() { return 0; }

    /**
     * Initiates the effect of the card.
     */
    @Override
    public void turnCard() {

        if (currentPlayer == Constants.EMPTY_PLAYER) {
            randPlayer();
        }

        int intTemp = (currentPlayer.getnmbrOfTerritories() - 1); // -1 so we later can remove objects at their correct possition
        intTemp = (int)(Math.random()*intTemp);
        currentPlayer.getTerritories().get(intTemp).removeTroops(currentPlayer.getTerritories().get(intTemp).getAmountOfTroops());
        currentPlayer.getTerritories().get(intTemp).setnewOwner(Constants.EMPTY_PLAYER);
    }

    private void randPlayer() {
        int randInt = (int)(Math.random()*2);

        if (randInt == 0) {
            currentPlayer = this.playerA;
        } else if (randInt == 1) {
            currentPlayer = this.playerB;
        }
    }
}
