package edu.chl.ChalmersRisk.model;

import java.awt.geom.Point2D;
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

        /*territories.add(new Territory("A",continents.get(0)));
        territories.add(new Territory("B", continents.get(0)));
        territories.add(new Territory("C", continents.get(0)));
        territories.add(new Territory("D", continents.get(0)));*/
        territories.add(new Territory("A",continents.get(0), new Point2D.Double(20,25), "first_territory.png"));
        territories.add(new Territory("B", continents.get(0), new Point2D.Double(80,40), "first_territory.png"));
        territories.add(new Territory("C", continents.get(0), new Point2D.Double(120,60), "first_territory.png"));
        territories.add(new Territory("D", continents.get(0), new Point2D.Double(200,40), "first_territory.png"));

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
