package edu.chl.ChalmersRisk.cardModels;

/**
 * A blank card that wont have any effect on the game. This card doesn't have any effect but will help
 * with our abstraction of a deck.
 *
 * Created by chrh on 26-May-15.
 */
public class BlankCard implements ICard {

    private String title, message;

    public BlankCard() {
        this.title = "No effect";
        this.message = "Nothing out of the ordinary occurs.";
    }

    @Override
    public String getTitle() { return this.title; }

    @Override
    public String getMessage() { return this.message; }

    @Override
    public int phaseCheck() { return 0; }

    @Override
    public void turnCard() { /* This card has no effect. */ }
}
