package edu.chl.ChalmersRisk.cardModels;


/** This class is used to test DeckOfCards.
 * Created by viking on 07/05/15.
 */
public class CardTest implements ICard {
    // the title of the card
    private String title;

    public CardTest(String title){
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getMessage() {
        return title;
    }

    @Override
    public int phaseCheck() {
        return 0;
    }

    public String toString(){
        return title;
    }

    //turns the card, revealing its effect
    public void turnCard() {

    }
}
