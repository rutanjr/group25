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

    //A cup of dice to use when resolving combat.
    private DiceCup cupOfDice;

    public Player(String name, String color){
        this.name = name;
        this.color = color;
        placedTroops = new ArrayList<Troop>();
        troopsToPlace = new ArrayList<Troop>();
        territories = new ArrayList<Territory>();
        cupOfDice = new DiceCup();
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


    public void loseTerritory(Territory territory){
        territories.remove(territory);
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

    /**
     * A method for moving all available troops from one territory to another.
     * @param fromTerritory the territory to move the troops from.
     * @param toTerritory the territory to move the troops to.
     * @return if the move was successful.
     */
    public boolean moveTroops(Territory fromTerritory, Territory toTerritory){
        return moveTroops(fromTerritory,toTerritory,fromTerritory.getAmountOfTroops()-1);
    }

    /**
     * A method for moving troops from one territory to another.
     * @param fromTerritory the territory to move the troops from.
     * @param toTerritory the territory to move the troops to.
     * @param amount the amount of troops.
     * @return if the move was successful.
     */
    public boolean moveTroops(Territory fromTerritory, Territory toTerritory, int amount){
        //Return false if less than 1 troops should be moved.
        if (amount<1){
            return false;
        }

        //Test if current player owns the territory.
        if(this!=fromTerritory.getOwner()){
            return false;
        }

        //Tests if the territories are owned by the same player.
        if(fromTerritory.getOwner()!=toTerritory.getOwner()){
            //throw new IllegalArgumentException("Territories aren't owned by the same player.");
            return false;
        }

        //Tests if there is a path between two territories.
        if (fromTerritory.isConnectedTo(toTerritory)){

            // should it move the actual troops instead?
            if (fromTerritory.getAmountOfTroops()-1>=amount){
                fromTerritory.removeTroops(amount);
                toTerritory.addTroops(amount);
                return true;
            }
        }
        return false;
        //throw new IllegalArgumentException("There are no path between the territories.");

    }

    /**
     * A method for resolving combat. Uses all troops available in territory.
     * @param attacker the Territory that the attacking troops comes from.
     * @param defender the Territory that is being defended.
     * @return true if the defender has lost all it troops in the territory.
     */
    public boolean combat(Territory attacker, Territory defender){
        return combat(attacker, defender, attacker.getAmountOfTroops() - 1);
    }

    /**
     * A method for resolving combat.
     * @param attacker the Territory that the attacking troops comes from.
     * @param defender the Territory that is being defended.
     * @param atkTroops the amount of troops to attack with.
     * @return true if the defender has lost all it troops in the territory.
     */
    public boolean combat(Territory attacker, Territory defender, int atkTroops)
            throws IllegalArgumentException{
        int[] atkRoll;
        int[] defRoll;
        //Attacker selects a number of dice <= #troops - 1 and 3

        if(attacker.getOwner().equals(defender.getOwner())){
            throw new IllegalArgumentException("Both territories are own by the same player.");
        }

        if (atkTroops<1){
            throw new IllegalArgumentException("There are too few troops to attack");
        }

        if (atkTroops >3) atkTroops = 3;

        //Defender gets two die if #troops <= 2, #troops = 1 gives 1 die.

        int defTroops = defender.getAmountOfTroops();
        if (defTroops>2) defTroops = 2;

        //Creating attacker's die array.
        if(atkTroops>=3){
            atkRoll = cupOfDice.rollDice(3);
        }
        else if (atkTroops==2){
            atkRoll = cupOfDice.rollDice(2);
        } else {
            atkRoll = cupOfDice.rollDice(1);
        }


        //Creating defender's die array.
        if (defTroops>=2){
            defRoll = cupOfDice.rollDice(2);
            System.out.println(defRoll[0]);
        } else {
            defRoll = cupOfDice.rollDice(1);
        }

        while (atkTroops > 0 && defTroops >0){
            int atkHighest = 0;
            for (int i=0;i<atkTroops;i++){
                if (atkRoll[i]>atkRoll[atkHighest]){

                    atkHighest = i;
                }
            }

            int defHighest = 0;
            for (int i=0;i<defTroops;i++){
                if (defRoll[i]>defRoll[defHighest]){
                    defHighest = i;
                }
            }

            //if the attacker's die is higher than the defender's then defender loses a troop
            //else attacker loses a troop
            if (atkRoll[atkHighest] > defRoll[defHighest]){
                //Defender lose a troop;
                defender.removeTroops(1);
            }else{
                //Attacker lose a troop;
                attacker.removeTroops(1);
            }

            //Remove die roll from pool.
            atkRoll[atkHighest] = 0;
            defRoll[defHighest] = 0;

            atkTroops--;
            defTroops--;
        }

        if (defender.getAmountOfTroops()<1){
            return true;
        } else {
            return false;
        }
    }
}
