package edu.chl.ChalmersRisk.gui;

import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.utilities.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created by Malin on 2015-05-14.
 */
public class TerritoryView extends StackPane {


    private Territory territory;
    private Button button;
    private ImageView image;

    public TerritoryView(){
        this(Constants.EMPTY_TERRITORY);
    }

    public TerritoryView(Territory territory){
        super();
        this.territory = territory;
        this.setLayoutX(territory.getPos().getX());
        this.setLayoutY(territory.getPos().getY());
        image = new ImageView(territory.getUrl());
        this.getChildren().add(image);

        button = new Button("0");
        button.setStyle("-fx-font-weight: 25;");
        button.setStyle("-fx-background-color: #FFFFFF;");


        System.out.println(image.getImage().getWidth() + "   img: " + image.getImage().impl_getUrl());

        button.setMouseTransparent(true);
        this.getChildren().add(button);
    }



    public void paintButton() {

        button.setStyle("-fx-background-color: #" + this.getTerritory().getOwner().getColor() + ";");


        button.setText("" + this.getTerritory().getAmountOfTroops());

        button.setTextFill((this.getTerritory().getAmountOfTroops() == 0) ? Color.BLACK : Color.WHITE);
        image.setEffect(new DropShadow(10, Color.valueOf("#" + this.getTerritory().getOwner().getColor())));

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
