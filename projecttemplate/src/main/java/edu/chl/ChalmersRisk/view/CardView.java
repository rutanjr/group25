package edu.chl.ChalmersRisk.view;

import edu.chl.ChalmersRisk.cardModels.ICard;
import edu.chl.ChalmersRisk.utilities.Constants;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * Created by Björn Berqvist on 26/05/15.
 * @revisedBy Robin Jansson
 */
public class CardView {
    public static void display(ICard card){
        if (!card.getTitle().equals(Constants.EMPTY_CARD.getTitle())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle(card.getTitle());
            alert.setHeaderText(null);
            alert.setContentText(card.getMessage());

            alert.showAndWait();
        }

    }
}
