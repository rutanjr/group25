package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.ICard;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by viking on 07/05/15.
 */
public class DeckOfCardsTest {
    @Test
    public void testShuffle() throws Exception {
        DeckOfCards testDeck = new DeckOfCards();
        testDeck.addCardToBackOfDeck(new CardTest("0"));
        testDeck.addCardToBackOfDeck(new CardTest("1"));
        testDeck.addCardToBackOfDeck(new CardTest("2"));
        testDeck.addCardToBackOfDeck(new CardTest("3"));
        testDeck.addCardToBackOfDeck(new CardTest("4"));
        testDeck.addCardToBackOfDeck(new CardTest("5"));
        testDeck.addCardToBackOfDeck(new CardTest("6"));
        testDeck.addCardToBackOfDeck(new CardTest("7"));
        testDeck.addCardToBackOfDeck(new CardTest("8"));
        testDeck.addCardToBackOfDeck(new CardTest("9"));

        testDeck.shuffle();

        //Test if size of deck is the same as size of unshuffled deck.
        assertTrue(testDeck.size()==testDeck.sortedSize());

        //Test if the first card has been changed.
        //Slight risk of false negative
        String first = testDeck.getFirst().toString();
        assertFalse(Integer.parseInt(first)==0);

        //Test if the last card has been changed.
        //Slight risk of false negative
        String last = testDeck.getLast().toString();
        assertFalse(Integer.parseInt(last)==9);

        //Tests if at least one card has been shuffled.
        //There is a small chance this will give a false negative.
        boolean inOrder = true;
        for (int i=0;i<10;i++){
            ICard pulledCard = testDeck.pullCard();
            if (Integer.parseInt(pulledCard.toString())!=i){
                inOrder = false;
            }
        }

        assertFalse(inOrder);

        //Test if size has been changed.
        assertTrue(testDeck.sortedSize()==10);
    }

    @Test
    public void testPullCard() throws Exception {
        DeckOfCards testDeck = new DeckOfCards();
        testDeck.addCardToBackOfDeck(new CardTest("A"));
        testDeck.addCardToBackOfDeck(new CardTest("B"));

        ICard pulledCard = testDeck.pullCard();

        assertTrue(pulledCard.toString().equals("A"));
    }
}
