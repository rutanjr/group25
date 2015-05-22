package edu.chl.ChalmersRisk.model;


import edu.chl.ChalmersRisk.utilities.Constants;

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


    //teamcolor
    private String color;

    public Player(String name, String color){
        this.name = name;
        this.color = color;
        placedTroops = new ArrayList<Troop>();
        troopsToPlace = new ArrayList<Troop>();
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
            //dumt att kasta exception.. vi kan hantera det lätt med nån text bara som säger det här och typ "try again"
            throw new IllegalArgumentException("The number of troops you want to place is larger than what you have. " +
                    "You have " + String.valueOf(troopsToPlace.size()) + " troops");
        }
        int nrOfPlaced = 0;
        while (!troopsToPlace.isEmpty() && nrOfPlaced < number) {
            if(!troopsToPlace.get(0).isPlaced()) {
                troopsToPlace.get(0).placeMe(territory);
                System.out.println("Placing troop!");
                if(!territory.getOwner().equals(this)){
                    territories.add(territory);
                    territory.setnewOwner(this);
                }
            }
            placedTroops.add(troopsToPlace.get(0));
            troopsToPlace.remove(0);
            nrOfPlaced++;
        }

    }

    public boolean isMyTerritory(Territory territory) {
        return territories.contains(territory);
    }

    public int getnmbrOfTerritories() {
        return territories.size();
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
    public ArrayList<Troop> getTroopsToPlace() {
        return (ArrayList<Troop>) troopsToPlace.clone();
    }

    public int amountOfTroops(){
        int count = 0;
        for(Troop t : getTroopsToPlace()){
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

    public String getColor(){
        return color;
    }

    public void addTerritories(Territory t1) {
        territories.add(t1);
    }

    public void removeTerritory(Territory t1) {
        t1.setnewOwner(Constants.EMPTY_PLAYER);
        territories.remove(t1);
    }
}
