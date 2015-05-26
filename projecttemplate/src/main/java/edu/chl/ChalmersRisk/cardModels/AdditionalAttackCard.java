package edu.chl.ChalmersRisk.cardModels;


/**
 * A card that upon being drawn will grant the player an additional attack phase this turn.
 *
 * Created by chrh on 2015-05-19.
 */
public class AdditionalAttackCard implements ICard {

    private String title = "Additional attack phase";

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return "Thanks to assistance from the Student Union, you may attack your opponent an additional time this turn.";
    }

    @Override
    public int phaseCheck() {
        return 0;
    }

    @Override
    public void turnCard() {
        //TODO
    }
}
