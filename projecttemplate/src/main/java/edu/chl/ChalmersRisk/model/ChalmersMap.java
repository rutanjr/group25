package edu.chl.ChalmersRisk.model;

import java.util.ArrayList;

/**
 * Created by Malin on 2015-04-23.
 */
public class ChalmersMap implements Maps {


    //defining how many total continents and territories
    private final ArrayList<Continent> continents = new ArrayList<Continent>();
    private final ArrayList<Territory> territories = new ArrayList<Territory>();

    public ChalmersMap(){

        continents.add(new Continent("Chalmers", 4,territories));

        territories.add(new Territory("A",continents.get(0)));
        territories.add(new Territory("B", continents.get(0)));

    }


    @Override
    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    @Override
    public ArrayList<Continent> getContinents() {
        return continents;
    }
}
