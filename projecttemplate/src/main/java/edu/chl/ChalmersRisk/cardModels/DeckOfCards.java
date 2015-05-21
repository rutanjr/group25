package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.ICard;

import java.util.LinkedList;
import java.util.Random;

/**
 * A class used to store cardModels.
 * Created by Björn Bergqvist on 07/05/15.
 */
public class DeckOfCards {
    private LinkedList<ICard> deck;
    private LinkedList<ICard> sortedDeck;

    public DeckOfCards(){
        deck = new LinkedList<ICard>();
        sortedDeck = new LinkedList<ICard>();
    }

    /**
     * Add a card to the deck,
     * it wont show up in play until deck has been reshuffled.
     * @param card the card to be added.
     */
    public void addCardToDeck(ICard card){
        sortedDeck.add(card);
    }


    /**
     * Add a card to the deck that is currently in use.
     * @param card
     */
    public void addCardToBackOfDeck(ICard card){
        sortedDeck.add(card);
        deck.add(card);
    }

    /**
     * Shuffles the current deck
     * based on an implementation of the Fisher-Yates shuffle algorithm
     */
    public void shuffle(){
        Random rand = new Random();
        for (int i=0;i<deck.size()-1;i++){
            int j = rand.nextInt(deck.size()-i)+i;
            //Switch places for deck[i] and deck[j];
            ICard temp = deck.get(i);
            deck.set(i,deck.get(j));
            deck.set(j,temp);
        }
    }

    /**
     * Resets the deck so it includes all cardModels and reshuffles it.
     */
    public void resetDeck(){
        deck=sortedDeck;
        shuffle();
    }

    /**
     * Pulls a card from the deck, removing it from the list.
     * If it pulls the last card the deck will reset.
     * @return a card.
     */
    public ICard pullCard(){
        ICard card =  deck.get(0);
        deck.remove(card);

        if (deck.size()<1){
            resetDeck();
        }

        return card;
    }

    /**
     * Get the first card in the deck.
     * @return the first card in the deck.
     */
    public ICard getFirst(){
        return deck.getFirst();
    }

    /**
     * Get the last card in the deck.
     * @return the last card in the deck.
     */
    public ICard getLast(){
        return deck.getLast();
    }

    /**
     * Get the size of the deck in play.
     * @return size of the deck in play.
     */
    public int size(){
        return deck.size();
    }

    /**
     * Get the size of the whole deck.
     * @return size of the whole deck.
     */
    public int sortedSize(){
        return sortedDeck.size();
    }
}
