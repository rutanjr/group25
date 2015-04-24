package edu.chl.ChalmersRisk.model;

import java.util.ArrayList;

/**
 * Created by Malin on 2015-04-23.
 */
public class ChalmersMap implements Maps {


    //defining how many total continents and territories
    private final Continent[] continents = new Continent[1];
    private final ArrayList<Territory> territories = new ArrayList<Territory>();

    public ChalmersMap(){
        territories.add(new Territory("A",continents[0]));
        territories.add(new Territory("B", continents[0]));


        continents[0] = new Continent("Chalmers",4,territories);
    }


    @Override
    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    @Override
    public Continent[] getContinents() {
        return continents;
    }
}
