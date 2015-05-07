package edu.chl.ChalmersRisk;

/**
 * /* The interface iCard describes the playing card for the board game Risk
 * @author Robin Jansson
 */

public interface ICard {

    // the title of the card
    public String title();

    public String getTitle();

    //turns the card, revealing its effect
    public void turnCard();

}
