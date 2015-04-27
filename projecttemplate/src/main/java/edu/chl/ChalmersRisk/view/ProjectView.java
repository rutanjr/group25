package edu.chl.ChalmersRisk.view;

import edu.chl.ChalmersRisk.controller.ChalmersRisk;
import edu.chl.ChalmersRisk.model.ChalmersMap;
import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Project;
import edu.chl.ChalmersRisk.model.Territory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static edu.chl.ChalmersRisk.controller.ChalmersRisk.*;


public class ProjectView extends Application {

    GameBoard gameBoard;
    ArrayList<Territory> territories;
    ArrayList<Continent> continents;
    String[] args;

    public ProjectView() {
        System.out.println("Projectview no param constructor");
        System.out.println(this);
    }

    public ProjectView(ArrayList<Territory> territories, ArrayList<Continent> continents) {
        this.territories = territories;
        for (Territory t : territories)
            System.out.println(t.getName());

        this.continents = continents;
        for(Continent c: continents)
            System.out.println(c.getName());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ChalmersRisk");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        System.out.println("Grid created");

        //Change these values to set the distances between objects in the view.
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Button startButton = new Button("Start game");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(startButton);
        grid.add(hBox,1,3);


        TextField playerOne = new TextField("First plyers name");
        grid.add(playerOne, 1, 1);

        TextField playerTwo = new TextField("Second players name");
        grid.add(playerTwo,1,2);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ChalmersRisk.startGame(new String[] {playerOne.getText(), playerTwo.getText()}, primaryStage);
            }
        });

       /* int i = 0;
        for (Button territorybutton: gameBoard.getTerritoryButtons()){
            grid.add(territorybutton, 0, i);
            i ++;
        }*/

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
