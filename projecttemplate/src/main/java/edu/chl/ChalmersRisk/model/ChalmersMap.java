package edu.chl.ChalmersRisk.model;

/**
 * Created by Malin on 2015-04-23.
 */
public class ChalmersMap implements Maps {


    //defining how many total continents and territories
    private final Continent[] continents = new Continent[1];
    private final Territory[] territories = new Territory[2];

    public ChalmersMap(){
        continents[0] = new Continent("Chalmers");
        territories[0] = new Territory("A",continents[0]);
        territories[1] = new Territory("B",continents[0]);
    }


    @Override
    public Territory[] getTerritories() {
        return territories;
    }

    @Override
    public Continent[] getContinents() {
        return continents;
    }
}
