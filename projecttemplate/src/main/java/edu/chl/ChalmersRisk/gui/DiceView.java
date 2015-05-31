package edu.chl.ChalmersRisk.gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Created by Malin on 2015-05-30.
 *
 * Class for displaying a picture of a die
 */
public class DiceView extends BorderPane {


    private ImageView image;

    /**
     * Class constructor. initializes the image to an empty die or zero-valued.
     */
    public DiceView(){

        super();

        image = new ImageView("noll.png");
        image.setFitHeight(45);
        image.setFitWidth(45);

        this.setCenter(image);
    }


    /**
     * Method to set the picture on the die.
     * @param number determines what picture should be on the die.
     */
    public void setPicture(int number){
        switch(number){
            case 0:
                image = new ImageView("noll.png");
                break;
            case 1:
                image = new ImageView("one.png");
                break;
            case 2:
                image = new ImageView("two.png");
                break;
            case 3:
                image = new ImageView("three.png");
                break;
            case 4:
                image = new ImageView("four.png");
                break;
            case 5:
                image = new ImageView("five.png");
                break;
            case 6:
                image = new ImageView("six.png");
                break;
        }

        image.setFitHeight(45);
        image.setFitWidth(45);
        this.setCenter(image);

    }


}
