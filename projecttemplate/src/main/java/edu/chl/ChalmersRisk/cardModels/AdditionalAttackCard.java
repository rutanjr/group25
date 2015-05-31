package edu.chl.ChalmersRisk.cardModels;


import edu.chl.ChalmersRisk.model.Player;

/**
 * INACTIVE
 * A card that upon being drawn will grant the player an additional attack phase this turn. Due to how the game is currently
 * implemented means that this card is unused. If in the future we implements a different ruleset for the game this game
 * this card could be activated.
 *
 * @author Robin Jansson
 */
public class AdditionalAttackCard implements ICard {

    private String title, message;
    private Player currentPlayer;

    /**
     * @param currentPlayer, the player being awarded with an additional attack phase.
     */
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
        return 2;
    }

    /**
     * @pre, either right before or right after the attack phase.
     */
    @Override
    public void turnCard() {
    }
}