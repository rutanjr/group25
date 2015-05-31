package edu.chl.ChalmersRisk;

import edu.chl.ChalmersRisk.controller.ChalmersRisk;
import edu.chl.ChalmersRisk.utilities.Constants;
import edu.chl.ChalmersRisk.view.StartScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Malin on 2015-04-28.
 * @revisedBy rutanjr, Oskar Rutqvist
 * The application that will be run
 */
public class CRApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ChalmersRisk");

        StartScreen startScreen = new StartScreen(primaryStage); //view
        ChalmersRisk chalmersRisk = new ChalmersRisk(startScreen); //controller

        Scene scene = new Scene(startScreen, Constants.width, Constants.height);
        primaryStage.setMinHeight(Constants.height + 39);
        primaryStage.setMaxHeight(Constants.height+39);
        primaryStage.setMaxWidth(Constants.width);
        primaryStage.setMinWidth(Constants.width);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
