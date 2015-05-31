package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Continent;

import java.util.ArrayList;

/**
 * A card that upon being drawn will increase/decrease the number of troops in all territories.
 *
 * @author Robin Jansson
 */
public class AllChangeTroopCard implements ICard {

    private String message, title;
    private int troopChange;
    private ArrayList<Continent> allContinents;

    /**
     * @param allContinents, a reference to the list of all continents
     * @param troopChange, an integer indicating the change in troops of all territories (can be negative or positive).
     */
    public AllChangeTroopCard(ArrayList<Continent> allContinents, int troopChange) {
        this.troopChange = troopChange;
        this.allContinents = allContinents;

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
    public int phaseCheck() {
        return 0;
    }

    @Override
    public void turnCard() {
        for (Continent temp : allContinents) {  // loops through the list of continents
            for (int i = 0; i < temp.getTerritories().size(); i++) {    // loops through list of territories
                if (troopChange < 0) {
                    temp.getTerritories().get(i).removeTroops(-troopChange);
                } else if (troopChange >= 0) {
                    temp.getTerritories().get(i).addTroops(troopChange);
                }
            }
        }
    }
}
