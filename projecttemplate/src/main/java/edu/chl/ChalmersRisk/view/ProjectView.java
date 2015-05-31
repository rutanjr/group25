package edu.chl.ChalmersRisk.view;

import edu.chl.ChalmersRisk.model.Continent;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;


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
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(startButton);
        grid.add(hBox,2,3);

        Text playerOneLabel = new Text("First player");
        playerOneLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(playerOneLabel, 1, 1);

        Text playerTwoLabel = new Text("Second player");
        playerTwoLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(playerTwoLabel, 1, 2);

        TextField playerOne = new TextField();
        grid.add(playerOne, 2, 1);

        TextField playerTwo = new TextField();
        grid.add(playerTwo,2,2);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // ChalmersRisk.startGame(new String[] {playerOne.getText(), playerTwo.getText()}, primaryStage);
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
