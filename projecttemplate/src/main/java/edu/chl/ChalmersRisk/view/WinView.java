package edu.chl.ChalmersRisk.view;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * Creates a popup when a player wins the game.
 * Created by rutanjr on 2015-05-30.
 */
public class WinView {
    public WinView() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("You win!");
        alert.setHeaderText(null);
        alert.setContentText("You just on the game! Congratulations!");
        alert.showAndWait();
    }
}
