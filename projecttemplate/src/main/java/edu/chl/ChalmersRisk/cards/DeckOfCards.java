package edu.chl.ChalmersRisk.cards;

import edu.chl.ChalmersRisk.ICard;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Björn Bergqvist on 07/05/15.
 */
public class DeckOfCards {
    private LinkedList<ICard> deck;
    private LinkedList<ICard> sortedDeck;

    public DeckOfCards(){
        deck = new LinkedList<ICard>();
        sortedDeck = new LinkedList<ICard>();
    }

    public void addCardToDeck(ICard card){
        sortedDeck.add(card);
    }

    public void addCardToBackOfDeck(ICard card){
        sortedDeck.add(card);
        deck.add(card);
    }

    //An implementation of the Fisher-Yates shuffle algorithm
    //based on http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
    private void shuffle(){
        deck = sortedDeck;
        Random rand = new Random();
        for (int i=0;i<deck.size()-1;i++){
            int j = rand.nextInt(i-deck.size())+deck.size();
            //Switch places for deck[i] and deck[j];
            ICard temp = deck.get(i);
            deck.add(i,deck.get(j));
            deck.add(j,temp);
        }
    }

    public ICard pullCard(){
        ICard card =  deck.get(0);
        deck.remove(card);

        if (deck.size()<1){
            shuffle();
        }

        return card;
    }
}
