package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.utilities.Constants;

/**
 * A card that upon being drawn will add troops to a specific territory.
 * @author Robin Jansson
 */
public class TerritoryTroopCard implements ICard {

    private Territory targetTerritory;
    private String title, message;
    private int bonusTroops;
    private Player currentPlayer = Constants.EMPTY_PLAYER, playerA, playerB;

    /**
     *
     * @param targetTerritory, the specific territory targetet with bonus troops
     * @param bonusTroops, the amount of extra troops
     */
    public TerritoryTroopCard(Territory targetTerritory, int bonusTroops) {
        this.targetTerritory = targetTerritory;
        this.bonusTroops = bonusTroops;
        this.title = "Reinforcements";
        this.message = "More reinforcements has arrived and territory " + targetTerritory.getName() +
                " will receive " + bonusTroops + " additional troops";
    }

    /**
     * Bonus troops will in this case be awarded to a random territory owned by a random player.
     *
     * @param playerA, a reference to a player
     * @param playerB, a reference to the other player
     * @param bonusTroops, the amount of bonus troops
     */
    public TerritoryTroopCard(Player playerA, Player playerB, int bonusTroops) {
        this.currentPlayer = playerA;
        this.playerA = playerA;
        this.playerB = playerB;
        this.bonusTroops = bonusTroops;
        this.title = "Reinforcements";
        this.message = "More reinforcements has arrived and a territory will receive " + bonusTroops + " additional troops";
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int phaseCheck() {
        return 0;
    }

    @Override
    public void turnCard() {
        if (currentPlayer != Constants.EMPTY_PLAYER) {
            randPlayer();
            int i = (int)( Math.random() * ( currentPlayer.getTerritories().size()-1 ) );
            this.targetTerritory = currentPlayer.getTerritories().get(i);
        }

        targetTerritory.addTroops(bonusTroops);

    }

    /**
     * A method that randomizes the players to give a random player the effect of the card.
     */
    private void randPlayer() {
        int randInt = (int)(Math.random()*2);

        if (randInt == 0) {
            currentPlayer = this.playerA;
        } else if (randInt == 1) {
            currentPlayer = this.playerB;
        }
    }
}
