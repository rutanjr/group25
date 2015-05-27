package edu.chl.ChalmersRisk.cardModels;


import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.utilities.Constants;

import java.util.ArrayList;

/**
 * A card that upon being drawn will destroy a territory from a player, making it neutral.
 *
 * Created by chrh on 2015-05-19.
 */
public class LoseTerritoryCard implements ICard {

    private String title, message;
    private Player currentPlayer, playerA, playerB;

    /**
     * A constructor for an event card where the current player loses a territory at random
     *
     * @param currentPlayer, a reference to the current player (the player who drew this card).
     */
    public LoseTerritoryCard(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.title = "Ops!";
        this.message = "An unsanctioned experiment in one of Chalmers building has gone awry and there is now," +
                " something different about the people there...";
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
        this.title = "Ops!";
        this.message = "An unsanctioned experiment in one of Chalmers building has gone awry and there is now," +
                " something different about the people there...";
    }

    @Override
    public String getTitle() { return this.title; }

    @Override
    public String getMessage() { return this.message; }

    @Override
    public int phaseCheck() { return 0; }

    /**
     * A method that turns a random Territory neutral, but keeps the troops currently on it
     */
    @Override
    public void turnCard() {
        if (currentPlayer == null) { // if the card is created with two players
            int randInt = (int)(Math.random()*2);

            if (randInt == 0) {
                currentPlayer = playerA;
            } else if (randInt == 1) {
                currentPlayer = playerB;
            }
        }
        cardEffect(currentPlayer);
    }
    
    private void cardEffect(Player targetPlayer) {
        int intTemp = (currentPlayer.getnmbrOfTerritories() - 1); // -1 so we later can remove objects at their correct possition
        intTemp = (int)(Math.random()*intTemp);
        currentPlayer.removeTerritory(currentPlayer.getTerritories().get(intTemp));
    }
}
