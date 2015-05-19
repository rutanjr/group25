package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.ICard;

/**
 * A card that upon being drawn will increase/decrease the number of troops in all territories.
 *
 * Created by chrh on 2015-05-19.
 */
public class AllChangeTroopCard implements ICard {

    private String message, title;
    private int troopChange;

    public AllChangeTroopCard(int troopChange) {
        this.troopChange = troopChange;

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
        //TODO and also check messages and titles for better name
    }
}
