package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;

/**
 * A card that upon being drawn will add troops to a specific territory.
 * Created by chrh on 2015-05-19.
 */
public class TerritoryTroopCard implements ICard {

    private Territory targetTerritory;
    private String title, message;
    private int bonusTroops;

    public TerritoryTroopCard(Territory targetTerritory, int bonusTroops) {
        this.targetTerritory = targetTerritory;
        this.bonusTroops = bonusTroops;
        this.title = "Reinforcements";
        this.message = "More reinforcements has arrived and territory " + targetTerritory.getName() +
                " will receive " + bonusTroops + " additional troops";
    }

    public TerritoryTroopCard(Player currentPlayer, int bonusTroops) {
        int i = (int)(Math.random() * currentPlayer.getTerritories().size());
        this.targetTerritory = currentPlayer.getTerritories().get(i);
        this.bonusTroops = bonusTroops;
        this.message = "More reinforcements has arrived and territory " + targetTerritory.getName() +
                " will receive " + bonusTroops + " additional troops";
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

        targetTerritory.addTroops(bonusTroops);

    }
}
