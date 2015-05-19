package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.ICard;

/**
 * A card that upon being drawn will grant the player an additional move phase this turn.
 *
 * Created by chrh on 2015-05-19.
 */
public class AdditionalMoveCard implements ICard {

    private String title = "Additional movement phase";

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return "Due to assistance from a professor, you are allowed to move your troops an additional time this turn.";
    }

    @Override
    public void turnCard() {
        //TODO
    }
}
