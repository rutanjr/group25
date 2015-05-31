package edu.chl.ChalmersRisk.cardModels;


import edu.chl.ChalmersRisk.model.Player;

/**
 * INACTIVE
 * A card that upon being drawn will grant the player an additional move phase this turn.  Due to how the game is currently
 * implemented means that this card is unused. If in the future we implements a different ruleset for the game this game
 * this card could be activated.
 *
 * @author Robin Jansson
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
        return 3;
    }

    /**
     * @pre, either right before or right after the move phase.
     */
    @Override
    public void turnCard() {
    }
}
