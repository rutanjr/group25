package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.ICard;
import edu.chl.ChalmersRisk.model.Territory;

import java.util.ArrayList;

/**
 * A card that upon being drawn will increase/decrease the number of troops in all territories.
 *
 * Created by chrh on 2015-05-19.
 */
public class AllChangeTroopCard implements ICard {

    private String message, title;
    private int troopChange;
    private ArrayList allTerritories;

    public AllChangeTroopCard(ArrayList allTerritories, int troopChange) {
        this.troopChange = troopChange;
        this.allTerritories = allTerritories;

        if (this.troopChange >= 0) {
            this.message = this.troopChange + " troops join each territory.";
            this.title = "Rally!";
        } else if (this.troopChange < 0) {
            this.message = "Due to the influenza " + this.troopChange + " soldiers from each territory has deserted.";
            this.title = "Influenza!";
        }

    }

    @Override
    public String getTitle() { return this.title; }

    @Override
    public String getMessage() { return this.message; }

    @Override
    public void turnCard() {
        for (Territory temp : allTerritories) {
            if (troopChange < 0) {
                temp.removeTroops(-troopChange);
            } else if (troopChange >= 0) {
                temp.addTroops(troopChange);
            }
        }
        //TODO Check if this is corrent.
    }
}
