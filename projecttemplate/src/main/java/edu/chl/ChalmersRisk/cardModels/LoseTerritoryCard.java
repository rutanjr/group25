package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.ICard;
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

    /**
     * A method that turns a random Territory neutral, but keeps the troops currently on it
     */
    @Override
    public void turnCard() {
        private ArrayList tempList = targetPlayer.getTerritories();
        private int tempInt = tempList.size() - 1;
        private Territory tempTerritory;

        intTemp = (int)(Math.random()*tempInt);

        tempTerritory = (Territory)(tempList.get(tempInt));
        tempTerritory.setnewOwner(Constants.EMPTY_PLAYER);

        //TODO Check if this works
    }
}
