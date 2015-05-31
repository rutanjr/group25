package edu.chl.ChalmersRisk.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @revisedBy rutanjr, Oskar Rutqvist
 * Created by Malin on 2015-05-05.
 */
public class InformationStrip extends BorderPane {

    private Text gameText;
    private final Button nextButton;

    public InformationStrip(){
        gameText = new Text("A new game has started");
        gameText.setFont(Font.font("Verdana", 16));
        gameText.setFill(Color.valueOf("#D1E4FF"));
        nextButton = new Button("NEXT");
        nextButton.setPrefSize(100,50);
        nextButton.setStyle("-fx-background-color: #526D93;");
        nextButton.setTextFill(Color.valueOf("#D1E4FF"));
        this.setPadding(new Insets(15, 12, 15, 12));
        this.setStyle("-fx-background-color: #102745;");

        this.setLeft(gameText);
        this.setRight(nextButton);



    }


    public Text getGameText(){
        return gameText;
    }

    public void setGameText(String text){
        gameText.setText(text);
    }

    public Button getNextButton(){
        return nextButton;
    }
}
