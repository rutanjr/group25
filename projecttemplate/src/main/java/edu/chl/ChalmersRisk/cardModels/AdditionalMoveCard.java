package edu.chl.ChalmersRisk.cardModels;


import edu.chl.ChalmersRisk.model.Player;

/**
 * A card that upon being drawn will grant the player an additional move phase this turn.
 *
 * Created by chrh on 2015-05-19.
 */
public class AdditionalMoveCard implements ICard {

    private String title, message;
    private Player currentPlayer;

    public AdditionalMoveCard(Player currentPlayer) {
        this.currentPlayer = currentPlayer;

        this.title = "Additional movement phase";
        this.message = "Due to assistance from a professor, you are allowed to move your troops an additional time this turn.";
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() { return this.message; }

    @Override
    public int phaseCheck() {
        return 0;
    }

    /**
     * @pre, either right before or right after the move phase.
     * @post, grants the user an additional move phase.
     */
    @Override
    public void turnCard() {
        //TODO should be a simple check with phasecheck and a call on the currentPlayer.doMovePhase()ish method
    }
}
