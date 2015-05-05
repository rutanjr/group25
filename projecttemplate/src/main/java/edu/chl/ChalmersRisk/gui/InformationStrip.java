package edu.chl.ChalmersRisk.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Created by Malin on 2015-05-05.
 */
public class InformationStrip extends HBox {

    Text gameText;
    Button nextButton;

    public InformationStrip(){
        gameText = new Text("A new game has started");
        nextButton = new Button("NEXT");
        nextButton.setPrefSize(100,50);
        this.setPadding(new Insets(15, 12, 15, 12));
        this.setSpacing(200);
        this.setStyle("-fx-background-color: #336699;");
        this.getChildren().addAll(gameText,nextButton);
    }


    public Text getGameText(){
        return gameText;
    }

    public Button getNextButton(){
        return nextButton;
    }
}
