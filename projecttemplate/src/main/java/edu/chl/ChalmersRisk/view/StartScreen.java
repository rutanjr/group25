package edu.chl.ChalmersRisk.view;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Territory;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Created by Malin on 2015-04-17.
 *
 *StartScreen for the game. Can be further developed in numerous ways.
 */
public class StartScreen extends GridPane{

        private Button startButton;
        private TextField playerOne,playerTwo;
        private Stage primaryStage;
        private Text warningText;

        public StartScreen(Stage primaryStage) {
            this.primaryStage = primaryStage;

            this.setAlignment(Pos.CENTER);
            System.out.println("Grid created");

            //Change these values to set the distances between objects in the view.
            this.setHgap(10);
            this.setVgap(10);
            this.setPadding(new javafx.geometry.Insets(25,25,25,25));

            startButton = new Button("Start game");
            HBox hBox = new HBox(10);
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(startButton);
            this.add(hBox, 1, 3);


            playerOne = new TextField();
            playerOne.setPromptText("First players name");
            this.add(playerOne, 1, 1);

            playerTwo = new TextField();
            playerTwo.setPromptText("Second players name");
            this.add(playerTwo, 1, 2);

            warningText = new Text();
            this.add(warningText,1,6);


        }

    /**
     *
     *Method to set the warning text. For intstance if you haven't filled in any names for the players
     * @param message : the message you want to be displayed
     */
        public void setWarningText(String message) {
            warningText.setText(message);
            warningText.setFill(Paint.valueOf("red"));
        }

        public Button getStartButton(){
            return startButton;
        }

        public TextField getPlayerOne(){
            return playerOne;
        }

        public TextField getPlayerTwo(){
            return playerTwo;
        }

        public Stage getPrimaryStage(){
            return primaryStage;
        }

    }


