package edu.chl.ChalmersRisk.model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by Malin on 2015-04-23.
 *
 * interface for the different maps
 */
public interface Maps {

    /**
     * Returns the ArrayList of all territories in this map.
     * @return ArrayList of territories.
     */
    ArrayList<Territory> getTerritories();

    /**
     * Returns a list of all continents in this map.
     * @return ArrayList of continents.
     */
    ArrayList<Continent> getContinents();

    /**
     * Returns height of the map.
     * @return height
     */
    double getWidth();

    /**
     * Returns the width of the map.
     * @return width
     */
    double getHeight();

    /**
     * Returns the background image for the chosen map.
     * @return ImageView of the background.
     */
    ImageView getBackground();

}
