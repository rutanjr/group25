package edu.chl.ChalmersRisk.model;

import edu.chl.ChalmersRisk.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rutanjr on 2015-03-31.
 * A class to represent continents. Controlling an entire continent grants bonus troops.
 *
 * @revisedBy Robin Jansson
 */
public class Continent {

    private String name;

    private final int value; // this is used if we intend to have different Continents giving a different amount of bonus troops
    private ArrayList<Territory> territories;

    private Player owner = Constants.EMPTY_PLAYER;

    // Constructors
    public Continent(String name, int value){
        this(name,value,new ArrayList<Territory>());
    }

    public Continent(String name, int value, ArrayList<Territory> territories)   {
        this.name = name;
        this.value = value;
        this.territories = territories;
    }

    /**
     * @return name of the continent.
     */
    public String getName() { return this.name; }

    /**
     * If a player controls the entire continent at the start of it's turn that player gets bonus
     * troops according to the value of the continent.
     * @return value of the continent
     */
    public int getValue()   { return this.value; }

    /**
     *
     * @return ArrayList of territories located in this continent.
     */
    public List<Territory> getTerritories() { return territories; }

    /**
     * @return player that owns the continent if its controlled by only one player.
     */
    public Player getOwner() { return this.owner; }

    public void setName(String name){
        this.name = name;
    }

    public void setOwner(Player owner) { this.owner = owner;}
    /**
    * Compares owners of territories to see if they are all owned by the same player.
    * @param player The Player object reference to the player who's tested if he controls all the
    * territories in the given continent.
    * @return True of the same player owns all territories in a continent
    */
    public boolean isContinentOwned(Player player) {
        for (int i = territories.size(); i > 0; i--) {
            if (!territories.get( (i-1 ) ).getOwner().equals(player)) {
                return false;
            }
        }
        return true;
    }
}
