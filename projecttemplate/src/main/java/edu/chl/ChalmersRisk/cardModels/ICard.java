package edu.chl.ChalmersRisk.cardModels;

/**
 * /* The interface iCard describes the playing card for the board game Risk
 * @author Robin Jansson
 */

public interface ICard {

    // returns the title of the card
    public String getTitle();

    //returns the message of the card
    public String getMessage();

    //Returns an Integer that will determine when the effect of the card will occur.
    public int phaseCheck();

    //turns the card, revealing its effect
    public void turnCard();

}
