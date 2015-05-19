package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.ICard;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;

/**
 * A card that upon being drawn will destroy a territory from a player, making it neutral.
 *
 * Created by chrh on 2015-05-19.
 */
public class LoseTerritoryCard implements ICard {

    private String title, message;
    private Player targetPlayer;
    private Territory targetTerritory;

    public LoseTerritoryCard(Player targetPlayer, Territory targetTerritory) {
        this.targetPlayer = targetPlayer;
        this.targetTerritory = targetTerritory;

        //TODO
        this.title = "";
        this.message = "";
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
    public void turnCard() {
        //TODO
    }
}
