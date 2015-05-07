package edu.chl.ChalmersRisk.cardModels;

/** This class is used to test DeckOfCards.
 * Created by viking on 07/05/15.
 */
public class CardTest {
    // the title of the card
    private String title;

    public CardTest(String title){
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public String toString(){
        return title;
    }

    //turns the card, revealing its effect
    public void turnCard() {

    }
}
