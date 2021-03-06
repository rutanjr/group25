package edu.chl.ChalmersRisk.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Malin on 2015-04-17.
 * @revisedBy rutanjr, Oskar Rutqvist
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

        //Change these values to set the distances between objects in the view.
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new javafx.geometry.Insets(25,25,25,25));
        this.setStyle("-fx-background-color: #102745;");


        playerOne = new TextField();
        playerOne.setStyle("-fx-background-color: #526D93; -fx-text-fill: #D1E4FF; -fx-prompt-text-fill: #D1E4FF");
        playerOne.setPromptText("First players name");
        this.add(playerOne, 1, 1);

        playerTwo = new TextField();
        playerTwo.setStyle("-fx-background-color: #526D93; -fx-text-fill: #D1E4FF; -fx-prompt-text-fill: #D1E4FF");
        playerTwo.setPromptText("Second players name");
        this.add(playerTwo, 1, 2);

        startButton = new Button("Start game");
        startButton.setTextFill(Color.valueOf("#D1E4FF"));
        startButton.setStyle("-fx-background-color: #526D93;");
        this.add(startButton, 1, 3);

        warningText = new Text();
        this.add(warningText,1,6);


    }

    /**
     *
     * Method to set the warning text. For intstance if you haven't filled in any names for the players
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


