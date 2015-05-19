package edu.chl.ChalmersRisk.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * Created by Malin on 2015-05-19.
 */
public class TopStrip extends BorderPane{

    Text gameText;

    public TopStrip(){
        gameText = new Text("A new game has started");
        this.setPadding(new Insets(15, 12, 15, 12));
        this.setStyle("-fx-background-color: #fba6b0;");

        this.setLeft(gameText);



    }


    public Text getGameText(){
        return gameText;
    }

    public void setGameText(String text){
        gameText.setText(text);
    }


}
