package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Territory;

/**
 * A card that upon being drawn will add troops to a specific territory.
 *
 * TODO this adds to any territory, maybe modify to give to the territory only if its controlled by the current player.
 *
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

        //TODO We maybe want to only add troops if current player is controlling said territory
    }
}
