package edu.chl.ChalmersRisk.model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by Malin on 2015-04-23.
 *
 * interface for the different maps
 */
public interface Maps {

    ArrayList<Territory> getTerritories();
    ArrayList<Continent> getContinents();

    double getWidth();
    double getHeight();
    ImageView getBackground();

}
