package edu.chl.ChalmersRisk.gui;

import edu.chl.ChalmersRisk.model.Die;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Created by Malin on 2015-05-30.
 */
public class DiceView extends BorderPane {


    private ImageView image;


    public DiceView(){

        super();

        image = new ImageView("noll.png");
        image.setFitHeight(45);
        image.setFitWidth(45);

        this.setCenter(image);
    }



    public void setPicture(int number){
        switch(number){
            case 0:
                image = new ImageView("noll.png");
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
