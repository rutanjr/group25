package edu.chl.ChalmersRisk.model;

/**
 * Created by rutanjr on 2015-03-31.
 * This is to create troops, each player recieves troops each turn and this is the class that represents them.
 * The troops can't change owner, the troops belongs to a player until death. When they die they "disappear" and the player receives new troops next turn.
 *
 * @revisedBy Malin Thelin
 *
 */
public class Troop {
    private final Player owner;
    private boolean placed;
    //TODO territory reference
    private Territory territory;


    public Troop(Player owner) {
        this.owner = owner;
        territory = null;
    }


    //if a troop is not placed, it has to be placed before a player can finish their turn.
    public boolean isPlaced(){
        return placed;
    }


    //used by Player method placeTroop to tell the troop that it is placed.
    public void placeMe(Territory territory){
        this.territory = territory;
        this.territory.addTroops(1);
        if(territory != null){
            placed = true;
        }

    }



}
