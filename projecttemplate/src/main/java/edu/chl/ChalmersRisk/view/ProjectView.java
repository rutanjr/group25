package edu.chl.ChalmersRisk.view;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Project;
import edu.chl.ChalmersRisk.model.Territory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ProjectView extends Application {

    GameBoard gameBoard;
    ArrayList<Territory> territories;
    ArrayList<Continent> continents;

    public ProjectView() {}

    public ProjectView(String[] args, ArrayList<Territory> territories, ArrayList<Continent> continents) {
        this.territories = territories;
        for (Territory t : territories)
            System.out.println(t.getName());

        this.continents = continents;
        for(Continent c: continents)
            System.out.println(c.getName());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ChalmersRisk");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        //Change these values to set the distances between objects in the view.
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        gameBoard = new GameBoard(continents);

        int i = 0;
        for (Button territorybutton: gameBoard.getTerritoryButtons()){
            grid.add(territorybutton, 0, i);
            i ++;
        }

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
