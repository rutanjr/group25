package edu.chl.ChalmersRisk;

import edu.chl.ChalmersRisk.controller.ChalmersRisk;
import edu.chl.ChalmersRisk.view.StartScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Malin on 2015-04-28.
 *
 * The application that will be run
 */
public class CRApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ChalmersRisk");


        ChalmersRisk chalmersRisk = new ChalmersRisk();
        StartScreen startScreen = new StartScreen(primaryStage,chalmersRisk);

        Scene scene = new Scene(startScreen, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
