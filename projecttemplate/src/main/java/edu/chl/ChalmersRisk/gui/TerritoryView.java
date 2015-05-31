package edu.chl.ChalmersRisk.gui;

import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.utilities.Constants;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * @revisedBy rutanjr, Oskar Rutqvist
 * Created by Malin on 2015-05-14.
 *
 *
 * Class used to display a territory on the map.
 */
public class TerritoryView extends StackPane {


    private Territory territory;
    private Button button;
    private ImageView image;

    /**
     * Empty class constructor
     */
    public TerritoryView(){
        this(Constants.EMPTY_TERRITORY);
    }


    /**
     * Class constructor
     * @param territory the territory that this view should display
     */
    public TerritoryView(Territory territory){
        super();
        this.territory = territory;
        this.setLayoutX(territory.getPos().getX());
        this.setLayoutY(territory.getPos().getY());
        image = new ImageView(territory.getUrl());
        this.getChildren().add(image);

        //a button to represent the text for each territory. Displays the number of troops on the territory.
        button = new Button("0");
        button.setStyle("-fx-font-weight: 25;");
        button.setStyle("-fx-background-color: #FFFFFF;");

        if(image.getImage().getWidth() > 190) {
            setMargin(button, new Insets(8,160,8,30));
        }


        button.setMouseTransparent(true);
        this.getChildren().add(button);
    }


    /**
     * Method that paints and sets the text on the button on the view
     */
    public void paintButton() {

        button.setStyle("-fx-background-color: #" + this.getTerritory().getOwner().getColor() + ";");

        button.setText("" + this.getTerritory().getAmountOfTroops());

        button.setTextFill((this.getTerritory().getAmountOfTroops() == 0) ? Color.BLACK : Color.WHITE);
        image.setEffect(Constants.createDropShadow(Color.valueOf("#" + this.getTerritory().getOwner().getColor())));
    }


    /**
     * Method is used to set a border around a button.
     */
    public void setFocused(){
        button.setEffect(Constants.createDropShadow(Color.BLACK,0.8));
    }


    /**
     * Method is used to clear any borders on a button.
     */
    public void removeFocused(){
        button.setEffect(Constants.createDropShadow(Color.valueOf("#" + this.getTerritory().getOwner().getColor()),0));
    }


    public Territory getTerritory(){
        return territory;
    }

    public ImageView getImage() {
        return image;
    }

    public Button getButton() {
        return button;
    }

    public void setTerritory(Territory territory){
        this.territory = territory;
    }
}
