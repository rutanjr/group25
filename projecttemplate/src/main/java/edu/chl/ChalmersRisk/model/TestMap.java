package edu.chl.ChalmersRisk.model;

import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by Malin on 2015-05-28.
 */
public class TestMap implements Maps {

    //defining how many total continents and territories
    private final ArrayList<Continent> continents = new ArrayList<Continent>();
    private final ArrayList<Territory> territories = new ArrayList<Territory>();
    private final ImageView background;

    public TestMap() {
        continents.add(new Continent("Chalmers", 4,territories));
        background = new ImageView("Chalmers.png");

        territories.add(new Territory("Vasa norra",continents.get(0), new Point2D.Double(31.5,87.5), "vasa.png")); //correct place
        territories.add(new Territory("Vasa södra", continents.get(0), new Point2D.Double(127.5,87.5), "vasa2.png"));
        territories.add(new Territory("Chalmers Villan och Friskis", continents.get(0), new Point2D.Double(115,45), "chalmersvillan.png"));
        territories.add(new Territory("Fysik", continents.get(0), new Point2D.Double(167.5,140), "fysik.png"));

    }


    @Override
    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    @Override
    public ArrayList<Continent> getContinents() {
        return continents;
    }

    @Override
    public double getWidth() {
        return background.getImage().getWidth();
    }

    @Override
    public double getHeight() {
        return background.getImage().getHeight();
    }

    @Override
    public ImageView getBackground() {
        return background;
    }
}
