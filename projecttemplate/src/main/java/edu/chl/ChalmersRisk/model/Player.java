package edu.chl.ChalmersRisk.model;

import java.util.ArrayList;

/**
 * Created by rutanjr on 2015-03-31.
 * Keeps track of the name of the player specific items, such as name, and troops belonging to this player, as well as all the territories the player currently holds.
 */
public class Player {

    private String name;
    private ArrayList<Territory> territories;
    private ArrayList<Troop> placedTroops;
    private ArrayList<Troop> troopsToPlace;

    public Player(String name){
        this.name = name;
        placedTroops = new ArrayList<Troop>();
        territories = new ArrayList<Territory>();
    }

    /**
     * Troops that the player gets at the start of this turn.
     * @param received ArrayList of troops
     */
    public void receiveTroops (ArrayList<Troop> received){
        troopsToPlace = received;
    }

    /**
     * Places a number of troops at a specified territory. Sets them as placed as well.
     * @param territory the location to place at.
     * @param number the number of troops to place.
     * @throws java.lang.IllegalArgumentException if the player doesn't have this many unplaced troops or doesn't own that territory.
     */
    public void placeTroops(Territory territory, int number){
        if(number > troopsToPlace.size()) {
            throw new IllegalArgumentException("The number of troops you want to place is larger than what you have. " +
                    "You have " + String.valueOf(troopsToPlace.size()) + " troops");
        }
        int nrOfPlaced = 0;
        while (!troopsToPlace.isEmpty() && nrOfPlaced < number) {
            if(!troopsToPlace.get(0).isPlaced()) {
                troopsToPlace.get(0).placeMe(territory);
                System.out.println("Placing troop!");
            }
            placedTroops.add(troopsToPlace.get(0));
            troopsToPlace.remove(0);
            nrOfPlaced++;
        }

    }

    public boolean isMyTerritory(Territory territory) {
        if(territories.contains(territory)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * @return ArrayList of this players all placed troops.
     */
    public ArrayList<Troop> getPlacedTroops() {
        return (ArrayList<Troop>) placedTroops.clone();
    }

    /**
     * To see if the player needs to place more troops.
     * @return weather the player has troops the yet needs placing.
     */
    public boolean hasTroopsToPlace() {
        return !troopsToPlace.isEmpty();
    }

    /**
     *
     * @return ArrayList of troops that needs placing.
     */
    public ArrayList<Troop> getTroopsToPlce() {
        return (ArrayList<Troop>) troopsToPlace.clone();
    }

    public int amountOfTroops(){
        int count = 0;
        for(Troop t : getTroopsToPlce()){
            count++;
        }
        return count;
    }

    /**
     * Used to easy calculate the number of troops to recieve.
     * @return ArrayList of territories that this player holds.
     */
    public ArrayList<Territory> getTerritories() {
        return (ArrayList<Territory>) territories.clone();
    }

    public String getName() {
        return name;
    }
}
