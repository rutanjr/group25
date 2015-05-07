package edu.chl.ChalmersRisk.cards;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Björn Bergqvist on 07/05/15.
 */
public class DeckOfCards {
    private ArrayList<Card> deck;

    public DeckOfCards(){
        deck = new ArrayList<card>;
    }

    //An implementation of the Fisher-Yates shuffle algorithm
    //based on http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
    private void shuffle(){
        Random rand = new Random();
        for (int i=0;i<deck.size()-1;i++){
            int j = rand.nextInt(i-deck.size())+deck.size();
            //Switch places for deck[i] and deck[j];
            Card temp = deck.get(i);
            deck.add(i,deck.get(j));
            deck.add(j,temp);
        }
    }
}
