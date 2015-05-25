package edu.chl.ChalmersRisk.gui;

import edu.chl.ChalmersRisk.model.Territory;
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
    Text text;
    Button button;
    ImageView image;

    public TerritoryView(){
        super();
    }

    public TerritoryView(Territory territory){
        super();
        this.territory = territory;
        //this.setStyle("-fx-background-color: #FFFFFF;");
        this.setLayoutX(territory.pos.getX());
        this.setLayoutY(territory.pos.getY());
        image = new ImageView((territory.url));
        this.getChildren().add(image);

        button = new Button("0");
        button.setStyle("-fx-font-weight: 25;");
        button.setStyle("-fx-background-color: #FFFFFF;");

        button.setMouseTransparent(true);
        this.getChildren().add(button);

        //text = new Text("0");
        //text.setFill(Color.WHITE);
        //this.getChildren().add(text);

    }



    public void paintButton() {

        button.setStyle("-fx-background-color: #" + this.getTerritory().getOwner().getColor() + ";");
        button.setText("" + this.getTerritory().getAmountOfTroops());
        button.setTextFill(Color.WHITE);
        image.setEffect(new DropShadow(10, Color.valueOf("#"+this.getTerritory().getOwner().getColor())));

    }


    public Territory getTerritory(){
        return territory;
    }


    public void setTerritory(Territory territory){
        this.territory = territory;
    }
}
