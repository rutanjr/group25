package edu.chl.ChalmersRisk.view;

import edu.chl.ChalmersRisk.controller.ChalmersRisk;
import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Territory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import javafx.scene.control.*;
import java.util.ArrayList;

/**
 * Created by Malin on 2015-04-17.
 *
 *StartScreen for the game. Can be further developed in numerous ways.
 */
public class StartScreen extends GridPane{

        ArrayList<Territory> territories;
        ArrayList<Continent> continents;

        Button startButton;
        TextField playerOne,playerTwo;
        Stage primaryStage;

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
            this.add(hBox,1,3);


            playerOne = new TextField("First players name");
            this.add(playerOne, 1, 1);

            playerTwo = new TextField("Second players name");
            this.add(playerTwo, 1, 2);



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


