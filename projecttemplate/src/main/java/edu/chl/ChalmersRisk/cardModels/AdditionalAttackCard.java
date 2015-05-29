package edu.chl.ChalmersRisk.cardModels;


import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.utilities.Constants;

/**
 * A card that upon being drawn will grant the player an additional attack phase this turn.
 *
 * Created by chrh on 2015-05-19.
 */
public class AdditionalAttackCard implements ICard {

    private String title, message;
    private Player currentPlayer;

    public AdditionalAttackCard(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.title = "Additional attack phase";
        this.message = "Thanks to assistance from the Student Union, you may attack your " +
                "opponent an additional time this turn.";
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int phaseCheck() {
        return 0;
    }

    /**
     * @pre, either right before or right after the attack phase.
     */
    @Override
    public void turnCard() {
        //TODO should be a simple check with phasecheck and a call on the currentPlayer.doAttackPhase()ish method
    }
}