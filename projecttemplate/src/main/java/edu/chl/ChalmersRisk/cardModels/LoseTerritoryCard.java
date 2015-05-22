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
    private Player targetPlayer;

    public LoseTerritoryCard(Player targetPlayer) {
        this.targetPlayer = targetPlayer;

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
    public int phaseCheck() {
        return 0;
    }

    /**
     * A method that turns a random Territory neutral, but keeps the troops currently on it
     */
    @Override
    public void turnCard() {

        int intTemp = targetPlayer.getTerritories().size();
        intTemp = (int)(Math.random()*intTemp);
        targetPlayer.removeTerritory(targetPlayer.getTerritories().get(intTemp));

    }
}
