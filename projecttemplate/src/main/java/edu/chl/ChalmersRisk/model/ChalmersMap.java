package edu.chl.ChalmersRisk.model;

import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * @revisedBy rutanjr
 * Created by Malin on 2015-04-23.
 */
public class ChalmersMap implements Maps {

    //defining how many total continents and territories
    private final ArrayList<Continent> continents = new ArrayList<Continent>();
    private final ArrayList<Territory> territories = new ArrayList<Territory>();
    private final ImageView background;

    public ChalmersMap(){

        continents.add(new Continent("Chalmers", 4,territories));
        background = new ImageView("Chalmers.png");

        territories.add(new Territory("Vasa norra",continents.get(0), new Point2D.Double(31.5,87.5), "vasa.png")); //correct place
        territories.add(new Territory("Vasa södra", continents.get(0), new Point2D.Double(127.5,87.5), "vasa2.png"));
        territories.add(new Territory("Chalmers Villan och Friskis", continents.get(0), new Point2D.Double(115,45), "chalmersvillan.png"));
        territories.add(new Territory("Fysik", continents.get(0), new Point2D.Double(167.5,140), "fysik.png"));
        territories.add(new Territory("Kemi Matematiska vetenskaper och Biblioteket", continents.get(0), new Point2D.Double(227,43), "kemimattebibliotek.png"));
        territories.add(new Territory("SSPA", continents.get(0), new Point2D.Double(366.5 ,171.5), "sspa.png"));
        territories.add(new Territory("Maskin", continents.get(0), new Point2D.Double(380,100), "maskin.png"));
        territories.add(new Territory("Hörsalarna", continents.get(0), new Point2D.Double(381,72), "horsalarna.png"));
        territories.add(new Territory("EDIT", continents.get(0), new Point2D.Double(488 ,100), "edit.png"));
        territories.add(new Territory("Kopparbunkern", continents.get(0), new Point2D.Double(544 ,77), "kopparbunkern.png"));
        territories.add(new Territory("Väg och vatten", continents.get(0), new Point2D.Double(460 ,198), "VA1.png"));
        territories.add(new Territory("Arkitektur och V & V", continents.get(0), new Point2D.Double(533.5 ,198), "VA2.png"));
        territories.add(new Territory("Kårhuset", continents.get(0), new Point2D.Double(278 ,240), "karhuset.png"));

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
