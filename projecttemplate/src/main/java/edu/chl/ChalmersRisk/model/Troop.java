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
    private Territory territory;


    public Troop(Player owner) {
        this.owner = owner;
        territory = null;
    }


    /**
     * Checks if the troop is placed or not. If the player has unplaced troops he cannot move on to
     * the attack phase from the place troops phase.
     * @return whether the troop is placed
     */
    public boolean isPlaced(){
        return placed;
    }


    /**
     * Places the troop.
     * @param territory to place troop on.
     */
    public void placeMe(Territory territory){
        this.territory = territory;
        this.territory.addTroops(1);
        if(territory != null){
            placed = true;
        }

    }



}
