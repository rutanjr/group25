package edu.chl.ChalmersRisk.cardModels;

/**
 * /* The interface iCard describes the playing card for the board game Risk
 * @author Robin Jansson
 */

public interface ICard {

    /**
     * @return a String that contains the title of the card.
     */
    public String getTitle();

    /**
     * @return a String that contains the message that should be displayed to the player.
     */
    public String getMessage();

    /**
     * @return an Integer value to test in what phase of the players turn the card effect should occur.
     */
    public int phaseCheck();

    /**
     *  this method executes the effect of the card
     */
    public void turnCard();

}
